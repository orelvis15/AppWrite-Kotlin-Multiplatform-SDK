package io.appwrite.services

import io.appwrite.Client
import io.appwrite.exceptions.AppwriteException
import io.appwrite.extensions.forEachAsync
import io.appwrite.models.realtime.*
import io.ktor.client.plugins.websocket.*
import io.ktor.http.*
import io.ktor.websocket.*
import kotlinx.coroutines.*
import kotlinx.serialization.json.Json
import kotlin.coroutines.CoroutineContext

class Realtime(client: Client) : Service(client), CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private companion object {
        private const val TYPE_ERROR = "error"
        private const val TYPE_EVENT = "event"

        private const val DEBOUNCE_MILLIS = 1L

        private var socket: DefaultClientWebSocketSession? = null
        private var activeChannels = mutableSetOf<String>()
        private var activeSubscriptions = mutableMapOf<Int, RealtimeCallback>()

        private var subCallDepth = 0
        private var reconnectAttempts = 0
        private var subscriptionsCounter = 0
        private var reconnect = true
    }

    private fun createSocket() {
        if (activeChannels.isEmpty()) {
            return
        }

        val queryParamBuilder = StringBuilder()
            .append("project=${client.getProject()}")

        activeChannels.forEach {
            queryParamBuilder
                .append("&channels[]=$it")
        }

        runBlocking {
            socket = client.httpClient.webSocketSession(
                method = HttpMethod.Get,
                host = "${client.endPointRealtime}",
                path = "/demo?$queryParamBuilder"
            )
            socket?.coroutineContext?.job?.invokeOnCompletion { cause ->
                launch {
                    while (socket?.isActive == true) {
                        val frame = socket?.incoming?.receive()

                        if (frame is Frame.Text) {
                            val message = Json.decodeFromString<RealtimeResponse>(frame.readText())
                            when (message.type) {
                                TYPE_ERROR -> handleResponseError(message)
                                TYPE_EVENT -> handleResponseEvent(message)
                            }
                        }

                        if (frame is Frame.Close) {
                            val timeout = getTimeout()

                            println(
                                "${this@Realtime::class.java.name}, " +
                                        "Realtime disconnected. Re-connecting in ${timeout / 1000} seconds. "
                            )

                            launch {
                                delay(timeout)
                                reconnectAttempts++
                                createSocket()
                            }
                        }
                    }
                }
            }
        }

        if (socket != null) {
            reconnect = false
            closeSocket()
        }
    }

    private fun handleResponseError(message: RealtimeResponse) {
        throw Json.decodeFromString<AppwriteException>(message.data.toString())
    }

    private suspend fun handleResponseEvent(message: RealtimeResponse) {
        val event = Json.decodeFromString<RealtimeResponseEvent<Any>>(message.data.toString())
        if (event.channels.isEmpty()) {
            return
        }
        if (!event.channels.any { activeChannels.contains(it) }) {
            return
        }
        activeSubscriptions.values.forEachAsync { subscription ->
            if (event.channels.any { subscription.channels.contains(it) }) {
                event.payload = Json.decodeFromString(event.payload.toString())
                subscription.callback(event)
            }
        }
    }

    private fun closeSocket() {
        runBlocking {
            socket?.close(CloseReason(CloseReason.Codes.VIOLATED_POLICY, ""))
        }
    }

    private fun getTimeout() = when {
        reconnectAttempts < 5 -> 1000L
        reconnectAttempts < 15 -> 5000L
        reconnectAttempts < 100 -> 10000L
        else -> 60000L
    }

    fun subscribe(
        vararg channels: String,
        callback: (RealtimeResponseEvent<Any>) -> Unit,
    ) = subscribe(
        channels = channels,
        Any::class.java,
        callback
    )

    fun <T> subscribe(
        vararg channels: String,
        payloadType: Class<T>,
        callback: (RealtimeResponseEvent<T>) -> Unit,
    ): RealtimeSubscription {
        val counter = subscriptionsCounter++

        activeChannels.addAll(channels)
        activeSubscriptions[counter] = RealtimeCallback(
            channels.toList(),
            payloadType,
            callback as (RealtimeResponseEvent<*>) -> Unit
        )

        runBlocking {
            subCallDepth++
            delay(DEBOUNCE_MILLIS)
            if (subCallDepth == 1) {
                createSocket()
            }
            subCallDepth--
        }

        return RealtimeSubscription {
            activeSubscriptions.remove(counter)
            cleanUp(*channels)
            createSocket()
        }
    }

    private fun cleanUp(vararg channels: String) {
        activeChannels.removeAll { channel ->
            if (!channels.contains(channel)) {
                return@removeAll false
            }
            activeSubscriptions.values.none { callback ->
                callback.channels.contains(channel)
            }
        }
    }
}