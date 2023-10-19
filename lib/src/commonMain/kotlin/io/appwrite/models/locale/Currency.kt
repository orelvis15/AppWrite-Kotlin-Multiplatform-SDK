package io.appwrite.models.locale

import kotlinx.serialization.SerialName

/**
 * Currency
 */
data class Currency(
    /**
     * Currency symbol.
     */
    @SerialName("symbol")
    val symbol: String,

    /**
     * Currency name.
     */
    @SerialName("name")
    val name: String,

    /**
     * Currency native symbol.
     */
    @SerialName("symbolNative")
    val symbolNative: String,

    /**
     * Number of decimal digits.
     */
    @SerialName("decimalDigits")
    val decimalDigits: Long,

    /**
     * Currency digit rounding.
     */
    @SerialName("rounding")
    val rounding: Double,

    /**
     * Currency code in [ISO 4217-1](http://en.wikipedia.org/wiki/ISO_4217) three-character format.
     */
    @SerialName("code")
    val code: String,

    /**
     * Currency plural name
     */
    @SerialName("namePlural")
    val namePlural: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "symbol" to symbol as Any,
        "name" to name as Any,
        "symbolNative" to symbolNative as Any,
        "decimalDigits" to decimalDigits as Any,
        "rounding" to rounding as Any,
        "code" to code as Any,
        "namePlural" to namePlural as Any,
    )

    companion object {

        fun from(
            map: Map<String, Any>,
        ) = Currency(
            symbol = map["symbol"] as String,
            name = map["name"] as String,
            symbolNative = map["symbolNative"] as String,
            decimalDigits = map["decimalDigits"].toString().toLong(),
            rounding = map["rounding"].toString().toDouble(),
            code = map["code"] as String,
            namePlural = map["namePlural"] as String,
        )
    }
}