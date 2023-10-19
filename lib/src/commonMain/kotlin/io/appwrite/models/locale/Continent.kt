package io.appwrite.models.locale

import kotlinx.serialization.SerialName

/**
 * Continent
 */
data class Continent(
    /**
     * Continent name.
     */
    @SerialName("name")
    val name: String,

    /**
     * Continent two letter code.
     */
    @SerialName("code")
    val code: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "name" to name as Any,
        "code" to code as Any,
    )

    companion object {

        fun from(
            map: Map<String, Any>,
        ) = Continent(
            name = map["name"] as String,
            code = map["code"] as String,
        )
    }
}