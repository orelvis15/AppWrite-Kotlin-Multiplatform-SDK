package io.appwrite.models.locale

import kotlinx.serialization.SerialName

/**
 * Locale codes list
 */
data class LocaleCodeList(
    /**
     * Total number of localeCodes documents that matched your query.
     */
    @SerialName("total")
    val total: Long,

    /**
     * List of localeCodes.
     */
    @SerialName("localeCodes")
    val localeCodes: List<LocaleCode>,

    ) {
    fun toMap(): Map<String, Any> = mapOf(
        "total" to total as Any,
        "localeCodes" to localeCodes.map { it.toMap() } as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = LocaleCodeList(
            total = map["total"].toString().toLong(),
            localeCodes = (map["localeCodes"] as List<Map<String, Any>>).map { LocaleCode.from(map = it) },
        )
    }
}