package io.appwrite.models.database

import kotlinx.serialization.SerialName

/**
 * Document
 */
data class Document<T>(
    /**
     * Document ID.
     */
    @SerialName("\$id")
    val id: String,

    /**
     * Collection ID.
     */
    @SerialName("\$collectionId")
    val collectionId: String,

    /**
     * Database ID.
     */
    @SerialName("\$databaseId")
    val databaseId: String,

    /**
     * Document creation date in ISO 8601 format.
     */
    @SerialName("\$createdAt")
    val createdAt: String,

    /**
     * Document update date in ISO 8601 format.
     */
    @SerialName("\$updatedAt")
    val updatedAt: String,

    /**
     * Document permissions. [Learn more about permissions](/docs/permissions).
     */
    @SerialName("\$permissions")
    val permissions: List<Any>,

    /**
     * Additional properties
     */
    @SerialName("data")
    val data: T
) {
    fun toMap(): Map<String, Any> = mapOf(
        "\$id" to id as Any,
        "\$collectionId" to collectionId as Any,
        "\$databaseId" to databaseId as Any,
        "\$createdAt" to createdAt as Any,
        "\$updatedAt" to updatedAt as Any,
        "\$permissions" to permissions as Any,
        "data" to data as Map<String, Any>
    )

    companion object {
        operator fun invoke(
            id: String,
            collectionId: String,
            databaseId: String,
            createdAt: String,
            updatedAt: String,
            permissions: List<Any>,
            data: Map<String, Any>
        ) = Document(
            id,
            collectionId,
            databaseId,
            createdAt,
            updatedAt,
            permissions,
            data
        )

        @Suppress("UNCHECKED_CAST")
        fun <T> from(
            map: Map<String, Any>
        ) = Document(
            id = map["\$id"] as String,
            collectionId = map["\$collectionId"] as String,
            databaseId = map["\$databaseId"] as String,
            createdAt = map["\$createdAt"] as String,
            updatedAt = map["\$updatedAt"] as String,
            permissions = map["\$permissions"] as List<Any>,
            data = map as T
        )
    }
}