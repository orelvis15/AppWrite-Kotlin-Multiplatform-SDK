package io.appwrite.models.function

import kotlinx.serialization.SerialName

/**
 * Headers
 */
data class Headers(
    /**
     * Header name.
     */
    @SerialName("name")
    val name: String,

    /**
     * Header value.
     */
    @SerialName("value")
    val value: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "name" to name as Any,
        "value" to value as Any,
    )

    companion object {

        fun from(
            map: Map<String, Any>,
        ) = Headers(
            name = map["name"] as String,
            value = map["value"] as String,
        )
    }
}