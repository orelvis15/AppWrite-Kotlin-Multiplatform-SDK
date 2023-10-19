package io.appwrite.models.account

import kotlinx.serialization.SerialName

/**
 * JWT
 */
data class Jwt(
    /**
     * JWT encoded string.
     */
    @SerialName("jwt")
    val jwt: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "jwt" to jwt as Any,
    )

    companion object {

        fun from(
            map: Map<String, Any>,
        ) = Jwt(
            jwt = map["jwt"] as String,
        )
    }
}