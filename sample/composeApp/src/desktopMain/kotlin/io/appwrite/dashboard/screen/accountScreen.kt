package io.appwrite.dashboard.screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.appwrite.Client
import io.appwrite.models.ItemList
import io.appwrite.services.*


@Composable
fun accountScreen() {

    val scrollToIndex = remember { mutableStateOf(0) }
    val scrollState = rememberLazyListState()
    val scrollStateIndex = rememberLazyListState()
    var currentIndex: Int by remember { mutableStateOf(0) }

    val client = Client("https://cloud.appwrite.io/v1", "", false)
        .project("651ffe9ce6229e1f5efb")

    val account = Account(client)

    val elements = getElementList()

    Row(modifier = Modifier.fillMaxSize().background(color = Color(0XFF141415))) {

        Column(modifier = Modifier.width(250.dp).padding(8.dp).drawBehind {
            drawLine(
                color = Color.White,
                start = Offset(size.width, 0f),
                end = Offset(size.width, size.height),
                strokeWidth = 0.4f
            )
        }) {
            Box {
                LazyColumn {
                    itemsIndexed(elements) { index, dto ->
                        Column {
                            val color = if(currentIndex == index) Color.White else Color.LightGray
                            androidx.compose.material3.Text(dto.name, color = color, modifier = Modifier.padding(5.dp).clickable {
                                scrollToIndex.value = index
                                currentIndex = index
                            })
                        }
                    }
                }
                VerticalScrollbar(
                    modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight(),
                    adapter = rememberScrollbarAdapter(
                        scrollState = scrollStateIndex
                    )
                )
            }
        }

        Column(modifier = Modifier.weight(1F)) {
            Box {
                LazyColumn(state = scrollState, verticalArrangement = Arrangement.spacedBy(5.dp)) {
                    itemsIndexed(elements) { index, dto ->
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxSize().padding(20.dp).background(Color(0XFF242426))
                        ) {
                            Spacer(Modifier.height(5.dp))
                            androidx.compose.material3.Text(dto.name, fontSize = 16.sp, color = Color(0xffFFDE00))
                            Spacer(Modifier.height(5.dp))
                            androidx.compose.material3.Text(
                                dto.description,
                                color = Color.White,
                                modifier = Modifier.fillMaxWidth().padding(10.dp)
                            )
                            Spacer(Modifier.height(10.dp))
                            buildForms(dto.id, account)
                        }
                    }
                }
                VerticalScrollbar(
                    modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight(),
                    adapter = rememberScrollbarAdapter(
                        scrollState = scrollState
                    )
                )
            }
        }

        LaunchedEffect(scrollToIndex.value) {
            scrollState.animateScrollToItem(scrollToIndex.value)
        }
    }
}

@Composable
private fun buildForms(index: Int, account: Account) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().border(0.5.dp, Color.LightGray)
    ) {
        Spacer(Modifier.height(5.dp))
        androidx.compose.material3.Text("Test it", color = Color.White)
        Spacer(Modifier.height(5.dp))
        when (index) {
            0 -> get(account)
            1 -> create(account)
            2 -> updateEmail(account)
            3 -> listIdentities(account)
            4 -> deleteIdentities(account)
            5 -> createJWT(account)
            6 -> listLogs(account)
            7 -> updateName(account)
            8 -> updatePassword(account)
            9 -> updatePhone(account)
            10 -> getPrefs(account)
            11 -> updatePrefs(account)
            12 -> createRecovery(account)
            13 -> updateRecovery(account)
            14 -> listSessions(account)
            15 -> deleteSessions(account)
            16 -> createAnonymousSession(account)
            17 -> createEmailSession(account)
            18 -> CreateMagicURLSession(account)
            19 -> UpdateMagicURLSession(account)
            20 -> CreateOAuth2Session(account)
            21 -> createPhoneSession(account)
            22 -> updatePhoneSession(account)
            23 -> getSession(account)
            24 -> updateSession(account)
            25 -> deleteSession(account)
            26 -> updateStatus(account)
            27 -> createVerification(account)
            28 -> updateVerification(account)
            29 -> createPhoneVerification(account)
            30 -> updatePhoneVerification(account)
        }
    }
}

