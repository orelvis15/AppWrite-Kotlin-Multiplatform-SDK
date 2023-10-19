package io.appwrite.extensions

import io.ktor.client.call.*
import io.ktor.client.statement.*
import kotlinx.serialization.json.Json
import kotlin.jvm.java

suspend fun <T> HttpResponse.parseResponse(responseType: Class<T>?, converter: ((Any) -> T)?): T {
    val json = Json { ignoreUnknownKeys = true }

    when {
        responseType == ByteArray::class.java -> {
            return converter?.invoke(body()) ?: body<ByteArray>() as T
        }

        else -> {
            if (bodyAsText().isEmpty()) return "" as T

            val result = json.parseToJsonElement(bodyAsText())
            return converter?.invoke(result.jsonElementToMap()) ?: result as T
        }
    }
}