package io.appwrite

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.res.ResourceLoader
import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpServer
import io.appwrite.exceptions.AppwriteException
import io.appwrite.extensions.toByteArray
import io.ktor.http.*
import java.net.InetSocketAddress
import java.net.URLDecoder
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

object WebAuthServer {

    private var server: HttpServer? = null

    @OptIn(ExperimentalComposeUiApi::class)
    suspend fun create(
        port: Int,
        successPath: String,
        failurePath: String,
        successHtmlResponse: ByteArray?,
        failureHtmlResponse: ByteArray?
    ): Map<String, String>? {
        return suspendCoroutine { continuation ->

            var successHtml = successHtmlResponse
            if (successHtmlResponse == null) {
                val resource = ResourceLoader.Default.load("OAuthSuccess.html")
                successHtml = resource.toByteArray()
            }

            var failureHtml = failureHtmlResponse
            if (failureHtmlResponse == null) {
                val resource = ResourceLoader.Default.load("OAuthFailure.html")
                failureHtml = resource.toByteArray()
            }

            if (server == null) {
                server = HttpServer.create(InetSocketAddress(port), 0)

                server?.createContext(Url(failurePath).fullPath) { http ->
                    sendResponse(http, failureHtml!!)
                    continuation.resume(null)
                }

                server?.createContext(Url(successPath).fullPath) { http ->
                    val parameters = http.requestURI.query
                    val decodedQuery = URLDecoder.decode(parameters, "UTF-8")
                    val queryParams = decodedQuery.split("&")

                    val paramMap = extractParams(queryParams)


                    if (paramMap["key"] == null || paramMap["secret"] == null) {
                        sendResponse(http, failureHtml!!)
                        throw AppwriteException(
                            "Invalid OAuth2 Response. Key and Secret not available.", 500
                        )
                    } else {
                        val data = mapOf("key" to paramMap["key"].toString(), "secret" to paramMap["secret"].toString())
                        sendResponse(http, successHtml!!)
                        continuation.resume(data)
                    }
                }
                server?.start()
            }
        }
    }

    private fun extractParams(
        queryParams: List<String>
    ): MutableMap<String, String> {
        val paramMap = mutableMapOf<String, String>()
        for (param in queryParams) {
            val pair = param.split("=")
            if (pair.size == 2) {
                val key = pair[0]
                val value = pair[1]
                paramMap[key] = value
            }
        }
        return paramMap
    }

    private fun sendResponse(
        http: HttpExchange,
        responseHtml: ByteArray
    ) {
        val response = http.responseHeaders
        response.add("Content-Type", "text/html; charset=utf-8")
        http.sendResponseHeaders(200, responseHtml.size.toLong())
        val os = http.responseBody
        os.write(responseHtml)
        os.close()
        server?.stop(0)
        server = null
    }
}