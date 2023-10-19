package io.appwrite.models.locale

import kotlinx.serialization.SerialName

/**
 * Continents List
 */
data class ContinentList(
    /**
     * Total number of continents documents that matched your query.
     */
    @SerialName("total")
    val total: Long,

    /**
     * List of continents.
     */
    @SerialName("continents")
    val continents: List<Continent>,

    ) {
    fun toMap(): Map<String, Any> = mapOf(
        "total" to total as Any,
        "continents" to continents.map { it.toMap() } as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ContinentList(
            total = map["total"].toString().toLong(),
            continents = (map["continents"] as List<Map<String, Any>>).map { Continent.from(map = it) },
        )
    }
}