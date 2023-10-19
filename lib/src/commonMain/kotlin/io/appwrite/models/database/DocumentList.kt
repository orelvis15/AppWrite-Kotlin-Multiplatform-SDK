package io.appwrite.models.database

import kotlinx.serialization.SerialName

/**
 * Documents List
 */
data class DocumentList<T>(
    /**
     * Total number of documents that matched your query.
     */
    @SerialName("total")
    val total: Long,

    /**
     * List of documents.
     */
    @SerialName("documents")
    val documents: List<Document<T>>,

    ) {
    fun toMap(): Map<String, Any> = mapOf(
        "total" to total as Any,
        "documents" to documents.map { it.toMap() } as Any,
    )

    companion object {
        operator fun invoke(
            total: Long,
            documents: List<Document<Map<String, Any>>>,
        ) = DocumentList(
            total,
            documents,
        )

        @Suppress("UNCHECKED_CAST")
        fun <T> from(
            map: Map<String, Any>
        ) = DocumentList<T>(
            total = (map["total"] as Number).toLong(),
            documents = (map["documents"] as List<Map<String, Any>>).map { Document.from(map = it) },
        )
    }
}