package io.appwrite.models.storage

import io.appwrite.serialization.ListAnyValueSerializer
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * File
 */
@Serializable
data class File(
    /**
     * File ID.
     */
    @SerialName("\$id")
    val id: String,

    /**
     * Bucket ID.
     */
    @SerialName("bucketId")
    val bucketId: String,

    /**
     * File creation date in ISO 8601 format.
     */
    @SerialName("\$createdAt")
    val createdAt: String,

    /**
     * File update date in ISO 8601 format.
     */
    @SerialName("\$updatedAt")
    val updatedAt: String,

    /**
     * File permissions. [Learn more about permissions](/docs/permissions).
     */
    @Serializable(with = ListAnyValueSerializer::class)
    @SerialName("\$permissions")
    val permissions: List<@Contextual Any>,

    /**
     * File name.
     */
    @SerialName("name")
    val name: String,

    /**
     * File MD5 signature.
     */
    @SerialName("signature")
    val signature: String,

    /**
     * File mime type.
     */
    @SerialName("mimeType")
    val mimeType: String,

    /**
     * File original size in bytes.
     */
    @SerialName("sizeOriginal")
    val sizeOriginal: Long,

    /**
     * Total number of chunks available
     */
    @SerialName("chunksTotal")
    val chunksTotal: Long,

    /**
     * Total number of chunks uploaded
     */
    @SerialName("chunksUploaded")
    val chunksUploaded: Long,

    ) {
    fun toMap(): Map<String, Any> = mapOf(
        "\$id" to id as Any,
        "bucketId" to bucketId as Any,
        "\$createdAt" to createdAt as Any,
        "\$updatedAt" to updatedAt as Any,
        "\$permissions" to permissions as Any,
        "name" to name as Any,
        "signature" to signature as Any,
        "mimeType" to mimeType as Any,
        "sizeOriginal" to sizeOriginal as Any,
        "chunksTotal" to chunksTotal as Any,
        "chunksUploaded" to chunksUploaded as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = File(
            id = map["\$id"].toString(),
            bucketId = map["bucketId"].toString(),
            createdAt = map["\$createdAt"].toString(),
            updatedAt = map["\$updatedAt"].toString(),
            permissions = map["\$permissions"] as List<Any>,
            name = map["name"].toString(),
            signature = map["signature"].toString(),
            mimeType = map["mimeType"].toString(),
            sizeOriginal = map["sizeOriginal"].toString().toLong(),
            chunksTotal = map["chunksTotal"].toString().toLong(),
            chunksUploaded = map["chunksUploaded"].toString().toLong(),
        )
    }
}