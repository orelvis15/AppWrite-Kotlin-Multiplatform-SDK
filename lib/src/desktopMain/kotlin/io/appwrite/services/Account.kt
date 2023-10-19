package io.appwrite.services

import io.appwrite.Client
import io.appwrite.WebAuthServer
import io.appwrite.cookies.CustomCookiesStorage
import io.appwrite.exceptions.AppwriteException
import io.appwrite.models.*
import io.appwrite.models.account.*
import io.appwrite.models.common.Preferences
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.*
import java.awt.Desktop
import java.net.URI


/**
 * The Account service allows you to authenticate and manage a user account.
 **/
class Account(client: Client) : Service(client) {

    /**
     * Get Account
     *
     * Get the currently logged in user.
     *
     * @return [io.appwrite.models.account.User<T>]
     */
    @Throws(AppwriteException::class)
    suspend fun <T> get(): User<T> {
        val apiPath = "account"

        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )

        val converter: (Any) -> User<T> = {
            User.from(map = it as Map<String, Any>)
        }

        return client.call(
            method = "GET",
            path = apiPath,
            headers = apiHeaders,
            params = mapOf(),
            converter = converter
        )
    }

    /**
     * Get Account
     *
     * Get the currently logged in user.
     *
     * @return [io.appwrite.models.account.User<T>]
     */
    @Throws(AppwriteException::class)
    suspend fun getAccount(
    ): User<Map<String, Any>> = get()

    /**
     * Create Account
     *
     * Use this endpoint to allow a new user to register a new account in your project. After the user registration completes successfully, you can use the [/account/verfication](/docs/client/account#accountCreateVerification) route to start verifying the user email address. To allow the new user to login to their new account, you need to create a new [account session](/docs/client/account#accountCreateSession).
     *
     * @param userId Unique Id. Choose a custom ID or generate a random ID with `ID.unique()`. Valid chars are a-z, A-Z, 0-9, period, hyphen, and underscore. Can't start with a special char. Max length is 36 chars.
     * @param email User email.
     * @param password New user password. Must be at least 8 chars.
     * @param name User name. Max length: 128 chars.
     * @return [io.appwrite.models.account.User<T>]
     */
    @JvmOverloads
    @Throws(AppwriteException::class)
    suspend fun <T> create(
        userId: String,
        email: String,
        password: String,
        name: String? = null,
    ): User<T> {
        val apiPath = "account"

        val apiParams = mutableMapOf(
            "userId" to userId,
            "email" to email,
            "password" to password,
            "name" to name,
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )

        val converter: (Any) -> User<T> = {
            User.from(map = it as Map<String, Any>)
        }

        return client.call(
            method = "POST",
            path = apiPath,
            headers = apiHeaders,
            params = apiParams,
            converter = converter
        )
    }

    /**
     * Create Account
     *
     * Use this endpoint to allow a new user to register a new account in your project. After the user registration completes successfully, you can use the [/account/verfication](/docs/client/account#accountCreateVerification) route to start verifying the user email address. To allow the new user to login to their new account, you need to create a new [account session](/docs/client/account#accountCreateSession).
     *
     * @param userId Unique Id. Choose a custom ID or generate a random ID with `ID.unique()`. Valid chars are a-z, A-Z, 0-9, period, hyphen, and underscore. Can't start with a special char. Max length is 36 chars.
     * @param email User email.
     * @param password New user password. Must be at least 8 chars.
     * @param name User name. Max length: 128 chars.
     * @return [io.appwrite.models.account.User<T>]
     */
    @JvmOverloads
    @Throws(AppwriteException::class)
    suspend fun createAccount(
        userId: String,
        email: String,
        password: String,
        name: String? = null,
    ): User<Map<String, Any>> = create(
        userId,
        email,
        password,
        name,
    )

    /**
     * Update Email
     *
     * Update currently logged in user account email address. After changing user address, the user confirmation status will get reset. A new confirmation email is not sent automatically however you can use the send confirmation email endpoint again to send the confirmation email. For security measures, user password is required to complete this request.This endpoint can also be used to convert an anonymous account to a normal one, by passing an email address and a new password.
     *
     * @param email User email.
     * @param password User password. Must be at least 8 chars.
     * @return [io.appwrite.models.account.User<T>]
     */
    @Throws(AppwriteException::class)
    suspend fun <T> updateEmail(
        email: String,
        password: String
    ): User<T> {
        val apiPath = "account/email"

        val apiParams = mutableMapOf<String, Any?>(
            "email" to email,
            "password" to password,
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )

        val converter: (Any) -> User<T> = {
            User.from(map = it as Map<String, Any>)
        }

        return client.call(
            method = "PATCH",
            path = apiPath,
            headers = apiHeaders,
            params = apiParams,
            converter = converter
        )
    }

    /**
     * Update Email
     *
     * Update currently logged in user account email address. After changing user address, the user confirmation status will get reset. A new confirmation email is not sent automatically however you can use the send confirmation email endpoint again to send the confirmation email. For security measures, user password is required to complete this request.This endpoint can also be used to convert an anonymous account to a normal one, by passing an email address and a new password.
     *
     * @param email User email.
     * @param password User password. Must be at least 8 chars.
     * @return [io.appwrite.models.account.User<T>]
     */
    @Throws(AppwriteException::class)
    suspend fun updateEmailAccount(
        email: String,
        password: String,
    ): User<Map<String, Any>> = updateEmail(
        email,
        password,
    )

    /**
     * List Identities
     *
     * Get the list of identities for the currently logged in user.
     *
     * @param queries Array of query strings generated using the Query class provided by the SDK. [Learn more about queries](https://appwrite.io/docs/queries). Maximum of 100 queries are allowed, each 4096 characters long. You may filter on the following attributes: userId, provider, providerUid, providerEmail, providerAccessTokenExpiry
     * @return [io.appwrite.models.IdentityList]
     */
    @JvmOverloads
    suspend fun listIdentities(
        queries: String? = null,
    ): IdentityList {
        val apiPath = "account/identities"

        val apiParams = mutableMapOf<String, Any?>(
            "queries" to queries,
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )

        val converter: (Any) -> IdentityList = {
            IdentityList.from(map = it as Map<String, Any>)
        }

        return client.call(
            method = "GET",
            path = apiPath,
            headers = apiHeaders,
            params = apiParams,
            converter = converter
        )
    }

    /**
     * Delete Identity
     *
     * Delete an identity by its unique ID.
     *
     * @param identityId Identity ID.
     * @return [Any]
     */
    suspend fun deleteIdentity(
        identityId: String,
    ): Any {
        val apiPath = "account/identities/{identityId}"
            .replace("{identityId}", identityId)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )
        return client.call(
            "DELETE",
            apiPath,
            apiHeaders,
            apiParams,
        )
    }

    /**
     * Create JWT
     *
     * Use this endpoint to create a JSON Web Token. You can use the resulting JWT to authenticate on behalf of the current user when working with the Appwrite server-side API and SDKs. The JWT secret is valid for 15 minutes from its creation and will be invalid if the user will logout in that time frame.
     *
     * @return [io.appwrite.models.Jwt]
     */
    suspend fun createJWT(
    ): Jwt {
        val apiPath = "account/jwt"

        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )

        val converter: (Any) -> Jwt = {
            Jwt.from(map = it as Map<String, Any>)
        }

        return client.call(
            method = "POST",
            path = apiPath,
            headers = apiHeaders,
            params = mapOf(),
            converter = converter
        )
    }

    /**
     * List Logs
     *
     * Get the list of latest security activity logs for the currently logged in user. Each log returns user IP address, location and date and time of log.
     *
     * @param queries Array of query strings generated using the Query class provided by the SDK. [Learn more about queries](https://appwrite.io/docs/queries). Only supported methods are limit and offset
     * @return [io.appwrite.models.LogList]
     */
    @JvmOverloads
    suspend fun listLogs(
        queries: List<String>? = null,
    ): LogList {
        val apiPath = "account/logs"

        val apiParams = mutableMapOf<String, Any?>(
            "queries" to queries,
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )

        val converter: (Any) -> LogList = {
            LogList.from(map = it as Map<String, Any>)
        }

        return client.call(
            method = "GET",
            path = apiPath,
            headers = apiHeaders,
            params = apiParams,
            converter = converter
        )
    }

    /**
     * Update Name
     *
     * Update currently logged in user account name.
     *
     * @param name User name. Max length: 128 chars.
     * @return [io.appwrite.models.account.User<T>]
     */
    suspend fun <T> updateName(
        name: String,
    ): User<T> {
        val apiPath = "account/name"

        val apiParams = mutableMapOf<String, Any?>(
            "name" to name,
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )

        val converter: (Any) -> User<T> = {
            User.from(map = it as Map<String, Any>)
        }

        return client.call(
            method = "PATCH",
            path = apiPath,
            headers = apiHeaders,
            params = apiParams,
            converter = converter
        )
    }

    /**
     * Update Name
     *
     * Update currently logged in user account name.
     *
     * @param name User name. Max length: 128 chars.
     * @return [io.appwrite.models.account.User<T>]
     */
    @Throws(AppwriteException::class)
    suspend fun updateNameAccount(
        name: String,
    ): User<Map<String, Any>> = updateName(
        name
    )

    /**
     * Update Password
     *
     * Update currently logged in user password. For validation, user is required to pass in the new password, and the old password. For users created with OAuth, Team Invites and Magic URL, oldPassword is optional.
     *
     * @param password New user password. Must be at least 8 chars.
     * @param oldPassword Current user password. Must be at least 8 chars.
     * @return [io.appwrite.models.account.User<T>]
     */
    @JvmOverloads
    suspend fun <T> updatePassword(
        password: String,
        oldPassword: String? = null,
    ): User<T> {
        val apiPath = "account/password"

        val apiParams = mutableMapOf<String, Any?>(
            "password" to password,
            "oldPassword" to oldPassword,
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )

        val converter: (Any) -> User<T> = {
            User.from(map = it as Map<String, Any>)
        }

        return client.call(
            method = "PATCH",
            path = apiPath,
            headers = apiHeaders,
            params = apiParams,
            converter = converter
        )
    }

    /**
     * Update Password
     *
     * Update currently logged in user password. For validation, user is required to pass in the new password, and the old password. For users created with OAuth, Team Invites and Magic URL, oldPassword is optional.
     *
     * @param password New user password. Must be at least 8 chars.
     * @param oldPassword Current user password. Must be at least 8 chars.
     * @return [io.appwrite.models.account.User<T>]
     */
    @JvmOverloads
    @Throws(AppwriteException::class)
    suspend fun updatePasswordAccount(
        password: String,
        oldPassword: String? = null,
    ): User<Map<String, Any>> = updatePassword(
        password,
        oldPassword
    )

    /**
     * Update Phone
     *
     * Update the currently logged in user&#039;s phone number. After updating the phone number, the phone verification status will be reset. A confirmation SMS is not sent automatically, however you can use the [POST /account/verification/phone](/docs/client/account#accountCreatePhoneVerification) endpoint to send a confirmation SMS.
     *
     * @param phone Phone number. Format this number with a leading '+' and a country code, e.g., +16175551212.
     * @param password User password. Must be at least 8 chars.
     * @return [io.appwrite.models.account.User<T>]
     */
    suspend fun <T> updatePhone(
        phone: String,
        password: String
    ): User<T> {
        val apiPath = "account/phone"

        val apiParams = mutableMapOf<String, Any?>(
            "phone" to phone,
            "password" to password,
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )

        val converter: (Any) -> User<T> = {
            User.from(map = it as Map<String, Any>)
        }

        return client.call(
            method = "PATCH",
            path = apiPath,
            headers = apiHeaders,
            params = apiParams,
            converter = converter
        )
    }

    /**
     * Update Phone
     *
     * Update the currently logged in user&#039;s phone number. After updating the phone number, the phone verification status will be reset. A confirmation SMS is not sent automatically, however you can use the [POST /account/verification/phone](/docs/client/account#accountCreatePhoneVerification) endpoint to send a confirmation SMS.
     *
     * @param phone Phone number. Format this number with a leading '+' and a country code, e.g., +16175551212.
     * @param password User password. Must be at least 8 chars.
     * @return [io.appwrite.models.account.User<T>]
     */
    @Throws(AppwriteException::class)
    suspend fun updatePhoneAccount(
        phone: String,
        password: String,
    ): User<Map<String, Any>> = updatePhone(
        phone,
        password
    )

    /**
     * Get Account Preferences
     *
     * Get the preferences as a key-value object for the currently logged in user.
     *
     * @return [io.appwrite.models.common.Preferences<T>]
     */
    suspend fun <T> getPrefs(): Preferences<T> {
        val apiPath = "account/prefs"

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )

        val converter: (Any) -> Preferences<T> = {
            Preferences.from(map = it as Map<String, Any>)
        }

        return client.call(
            method = "GET",
            path = apiPath,
            headers = apiHeaders,
            params = apiParams,
            converter = converter
        )
    }

    /**
     * Get Account Preferences
     *
     * Get the preferences as a key-value object for the currently logged in user.
     *
     * @return [io.appwrite.models.common.Preferences<T>]
     */
    @Throws(AppwriteException::class)
    suspend fun getPrefsAccount(
    ): Preferences<Map<String, Any>> = getPrefs()

    /**
     * Update Preferences
     *
     * Update currently logged in user account preferences. The object you pass is stored as is, and replaces any previous value. The maximum allowed prefs size is 64kB and throws error if exceeded.
     *
     * @param prefs Prefs key-value JSON object.
     * @return [io.appwrite.models.account.User<T>]
     */
    suspend fun <T> updatePrefs(
        prefs: Any,
    ): User<T> {
        val apiPath = "account/prefs"

        val apiParams = mutableMapOf<String, Any?>(
            "prefs" to prefs,
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )

        val converter: (Any) -> User<T> = {
            User.from(map = it as Map<String, Any>)
        }

        return client.call(
            method = "PATCH",
            path = apiPath,
            headers = apiHeaders,
            params = apiParams,
            converter = converter
        )
    }

    /**
     * Update Preferences
     *
     * Update currently logged in user account preferences. The object you pass is stored as is, and replaces any previous value. The maximum allowed prefs size is 64kB and throws error if exceeded.
     *
     * @param prefs Prefs key-value JSON object.
     * @return [io.appwrite.models.account.User<T>]
     */
    @Throws(AppwriteException::class)
    suspend fun updatePrefsAccount(
        prefs: Any,
    ): User<Map<String, Any>> = updatePrefs(
        prefs
    )

    /**
     * Create Password Recovery
     *
     * Sends the user an email with a temporary secret key for password reset. When the user clicks the confirmation link he is redirected back to your app password reset URL with the secret key and email address values attached to the URL query string. Use the query string params to submit a request to the [PUT /account/recovery](/docs/client/account#accountUpdateRecovery) endpoint to complete the process. The verification link sent to the user&#039;s email address is valid for 1 hour.
     *
     * @param email User email.
     * @param url URL to redirect the user back to your app from the recovery email. Only URLs from hostnames in your project platform list are allowed. This requirement helps to prevent an [open redirect](https://cheatsheetseries.owasp.org/cheatsheets/Unvalidated_Redirects_and_Forwards_Cheat_Sheet.html) attack against your project API.
     * @return [io.appwrite.models.Token]
     */
    suspend fun createRecovery(
        email: String,
        url: String,
    ): Token {
        val apiPath = "account/recovery"

        val apiParams = mutableMapOf<String, Any?>(
            "email" to email,
            "url" to url,
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )

        val converter: (Any) -> Token = {
            Token.from(map = it as Map<String, Any>)
        }

        return client.call(
            method = "POST",
            path = apiPath,
            headers = apiHeaders,
            params = apiParams,
            converter = converter
        )
    }

    /**
     * Create Password Recovery (confirmation)
     *
     * Use this endpoint to complete the user account password reset. Both the **userId** and **secret** arguments will be passed as query parameters to the redirect URL you have provided when sending your request to the [POST /account/recovery](/docs/client/account#accountCreateRecovery) endpoint.Please note that in order to avoid a [Redirect Attack](https://github.com/OWASP/CheatSheetSeries/blob/master/cheatsheets/Unvalidated_Redirects_and_Forwards_Cheat_Sheet.md) the only valid redirect URLs are the ones from domains you have set when adding your platforms in the console interface.
     *
     * @param userId User ID.
     * @param secret Valid reset token.
     * @param password New user password. Must be at least 8 chars.
     * @param passwordAgain Repeat new user password. Must be at least 8 chars.
     * @return [io.appwrite.models.Token]
     */
    suspend fun updateRecovery(
        userId: String,
        secret: String,
        password: String,
        passwordAgain: String,
    ): Token {
        val apiPath = "account/recovery"

        val apiParams = mutableMapOf<String, Any?>(
            "userId" to userId,
            "secret" to secret,
            "password" to password,
            "passwordAgain" to passwordAgain,
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )

        val converter: (Any) -> Token = {
            Token.from(map = it as Map<String, Any>)
        }

        return client.call(
            method = "PUT",
            path = apiPath,
            headers = apiHeaders,
            params = apiParams,
            converter = converter
        )
    }

    /**
     * List Sessions
     *
     * Get the list of active sessions across different devices for the currently logged in user.
     *
     * @return [io.appwrite.models.SessionList]
     */
    suspend fun listSessions(
    ): SessionList {
        val apiPath = "account/sessions"

        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )

        val converter: (Any) -> SessionList = {
            SessionList.from(map = it as Map<String, Any>)
        }

        return client.call(
            method = "GET",
            path = apiPath,
            headers = apiHeaders,
            params = mapOf(),
            converter = converter
        )
    }

    /**
     * Delete Sessions
     *
     * Delete all sessions from the user account and remove any sessions cookies from the end client.
     *
     * @return [Any]
     */
    suspend fun deleteSessions(
    ): Any {
        val apiPath = "account/sessions"

        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )

        return client.call(
            "DELETE",
            apiPath,
            apiHeaders,
            mapOf()
        )
    }

    /**
     * Create Anonymous Session
     *
     * Use this endpoint to allow a new user to register an anonymous account in your project. This route will also create a new session for the user. To allow the new user to convert an anonymous account to a normal account, you need to update its [email and password](/docs/client/account#accountUpdateEmail) or create an [OAuth2 session](/docs/client/account#accountCreateOAuth2Session).
     *
     * @return [io.appwrite.models.Session]
     */
    suspend fun createAnonymousSession(
    ): Session {
        val apiPath = "/account/sessions/anonymous"

        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )

        val converter: (Any) -> Session = {
            Session.from(map = it as Map<String, Any>)
        }

        return client.call(
            method = "POST",
            path = apiPath,
            headers = apiHeaders,
            params = mapOf(),
            converter = converter
        )
    }

    /**
     * Create Email Session
     *
     * Allow the user to login into their account by providing a valid email and password combination. This route will create a new session for the user.A user is limited to 10 active sessions at a time by default. [Learn more about session limits](/docs/authentication-security#limits).
     *
     * @param email User email.
     * @param password User password. Must be at least 8 chars.
     * @return [io.appwrite.models.Session]
     */
    @Throws(AppwriteException::class)
    suspend fun createEmailSession(
        email: String,
        password: String,
    ): Session {
        val apiPath = "account/sessions/email"

        val apiParams = mutableMapOf<String, Any?>(
            "email" to email,
            "password" to password,
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )

        val converter: (Any) -> Session = {
            Session.from(map = it as Map<String, Any>)
        }

        return client.call(
            method = "POST",
            path = apiPath,
            headers = apiHeaders,
            params = apiParams,
            converter = converter
        )
    }

    /**
     * Create Magic URL session
     *
     * Sends the user an email with a secret key for creating a session. If the provided user ID has not been registered, a new user will be created. When the user clicks the link in the email, the user is redirected back to the URL you provided with the secret key and userId values attached to the URL query string. Use the query string parameters to submit a request to the [PUT /account/sessions/magic-url](/docs/client/account#accountUpdateMagicURLSession) endpoint to complete the login process. The link sent to the user&#039;s email address is valid for 1 hour. If you are on a mobile device you can leave the URL parameter empty, so that the login completion will be handled by your Appwrite instance by default.A user is limited to 10 active sessions at a time by default. [Learn more about session limits](/docs/authentication-security#limits).
     *
     * @param userId Unique Id. Choose a custom ID or generate a random ID with `ID.unique()`. Valid chars are a-z, A-Z, 0-9, period, hyphen, and underscore. Can't start with a special char. Max length is 36 chars.
     * @param email User email.
     * @param url URL to redirect the user back to your app from the magic URL login. Only URLs from hostnames in your project platform list are allowed. This requirement helps to prevent an [open redirect](https://cheatsheetseries.owasp.org/cheatsheets/Unvalidated_Redirects_and_Forwards_Cheat_Sheet.html) attack against your project API.
     * @return [io.appwrite.models.Token]
     */
    @JvmOverloads
    suspend fun createMagicURLSession(
        userId: String,
        email: String,
        url: String? = null,
    ): Token {
        val apiPath = "account/sessions/magic-url"

        val apiParams = mutableMapOf<String, Any?>(
            "userId" to userId,
            "email" to email,
            "url" to url,
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )

        val converter: (Any) -> Token = {
            Token.from(map = it as Map<String, Any>)
        }

        return client.call(
            method = "POST",
            path = apiPath,
            headers = apiHeaders,
            params = apiParams,
            converter = converter
        )
    }

    /**
     * Create Magic URL session (confirmation)
     *
     * Use this endpoint to complete creating the session with the Magic URL. Both the **userId** and **secret** arguments will be passed as query parameters to the redirect URL you have provided when sending your request to the [POST /account/sessions/magic-url](/docs/client/account#accountCreateMagicURLSession) endpoint.Please note that in order to avoid a [Redirect Attack](https://github.com/OWASP/CheatSheetSeries/blob/master/cheatsheets/Unvalidated_Redirects_and_Forwards_Cheat_Sheet.md) the only valid redirect URLs are the ones from domains you have set when adding your platforms in the console interface.
     *
     * @param userId User ID.
     * @param secret Valid verification token.
     * @return [io.appwrite.models.Session]
     */
    suspend fun updateMagicURLSession(
        userId: String,
        secret: String,
    ): Session {
        val apiPath = "account/sessions/magic-url"

        val apiParams = mutableMapOf<String, Any?>(
            "userId" to userId,
            "secret" to secret,
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )

        val converter: (Any) -> Session = {
            Session.from(map = it as Map<String, Any>)
        }

        return client.call(
            method = "PUT",
            path = apiPath,
            headers = apiHeaders,
            params = apiParams,
            converter = converter
        )
    }

    /**
     * Create OAuth2 Session
     *
     * Allow the user to login to their account using the OAuth2 provider of their choice. Each OAuth2 provider should be enabled from the Appwrite console first. Use the success and failure arguments to provide a redirect URL&#039;s back to your app when login is completed.If there is already an active session, the new session will be attached to the logged-in account. If there are no active sessions, the server will attempt to look for a user with the same email address as the email received from the OAuth2 provider and attach the new session to the existing user. If no matching user is found - the server will create a new user.A user is limited to 10 active sessions at a time by default. [Learn more about session limits](/docs/authentication-security#limits).
     *
     * @param provider OAuth2 Provider. Currently, supported providers are: amazon, apple, auth0, authentik, autodesk, bitbucket, bitly, box, dailymotion, discord, disqus, dropbox, etsy, facebook, github, gitlab, google, linkedin, microsoft, notion, oidc, okta, paypal, paypalSandbox, podio, salesforce, slack, spotify, stripe, tradeshift, tradeshiftBox, twitch, wordpress, yahoo, yammer, yandex, zoom.
     * @param success URL to redirect back to your app after a successful login attempt.  Only URLs from hostnames in your project's platform list are allowed. This requirement helps to prevent an [open redirect](https://cheatsheetseries.owasp.org/cheatsheets/Unvalidated_Redirects_and_Forwards_Cheat_Sheet.html) attack against your project API.
     * @param failure URL to redirect back to your app after a failed login attempt.  Only URLs from hostnames in your project's platform list are allowed. This requirement helps to prevent an [open redirect](https://cheatsheetseries.owasp.org/cheatsheets/Unvalidated_Redirects_and_Forwards_Cheat_Sheet.html) attack against your project API.
     * @param scopes A list of custom OAuth2 scopes. Check each provider internal docs for a list of supported scopes. Maximum of 100 scopes are allowed, each 4096 characters long.
     * @param successHtmlResponse HTML template that will be shown to the user when the authentication process finishes in the browser and it is successful
     * @param failureHtmlResponse HTML template that will be shown to the user when the authentication process finishes in the browser and it fails
     */
    @JvmOverloads
    suspend fun createOAuth2Session(
        provider: String,
        success: String,
        failure: String,
        scopes: List<String>? = null,
        serverPort: Int,
        successHtmlResponse: ByteArray? = null,
        failureHtmlResponse: ByteArray? = null
    ): Boolean {
        val apiPath = "account/sessions/oauth2/$provider"

        val apiParams = mutableMapOf(
            "success" to success,
            "failure" to failure,
            "scopes" to scopes,
            "project" to client.getProject()
        )
        val apiQuery = mutableListOf<String>()
        apiParams.forEach {
            when (it.value) {
                null -> {
                    return@forEach
                }

                is List<*> -> {
                    apiQuery.add("${it.key}[]=${it.value.toString()}")
                }

                else -> {
                    apiQuery.add("${it.key}=${it.value.toString()}")
                }
            }
        }

        val apiUrl = URI("${client.endPoint}/${apiPath}?${apiQuery.joinToString("&")}")

        withContext(Dispatchers.IO) {
            Desktop.getDesktop().browse(apiUrl)
        }

        val codes = WebAuthServer.create(serverPort, success, failure, successHtmlResponse, failureHtmlResponse)

        codes?.let {
            val cookies = Cookie(
                name = codes["key"].toString(),
                value = codes["secret"].toString(),
                domain = URI(client.endPoint).host,
                httpOnly = true
            )
            CustomCookiesStorage.addCookie(Url(client.endPoint), cookies)
            return true
        }
        return false
    }

    /**
     * Create Phone session
     *
     * Sends the user an SMS with a secret key for creating a session. If the provided user ID has not be registered, a new user will be created. Use the returned user ID and secret and submit a request to the [PUT /account/sessions/phone](/docs/client/account#accountUpdatePhoneSession) endpoint to complete the login process. The secret sent to the user&#039;s phone is valid for 15 minutes.A user is limited to 10 active sessions at a time by default. [Learn more about session limits](/docs/authentication-security#limits).
     *
     * @param userId Unique Id. Choose a custom ID or generate a random ID with `ID.unique()`. Valid chars are a-z, A-Z, 0-9, period, hyphen, and underscore. Can't start with a special char. Max length is 36 chars.
     * @param phone Phone number. Format this number with a leading '+' and a country code, e.g., +16175551212.
     * @return [io.appwrite.models.Token]
     */
    suspend fun createPhoneSession(
        userId: String,
        phone: String,
    ): Token {
        val apiPath = "account/sessions/phone"

        val apiParams = mutableMapOf<String, Any?>(
            "userId" to userId,
            "phone" to phone,
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )

        val converter: (Any) -> Token = {
            Token.from(map = it as Map<String, Any>)
        }

        return client.call(
            method = "POST",
            path = apiPath,
            headers = apiHeaders,
            params = apiParams,
            converter = converter
        )
    }

    /**
     * Create Phone Session (confirmation)
     *
     * Use this endpoint to complete creating a session with SMS. Use the **userId** from the [createPhoneSession](/docs/client/account#accountCreatePhoneSession) endpoint and the **secret** received via SMS to successfully update and confirm the phone session.
     *
     * @param userId User ID.
     * @param secret Valid verification token.
     * @return [io.appwrite.models.Session]
     */
    suspend fun updatePhoneSession(
        userId: String,
        secret: String,
    ): Session {
        val apiPath = "account/sessions/phone"

        val apiParams = mutableMapOf<String, Any?>(
            "userId" to userId,
            "secret" to secret,
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )

        val converter: (Any) -> Session = {
            Session.from(map = it as Map<String, Any>)
        }

        return client.call(
            method = "PUT",
            path = apiPath,
            headers = apiHeaders,
            params = apiParams,
            converter = converter
        )
    }

    /**
     * Get Session
     *
     * Use this endpoint to get a logged in user&#039;s session using a Session ID. Inputting &#039;current&#039; will return the current session being used.
     *
     * @param sessionId Session ID. Use the string 'current' to get the current device session.
     * @return [io.appwrite.models.Session]
     */
    @Throws(AppwriteException::class)
    suspend fun getSession(
        sessionId: String,
    ): Session {
        val apiPath = "account/sessions/$sessionId"

        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )

        val converter: (Any) -> Session = {
            if ((it as Map<String, Any>).size == 2) {
                SessionList.from(it).sessions.first()
            } else {
                Session.from(map = it)
            }
        }

        return client.call(
            method = "GET",
            path = apiPath,
            headers = apiHeaders,
            params = mapOf(),
            converter = converter
        )
    }

    /**
     * Update OAuth Session (Refresh Tokens)
     *
     * Access tokens have limited lifespan and expire to mitigate security risks. If session was created using an OAuth provider, this route can be used to &quot;refresh&quot; the access token.
     *
     * @param sessionId Session ID. Use the string 'current' to update the current device session.
     * @return [io.appwrite.models.Session]
     */
    @Throws(AppwriteException::class)
    suspend fun updateSession(
        sessionId: String,
    ): Session {
        val apiPath = "account/sessions/{sessionId}"
            .replace("{sessionId}", sessionId)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )

        val converter: (Any) -> Session = {
            Session.from(map = it as Map<String, Any>)
        }

        return client.call(
            method = "PATCH",
            path = apiPath,
            headers = apiHeaders,
            params = apiParams,
            converter = converter
        )
    }

    /**
     * Delete Session
     *
     * Logout the user. Use &#039;current&#039; as the session ID to logout on this device, use a session ID to logout on another device. If you&#039;re looking to logout the user on all devices, use [Delete Sessions](/docs/client/account#accountDeleteSessions) instead.
     *
     * @param sessionId Session ID. Use the string 'current' to delete the current device session.
     * @return [Any]
     */
    @Throws(AppwriteException::class)
    suspend fun deleteSession(
        sessionId: String,
    ): Any {
        val apiPath = "account/sessions/$sessionId"

        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )
        return client.call(
            "DELETE",
            apiPath,
            apiHeaders,
            mapOf()
        )
    }

    /**
     * Update Status
     *
     * Block the currently logged in user account. Behind the scene, the user record is not deleted but permanently blocked from any access. To completely delete a user, use the Users API instead.
     *
     * @return [io.appwrite.models.account.User<T>]
     */
    @Throws(AppwriteException::class)
    suspend fun <T> updateStatus(
    ): User<T> {
        val apiPath = "account/status"

        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )

        val converter: (Any) -> User<T> = {
            User.from(map = it as Map<String, Any>)
        }

        return client.call(
            method = "PATCH",
            path = apiPath,
            headers = apiHeaders,
            params = mapOf(),
            converter = converter
        )
    }

    /**
     * Update Status
     *
     * Block the currently logged in user account. Behind the scene, the user record is not deleted but permanently blocked from any access. To completely delete a user, use the Users API instead.
     *
     * @return [io.appwrite.models.account.User<T>]
     */
    @Throws(AppwriteException::class)
    suspend fun updateStatusAccount(
    ): User<Map<String, Any>> = updateStatus()

    /**
     * Create Email Verification
     *
     * Use this endpoint to send a verification message to your user email address to confirm they are the valid owners of that address. Both the **userId** and **secret** arguments will be passed as query parameters to the URL you have provided to be attached to the verification email. The provided URL should redirect the user back to your app and allow you to complete the verification process by verifying both the **userId** and **secret** parameters. Learn more about how to [complete the verification process](/docs/client/account#accountUpdateEmailVerification). The verification link sent to the user&#039;s email address is valid for 7 days.Please note that in order to avoid a [Redirect Attack](https://github.com/OWASP/CheatSheetSeries/blob/master/cheatsheets/Unvalidated_Redirects_and_Forwards_Cheat_Sheet.md), the only valid redirect URLs are the ones from domains you have set when adding your platforms in the console interface.
     *
     * @param url URL to redirect the user back to your app from the verification email. Only URLs from hostnames in your project platform list are allowed. This requirement helps to prevent an [open redirect](https://cheatsheetseries.owasp.org/cheatsheets/Unvalidated_Redirects_and_Forwards_Cheat_Sheet.html) attack against your project API.
     * @return [io.appwrite.models.Token]
     */
    suspend fun createVerification(
        url: String,
    ): Token {
        val apiPath = "account/verification"

        val apiParams = mutableMapOf<String, Any?>(
            "url" to url,
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )

        val converter: (Any) -> Token = {
            Token.from(map = it as Map<String, Any>)
        }

        return client.call(
            method = "POST",
            path = apiPath,
            headers = apiHeaders,
            params = apiParams,
            converter = converter
        )
    }

    /**
     * Create Email Verification (confirmation)
     *
     * Use this endpoint to complete the user email verification process. Use both the **userId** and **secret** parameters that were attached to your app URL to verify the user email ownership. If confirmed this route will return a 200 status code.
     *
     * @param userId User ID.
     * @param secret Valid verification token.
     * @return [io.appwrite.models.Token]
     */
    @Throws(AppwriteException::class)
    suspend fun updateVerification(
        userId: String,
        secret: String,
    ): Token {
        val apiPath = "account/verification"

        val apiParams = mutableMapOf<String, Any?>(
            "userId" to userId,
            "secret" to secret,
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )

        val converter: (Any) -> Token = {
            Token.from(map = it as Map<String, Any>)
        }

        return client.call(
            method = "PUT",
            path = apiPath,
            headers = apiHeaders,
            params = apiParams,
            converter = converter
        )
    }

    /**
     * Create Phone Verification
     *
     * Use this endpoint to send a verification SMS to the currently logged in user. This endpoint is meant for use after updating a user&#039;s phone number using the [accountUpdatePhone](/docs/client/account#accountUpdatePhone) endpoint. Learn more about how to [complete the verification process](/docs/client/account#accountUpdatePhoneVerification). The verification code sent to the user&#039;s phone number is valid for 15 minutes.
     *
     * @return [io.appwrite.models.Token]
     */
    suspend fun createPhoneVerification(
    ): Token {
        val apiPath = "account/verification/phone"

        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )

        val converter: (Any) -> Token = {
            Token.from(map = it as Map<String, Any>)
        }

        return client.call(
            method = "POST",
            path = apiPath,
            headers = apiHeaders,
            params = mapOf(),
            converter = converter
        )
    }

    /**
     * Create Phone Verification (confirmation)
     *
     * Use this endpoint to complete the user phone verification process. Use the **userId** and **secret** that were sent to your user&#039;s phone number to verify the user email ownership. If confirmed this route will return a 200 status code.
     *
     * @param userId User ID.
     * @param secret Valid verification token.
     * @return [io.appwrite.models.Token]
     */
    suspend fun updatePhoneVerification(
        userId: String,
        secret: String,
    ): Token {
        val apiPath = "account/verification/phone"

        val apiParams = mutableMapOf<String, Any?>(
            "userId" to userId,
            "secret" to secret,
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )

        val converter: (Any) -> Token = {
            Token.from(map = it as Map<String, Any>)
        }

        return client.call(
            method = "PUT",
            path = apiPath,
            headers = apiHeaders,
            params = apiParams,
            converter = converter
        )
    }
}