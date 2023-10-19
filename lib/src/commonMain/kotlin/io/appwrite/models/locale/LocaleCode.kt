package io.appwrite.models.locale

import kotlinx.serialization.SerialName

/**
 * LocaleCode
 */
data class LocaleCode(
    /**
     * Locale codes in [ISO 639-1](https://en.wikipedia.org/wiki/List_of_ISO_639-1_codes)
     */
    @SerialName("code")
    val code: String,

    /**
     * Locale name
     */
    @SerialName("name")
    val name: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "code" to code as Any,
        "name" to name as Any,
    )

    companion object {

        fun from(
            map: Map<String, Any>,
        ) = LocaleCode(
            code = map["code"] as String,
            name = map["name"] as String,
        )
    }
}