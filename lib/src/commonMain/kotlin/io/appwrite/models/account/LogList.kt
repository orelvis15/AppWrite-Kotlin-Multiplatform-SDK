package io.appwrite.models.account

import kotlinx.serialization.SerialName

/**
 * Logs List
 */
data class LogList(
    /**
     * Total number of logs documents that matched your query.
     */
    @SerialName("total")
    val total: Long,

    /**
     * List of logs.
     */
    @SerialName("logs")
    val logs: List<Log>,

    ) {
    fun toMap(): Map<String, Any> = mapOf(
        "total" to total as Any,
        "logs" to logs.map { it.toMap() } as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = LogList(
            total = (map["total"] as Number).toLong(),
            logs = (map["logs"] as List<Map<String, Any>>).map { Log.from(map = it) },
        )
    }
}