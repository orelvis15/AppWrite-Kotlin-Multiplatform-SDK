package io.appwrite.models.locale

import kotlinx.serialization.SerialName

/**
 * Phones List
 */
data class PhoneList(
    /**
     * Total number of phones documents that matched your query.
     */
    @SerialName("total")
    val total: Long,

    /**
     * List of phones.
     */
    @SerialName("phones")
    val phones: List<Phone>,

    ) {
    fun toMap(): Map<String, Any> = mapOf(
        "total" to total as Any,
        "phones" to phones.map { it.toMap() } as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = PhoneList(
            total = map["total"].toString().toLong(),
            phones = (map["phones"] as List<Map<String, Any>>).map { Phone.from(map = it) },
        )
    }
}