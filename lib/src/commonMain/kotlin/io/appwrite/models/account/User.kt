package io.appwrite.models.account

import io.appwrite.models.common.Preferences
import io.appwrite.serialization.AnyValueSerializer
import io.appwrite.serialization.ListAnyValueSerializer
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * User
 */
@Serializable
data class User<T>(
    /**
     * User ID.
     */
    @SerialName("\$id")
    val id: String,

    /**
     * User creation date in ISO 8601 format.
     */
    @SerialName("\$createdAt")
    val createdAt: String,

    /**
     * User update date in ISO 8601 format.
     */
    @SerialName("\$updatedAt")
    val updatedAt: String,

    /**
     * User name.
     */
    @SerialName("name")
    val name: String,

    /**
     * Hashed user password.
     */
    @SerialName("password")
    var password: String? = null,

    /**
     * Password hashing algorithm.
     */
    @SerialName("hash")
    var hash: String? = null,

    /**
     * Password hashing algorithm configuration.
     */
    @Serializable(with = AnyValueSerializer::class)
    @SerialName("hashOptions")
    var hashOptions: Any? = null,

    /**
     * User registration date in ISO 8601 format.
     */
    @SerialName("registration")
    val registration: String,

    /**
     * User status. Pass `true` for enabled and `false` for disabled.
     */
    @SerialName("status")
    val status: Boolean,

    /**
     * Labels for the user.
     */
    @Serializable(with = ListAnyValueSerializer::class)
    @SerialName("labels")
    val labels: List<@Contextual  Any?>,

    /**
     * Password update time in ISO 8601 format.
     */
    @SerialName("passwordUpdate")
    val passwordUpdate: String,

    /**
     * User email address.
     */
    @SerialName("email")
    val email: String,

    /**
     * User phone number in E.164 format.
     */
    @SerialName("phone")
    val phone: String,

    /**
     * Email verification status.
     */
    @SerialName("emailVerification")
    val emailVerification: Boolean,

    /**
     * Phone verification status.
     */
    @SerialName("phoneVerification")
    val phoneVerification: Boolean,

    /**
     * User preferences as a key-value object
     */
    @SerialName("prefs")
    val prefs: Preferences<T>,

    /**
     * Most recent access date in ISO 8601 format. This attribute is only updated again after 24 hours.
     */
    @SerialName("accessedAt")
    val accessedAt: String,

    ) {
    fun toMap(): Map<String, Any> = mapOf(
        "\$id" to id as Any,
        "\$createdAt" to createdAt as Any,
        "\$updatedAt" to updatedAt as Any,
        "name" to name as Any,
        "password" to password as Any,
        "hash" to hash as Any,
        "hashOptions" to hashOptions as Any,
        "registration" to registration as Any,
        "status" to status as Any,
        "labels" to labels as Any,
        "passwordUpdate" to passwordUpdate as Any,
        "email" to email as Any,
        "phone" to phone as Any,
        "emailVerification" to emailVerification as Any,
        "phoneVerification" to phoneVerification as Any,
        "prefs" to prefs.toMap() as Any,
        "accessedAt" to accessedAt as Any,
    )

    companion object {
        operator fun invoke(
            id: String,
            createdAt: String,
            updatedAt: String,
            name: String,
            password: String?,
            hash: String?,
            hashOptions: Any,
            registration: String,
            status: Boolean,
            labels: List<Any?>,
            passwordUpdate: String,
            email: String,
            phone: String,
            emailVerification: Boolean,
            phoneVerification: Boolean,
            prefs: Preferences<Map<String, Any>>,
            accessedAt: String,
        ) = User(
            id,
            createdAt,
            updatedAt,
            name,
            password,
            hash,
            hashOptions,
            registration,
            status,
            labels,
            passwordUpdate,
            email,
            phone,
            emailVerification,
            phoneVerification,
            prefs,
            accessedAt,
        )

        @Suppress("UNCHECKED_CAST")
        fun <T> from(
            map: Map<String, Any>
        ) = User<T>(
            id = map["\$id"] as String,
            createdAt = map["\$createdAt"] as String,
            updatedAt = map["\$updatedAt"] as String,
            name = map["name"] as String,
            password = map["password"] as? String?,
            hash = map["hash"] as? String?,
            hashOptions = map["hashOptions"],
            registration = map["registration"] as String,
            status = map["status"].toString().toBoolean(),
            labels = map["labels"] as List<Any>,
            passwordUpdate = map["passwordUpdate"] as String,
            email = map["email"] as String,
            phone = map["phone"] as String,
            emailVerification = map["emailVerification"].toString().toBoolean(),
            phoneVerification = map["phoneVerification"].toString().toBoolean(),
            prefs = Preferences.from(map = map["prefs"] as Map<String, Any>),
            accessedAt = map["accessedAt"] as String,
        )
    }
}