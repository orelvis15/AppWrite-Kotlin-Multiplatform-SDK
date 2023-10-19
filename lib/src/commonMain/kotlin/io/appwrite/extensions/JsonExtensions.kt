package io.appwrite.extensions

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

fun JsonElement.jsonElementToMap(): Map<String, Any> {
    val map = mutableMapOf<String, Any>()
    if (this is JsonObject) {
        for ((key, value) in this.entries) {
            map[key] = when (value) {
                is JsonPrimitive -> value.content
                is JsonObject -> value.jsonElementToMap()
                is JsonArray -> value.map { it.jsonElementToMap() }
                else -> value
            }
        }
    }

    return map
}