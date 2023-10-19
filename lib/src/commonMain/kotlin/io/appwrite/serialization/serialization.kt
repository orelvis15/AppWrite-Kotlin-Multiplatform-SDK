package io.appwrite.serialization

import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*

object AnyValueSerializer : KSerializer<Any?> {
    private val delegateSerializer = JsonPrimitive.serializer()
    override val descriptor = delegateSerializer.descriptor
    override fun serialize(encoder: Encoder, value: Any?) {
        encoder.encodeSerializableValue(delegateSerializer, value.toJsonPrimitive())
    }
    override fun deserialize(decoder: Decoder): Any? {
        val jsonPrimitive = decoder.decodeSerializableValue(delegateSerializer)
        return jsonPrimitive.toAnyValue()
    }
}

object ListAnyValueSerializer : KSerializer<List<Any?>> {
    private val delegateSerializer = ListSerializer(AnyValueSerializer)

    override val descriptor = delegateSerializer.descriptor

    override fun serialize(encoder: Encoder, value: List<Any?>) {
        encoder.encodeSerializableValue(delegateSerializer, value)
    }

    override fun deserialize(decoder: Decoder): List<Any?> {
        return decoder.decodeSerializableValue(delegateSerializer)
    }
}

private fun Any?.toJsonPrimitive(): JsonPrimitive {
    return when (this) {
        null -> JsonNull
        is JsonPrimitive -> this
        is Boolean -> JsonPrimitive(this)
        is Number -> JsonPrimitive(this)
        is String -> JsonPrimitive(this)
        else -> throw Exception("Error :${this::class}")
    }
}

private fun JsonPrimitive.toAnyValue():Any?{
    val content = this.content
    if (this.isString){
        return content
    }
    if (content.equals("null", ignoreCase = true)){
        return null
    }
    if (content.equals("true", ignoreCase = true)){
        return true
    }
    if (content.equals("false", ignoreCase = true)){
        return false
    }
    val intValue = content.toIntOrNull()
    if (intValue!=null){
        return intValue
    }
    val longValue = content.toLongOrNull()
    if (longValue!=null){
        return longValue
    }
    val doubleValue = content.toDoubleOrNull()
    if (doubleValue!=null){
        return doubleValue
    }
    throw Exception("Error ：${content}")
}

fun Any?.toJsonElement(): JsonElement {
    return when (this) {
        is Number -> JsonPrimitive(this)
        is Boolean -> JsonPrimitive(this)
        is String -> JsonPrimitive(this)
        is Array<*> -> this.toJsonArray()
        is List<*> -> this.toJsonArray()
        is Map<*, *> -> this.toJsonObject()
        is JsonElement -> this
        else -> JsonNull
    }
}

fun Array<*>.toJsonArray(): JsonArray {
    val array = mutableListOf<JsonElement>()
    this.forEach { array.add(it.toJsonElement()) }
    return JsonArray(array)
}

fun List<*>.toJsonArray(): JsonArray {
    val array = mutableListOf<JsonElement>()
    this.forEach { array.add(it.toJsonElement()) }
    return JsonArray(array)
}

fun Map<*, *>.toJsonObject(): JsonObject {
    val map = mutableMapOf<String, JsonElement>()
    this.forEach {
        if (it.key is String) {
            map[it.key as String] = it.value.toJsonElement()
        }
    }
    return JsonObject(map)
}