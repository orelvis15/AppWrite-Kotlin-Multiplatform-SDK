package io.appwrite.models.storage

data class UploadProgress(
    val id: String,
    val progress: Double,
    val sizeUploaded: Long,
    val chunksTotal: Int,
    val chunksUploaded: Int
)