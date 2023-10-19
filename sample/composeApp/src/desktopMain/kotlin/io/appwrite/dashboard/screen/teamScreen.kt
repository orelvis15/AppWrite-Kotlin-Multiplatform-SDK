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
fun teamScreen() {

    val scrollToIndex = remember { mutableStateOf(0) }
    val scrollState = rememberLazyListState()
    val scrollStateIndex = rememberLazyListState()
    var currentIndex: Int by remember { mutableStateOf(0) }

    val client = Client("https://cloud.appwrite.io/v1", "", false)
        .project("651ffe9ce6229e1f5efb")

    val teams = Teams(client)

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
                            buildForms(dto.id, teams)
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
private fun buildForms(index: Int, teams: Teams) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().border(0.5.dp, Color.LightGray)
    ) {
        Spacer(Modifier.height(5.dp))
        androidx.compose.material3.Text("Test it", color = Color.White)
        Spacer(Modifier.height(5.dp))
        when (index) {
            0 -> listTeam(teams)
            1 -> createTeam(teams)
            2 -> getTeam(teams)
            3 -> updateNameTeam(teams)
            4 -> deleteTeam(teams)
            5 -> listMembershipsTeam(teams)
            6 -> createMembershipsTeam(teams)
            7 -> getMembershipsTeam(teams)
            8 -> updateMembershipsTeam(teams)
            9 -> deleteMembershipsTeam(teams)
            10 -> updateMembershipsTeamStatus(teams)
            11 -> getTeamPref(teams)
            12 -> updateTeamPref(teams)
        }
    }
}

private fun getElementList(): List<ItemList> {
    return listOf(
        ItemList(0, "List Teams", "Get a list of all the teams in which the current user is a member. You can use the parameters to filter your results"),
        ItemList(
            1,
            "Create Team",
            "Create a new team. The user who creates the team will automatically be assigned as the owner of the team. Only the users with the owner role can invite new members, add new owners and delete or update the team"
        ),
        ItemList(
            2,
            "Get Team",
            "Get a team by its ID. All team members have read access for this resource"
        ),
        ItemList(3, "Update Name", "Update the team's name by its unique ID"),
        ItemList(4, "Delete Team", "Delete a team using its ID. Only team members with the owner role can delete the team"),
        ItemList(
            5,
            "List Team Memberships",
            "Use this endpoint to list a team&#039;s members using the team&#039;s ID. All team members have read access to this endpoint"
        ),
        ItemList(
            6,
            "Create Team Membership",
            "Invite a new member to join your team. Provide an ID for existing users, or invite unregistered users using an email or phone number. If initiated from a Client SDK, Appwrite will send an email or sms with a link to join the team to the invited user, and an account will be created for them if one doesn&#039;t exist. If initiated from a Server SDK, the new member will be added automatically to the team.You only need to provide one of a user ID, email, or phone number. Appwrite will prioritize accepting the user ID &gt; email &gt; phone number if you provide more than one of these parameters.Use the `url` parameter to redirect the user from the invitation email to your app. After the user is redirected, use the [Update Team Membership Status](/docs/client/teams#teamsUpdateMembershipStatus) endpoint to allow the user to accept the invitation to the team. Please note that to avoid a [Redirect Attack](https://github.com/OWASP/CheatSheetSeries/blob/master/cheatsheets/Unvalidated_Redirects_and_Forwards_Cheat_Sheet.md) Appwrite will accept the only redirect URLs under the domains you have added as a platform on the Appwrite Console"
        ),
        ItemList(7, "Get Team Membership", "Get a team member by the membership unique id. All team members have read access for this resource"),
        ItemList(
            8,
            "Update Membership",
            "Modify the roles of a team member. Only team members with the owner role have access to this endpoint. Learn more about [roles and permissions](/docs/permissions)"
        ),
        ItemList(
            9,
            "Delete Team Membership",
            "This endpoint allows a user to leave a team or for a team owner to delete the membership of any other team member. You can also use this endpoint to delete a user membership even if it is not accepted"
        ),
        ItemList(10, "Update Team Membership Status", "Use this endpoint to allow a user to accept an invitation to join a team after being redirected back to your app from the invitation email received by the user.If the request is successful, a session for the user is automatically created"),
        ItemList(
            11,
            "Get Team Preferences",
            "Get the team's shared preferences by its unique ID. If a preference doesn't need to be shared by all team members, prefer storing them in [user preferences](/docs/client/account#accountGetPrefs)"
        ),
        ItemList(
            12,
            "Update Preferences",
            "Update the team&#039;s preferences by its unique ID. The object you pass is stored as is and replaces any previous value. The maximum allowed prefs size is 64kB and throws an error if exceeded"
        )
    )
}