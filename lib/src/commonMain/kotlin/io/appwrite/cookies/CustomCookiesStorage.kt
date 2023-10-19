package io.appwrite.cookies

import com.russhwolf.settings.get
import com.russhwolf.settings.set
import io.appwrite.Storage
import io.ktor.client.plugins.cookies.*
import io.ktor.http.*
import io.ktor.util.*
import io.ktor.util.date.*
import kotlinx.atomicfu.AtomicLong
import kotlinx.atomicfu.atomic
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.math.min

object CustomCookiesStorage : CookiesStorage {
    private val storage = Storage.settings
    private val oldestCookie: AtomicLong = atomic(0L)
    private val mutex = Mutex()

    override suspend fun addCookie(requestUrl: Url, cookie: Cookie): Unit = mutex.withLock {
        with(cookie) {
            if (name.isBlank()) return@withLock
        }

        val containerCustom: MutableList<CustomCookie> = getStorageCookiesDecode().toMutableList()
        val containerCookie: MutableList<Cookie> = containerCustom.map { it.toKtorCookie() }.toMutableList()

        containerCookie.removeAll { it.name == cookie.name && it.matches(requestUrl) }
        containerCookie.add(cookie.fillDefaults(requestUrl))
        setStorageCookiesEncode(containerCookie.map { it.toCustomCookies() })

        cookie.expires?.timestamp?.let { expires ->
            if (oldestCookie.value > expires) {
                oldestCookie.value = expires
            }
        }
    }

    override fun close() {}

    private fun cleanup(timestamp: Long) {
        val container: MutableList<CustomCookie> = getStorageCookiesDecode().toMutableList()

        container.removeAll { cookie ->
            val expires = cookie.expires ?: return@removeAll false
            expires < timestamp
        }

        val newOldest = container.fold(Long.MAX_VALUE) { acc, cookie ->
            cookie.expires?.let { min(acc, it) } ?: acc
        }

        oldestCookie.value = newOldest
    }

    override suspend fun get(requestUrl: Url): List<Cookie> = mutex.withLock {
        val now = getTimeMillis()
        if (now >= oldestCookie.value) cleanup(now)

        val containerCustom: MutableList<CustomCookie> = getStorageCookiesDecode().toMutableList()
        val containerCookie = containerCustom.map { it.toKtorCookie() }
        return@withLock containerCookie.filter { it.matches(requestUrl) }
    }

    private fun getStorageCookiesDecode(): List<CustomCookie> {
        val cookies: String = storage["cookies"] ?: ""
        if (cookies.isEmpty()) return listOf()
        return Json.decodeFromString<List<CustomCookie>>(cookies)
    }

    private fun setStorageCookiesEncode(cookies: List<CustomCookie>) {
        storage["cookies"] = Json.encodeToString(cookies)
    }

    private fun Cookie.matches(requestUrl: Url): Boolean {
        val domain = domain?.toLowerCasePreservingASCIIRules()?.trimStart('.')
            ?: error("Domain field should have the default value")

        val path = with(path) {
            val current = path ?: error("Path field should have the default value")
            if (current.endsWith('/')) current else "$path/"
        }

        val host = requestUrl.host.toLowerCasePreservingASCIIRules()
        val requestPath = let {
            val pathInRequest = requestUrl.encodedPath
            if (pathInRequest.endsWith('/')) pathInRequest else "$pathInRequest/"
        }

        if (host != domain && (hostIsIp(host) || !host.endsWith(".$domain"))) {
            return false
        }

        if (path != "/" &&
            requestPath != path &&
            !requestPath.startsWith(path)
        ) {
            return false
        }

        return !(secure && !requestUrl.protocol.isSecure())
    }

    private fun Cookie.fillDefaults(requestUrl: Url): Cookie {
        var result = this

        if (result.path?.startsWith("/") != true) {
            result = result.copy(path = requestUrl.encodedPath)
        }

        if (result.domain.isNullOrBlank()) {
            result = result.copy(domain = requestUrl.host)
        }

        return result
    }

}