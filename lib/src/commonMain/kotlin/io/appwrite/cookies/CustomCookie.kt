package io.appwrite.cookies

import io.ktor.http.*
import kotlinx.serialization.Serializable

@Serializable
data class CustomCookie(
    val name: String,
    val value: String,
    val encoding: CookieEncoding = CookieEncoding.URI_ENCODING,
    val maxAge: Int = 0,
    val expires: Long? = null,
    val domain: String? = null,
    val path: String? = null,
    val secure: Boolean = false,
    val httpOnly: Boolean = false,
    val extensions: Map<String, String?> = emptyMap()
) {
    fun toKtorCookie(): Cookie {
        return Cookie(
            this.name,
            this.value,
            this.encoding,
            this.maxAge,
            null,
            this.domain,
            this.path,
            this.secure,
            this.httpOnly,
            this.extensions
        )
    }
}

fun Cookie.toCustomCookies(): CustomCookie {
    return CustomCookie(
        this.name,
        this.value,
        this.encoding,
        this.maxAge,
        null,
        this.domain,
        this.path,
        this.secure,
        this.httpOnly,
        this.extensions
    )
}