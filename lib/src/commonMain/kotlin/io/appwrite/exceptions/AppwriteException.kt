package io.appwrite.exceptions

import kotlinx.serialization.Serializable

@Serializable
class AppwriteException(
    override val message: String? = null,
    val code: Int? = null,
    val type: String? = null,
    val response: String? = null
) : Exception(message) {
    override fun toString(): String {
        return "Code: $code type: $type response: ${response.toString()}"
    }
}