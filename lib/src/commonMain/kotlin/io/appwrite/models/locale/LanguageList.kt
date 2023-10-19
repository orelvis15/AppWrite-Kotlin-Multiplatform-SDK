package io.appwrite.models.locale

import kotlinx.serialization.SerialName

/**
 * Languages List
 */
data class LanguageList(
    /**
     * Total number of languages documents that matched your query.
     */
    @SerialName("total")
    val total: Long,

    /**
     * List of languages.
     */
    @SerialName("languages")
    val languages: List<Language>,

    ) {
    fun toMap(): Map<String, Any> = mapOf(
        "total" to total as Any,
        "languages" to languages.map { it.toMap() } as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = LanguageList(
            total = map["total"].toString().toLong(),
            languages = (map["languages"] as List<Map<String, Any>>).map { Language.from(map = it) },
        )
    }
}