private fun getElementList(): List<ItemList> {
    return listOf(
        ItemList(0, "Get Session", "Get the currently logged in user"),
        ItemList(
            1,
            "Create",
            "Use this endpoint to allow a new user to register a new account in your project. After the user registration completes successfully, you can use the [/account/verfication](/docs/client/account#accountCreateVerification) route to start verifying the user email address. To allow the new user to login to their new account, you need to create a new [account session](/docs/client/account#accountCreateSession)"
        ),
        ItemList(
            2,
            "Update Email",
            "Update currently logged in user account email address. After changing user address, the user confirmation status will get reset. A new confirmation email is not sent automatically however you can use the send confirmation email endpoint again to send the confirmation email. For security measures, user password is required to complete this request.This endpoint can also be used to convert an anonymous account to a normal one, by passing an email address and a new password."
        ),
        ItemList(3, "List Identities", "Get the list of identities for the currently logged in user"),
        ItemList(4, "Delete Identity", "Delete an identity by its unique ID"),
        ItemList(
            5,
            "Create JWT",
            "Use this endpoint to create a JSON Web Token. You can use the resulting JWT to authenticate on behalf of the current user when working with the Appwrite server-side API and SDKs. The JWT secret is valid for 15 minutes from its creation and will be invalid if the user will logout in that time frame."
        ),
        ItemList(
            6,
            "List Logs",
            "Get the list of latest security activity logs for the currently logged in user. Each log returns user IP address, location and date and time of log"
        ),
        ItemList(7, "Update Name", "Update currently logged in user account name"),
        ItemList(
            8,
            "Update Password",
            "Update currently logged in user password. For validation, user is required to pass in the new password, and the old password. For users created with OAuth, Team Invites and Magic URL, oldPassword is optional"
        ),
        ItemList(
            9,
            "Update Phone",
            "Update the currently logged in user&#039;s phone number. After updating the phone number, the phone verification status will be reset. A confirmation SMS is not sent automatically, however you can use the [POST /account/verification/phone](/docs/client/account#accountCreatePhoneVerification) endpoint to send a confirmation SMS."
        ),
        ItemList(10, "Get Prefs", "Get the preferences as a key-value object for the currently logged in user."),
        ItemList(
            11,
            "Update Prefs",
            "Update currently logged in user account preferences. The object you pass is stored as is, and replaces any previous value. The maximum allowed prefs size is 64kB and throws error if exceeded."
        ),
        ItemList(
            12,
            "Create Recovery",
            "Sends the user an email with a temporary secret key for password reset. When the user clicks the confirmation link he is redirected back to your app password reset URL with the secret key and email address values attached to the URL query string. Use the query string params to submit a request to the [PUT /account/recovery](/docs/client/account#accountUpdateRecovery) endpoint to complete the process. The verification link sent to the user&#039;s email address is valid for 1 hour."
        ),
        ItemList(
            13,
            "Update Recovery",
            "Use this endpoint to complete the user account password reset. Both the **userId** and **secret** arguments will be passed as query parameters to the redirect URL you have provided when sending your request to the [POST /account/recovery](/docs/client/account#accountCreateRecovery) endpoint.Please note that in order to avoid a [Redirect Attack](https://github.com/OWASP/CheatSheetSeries/blob/master/cheatsheets/Unvalidated_Redirects_and_Forwards_Cheat_Sheet.md) the only valid redirect URLs are the ones from domains you have set when adding your platforms in the console interface."
        ),
        ItemList(
            14,
            "List Sessions",
            "Get the list of active sessions across different devices for the currently logged in user"
        ),
        ItemList(
            15,
            "Delete Sessions",
            "Delete all sessions from the user account and remove any sessions cookies from the end client"
        ),
        ItemList(
            16,
            "Create Anonymous Session",
            "Use this endpoint to allow a new user to register an anonymous account in your project. This route will also create a new session for the user. To allow the new user to convert an anonymous account to a normal account, you need to update its [email and password](/docs/client/account#accountUpdateEmail) or create an [OAuth2 session](/docs/client/account#accountCreateOAuth2Session)"
        ),
        ItemList(
            17,
            "Create Email Session",
            "Allow the user to login into their account by providing a valid email and password combination. This route will create a new session for the user.A user is limited to 10 active sessions at a time by default. [Learn more about session limits](/docs/authentication-security#limits)"
        ),
        ItemList(
            18,
            "Create MagicURL Session",
            "Sends the user an email with a secret key for creating a session. If the provided user ID has not been registered, a new user will be created. When the user clicks the link in the email, the user is redirected back to the URL you provided with the secret key and userId values attached to the URL query string. Use the query string parameters to submit a request to the [PUT /account/sessions/magic-url](/docs/client/account#accountUpdateMagicURLSession) endpoint to complete the login process. The link sent to the user&#039;s email address is valid for 1 hour. If you are on a mobile device you can leave the URL parameter empty, so that the login completion will be handled by your Appwrite instance by default.A user is limited to 10 active sessions at a time by default. [Learn more about session limits](/docs/authentication-security#limits)"
        ),
        ItemList(
            19,
            "Update MagicURL Session",
            "Use this endpoint to complete creating the session with the Magic URL. Both the **userId** and **secret** arguments will be passed as query parameters to the redirect URL you have provided when sending your request to the [POST /account/sessions/magic-url](/docs/client/account#accountCreateMagicURLSession) endpoint.Please note that in order to avoid a [Redirect Attack](https://github.com/OWASP/CheatSheetSeries/blob/master/cheatsheets/Unvalidated_Redirects_and_Forwards_Cheat_Sheet.md) the only valid redirect URLs are the ones from domains you have set when adding your platforms in the console interface"
        ),
        ItemList(
            20,
            "Create OAuth2 Session",
            "Allow the user to login to their account using the OAuth2 provider of their choice. Each OAuth2 provider should be enabled from the Appwrite console first. Use the success and failure arguments to provide a redirect URL&#039;s back to your app when login is completed.If there is already an active session, the new session will be attached to the logged-in account. If there are no active sessions, the server will attempt to look for a user with the same email address as the email received from the OAuth2 provider and attach the new session to the existing user. If no matching user is found - the server will create a new user.A user is limited to 10 active sessions at a time by default. [Learn more about session limits](/docs/authentication-security#limits)"
        ),
        ItemList(
            21,
            "Create Phone Session",
            "Sends the user an SMS with a secret key for creating a session. If the provided user ID has not be registered, a new user will be created. Use the returned user ID and secret and submit a request to the [PUT /account/sessions/phone](/docs/client/account#accountUpdatePhoneSession) endpoint to complete the login process. The secret sent to the user&#039;s phone is valid for 15 minutes.A user is limited to 10 active sessions at a time by default. [Learn more about session limits](/docs/authentication-security#limits)"
        ),
        ItemList(
            22,
            "Update Phone Session",
            "Use this endpoint to complete creating a session with SMS. Use the **userId** from the [createPhoneSession](/docs/client/account#accountCreatePhoneSession) endpoint and the **secret** received via SMS to successfully update and confirm the phone session"
        ),
        ItemList(
            23,
            "Get Session",
            "Use this endpoint to get a logged in user&#039;s session using a Session ID. Inputting &#039;current&#039; will return the current session being used"
        ),
        ItemList(
            24,
            "Update Session",
            "Access tokens have limited lifespan and expire to mitigate security risks. If session was created using an OAuth provider, this route can be used to &quot;refresh&quot; the access token"
        ),
        ItemList(
            25,
            "Delete Session",
            "Logout the user. Use &#039;current&#039; as the session ID to logout on this device, use a session ID to logout on another device. If you&#039;re looking to logout the user on all devices, use [Delete Sessions](/docs/client/account#accountDeleteSessions) instead"
        ),
        ItemList(
            26,
            "Update Status",
            "Block the currently logged in user account. Behind the scene, the user record is not deleted but permanently blocked from any access. To completely delete a user, use the Users API instead"
        ),
        ItemList(
            27,
            "Create Verification",
            "Use this endpoint to send a verification message to your user email address to confirm they are the valid owners of that address. Both the **userId** and **secret** arguments will be passed as query parameters to the URL you have provided to be attached to the verification email. The provided URL should redirect the user back to your app and allow you to complete the verification process by verifying both the **userId** and **secret** parameters. Learn more about how to [complete the verification process](/docs/client/account#accountUpdateEmailVerification). The verification link sent to the user&#039;s email address is valid for 7 days.Please note that in order to avoid a [Redirect Attack](https://github.com/OWASP/CheatSheetSeries/blob/master/cheatsheets/Unvalidated_Redirects_and_Forwards_Cheat_Sheet.md), the only valid redirect URLs are the ones from domains you have set when adding your platforms in the console interface"
        ),
        ItemList(
            28,
            "Update Verification",
            "Use this endpoint to complete the user email verification process. Use both the **userId** and **secret** parameters that were attached to your app URL to verify the user email ownership. If confirmed this route will return a 200 status code"
        ),
        ItemList(
            29,
            "Create PhoneVerification",
            "Use this endpoint to send a verification SMS to the currently logged in user. This endpoint is meant for use after updating a user&#039;s phone number using the [accountUpdatePhone](/docs/client/account#accountUpdatePhone) endpoint. Learn more about how to [complete the verification process](/docs/client/account#accountUpdatePhoneVerification). The verification code sent to the user&#039;s phone number is valid for 15 minutes"
        ),
        ItemList(
            30,
            "Update PhoneVerification",
            "Use this endpoint to complete the user phone verification process. Use the **userId** and **secret** that were sent to your user&#039;s phone number to verify the user email ownership. If confirmed this route will return a 200 status code"
        )
    )
}