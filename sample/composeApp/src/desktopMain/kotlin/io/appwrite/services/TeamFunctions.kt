package io.appwrite.services

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.appwrite.CustomButton
import io.appwrite.CustomTextField
import io.appwrite.ID
import io.appwrite.output
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun listTeam(team: Teams) {
    val output: MutableState<Any> = remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(false) }

    val queries = remember { mutableStateOf("") }
    val search = remember { mutableStateOf("") }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().drawBehind {
        drawLine(
            color = Color.LightGray,
            start = Offset(0f, size.height),
            end = Offset(size.width, size.height),
            strokeWidth = 1f
        )
    }) {
        Spacer(Modifier.height(10.dp))
        Text("Add the elements list separated by commas", color = Color.White, fontSize = 10.sp)
        CustomTextField(queries, "Queries")
        CustomTextField(search, "Search")
        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val query = if(queries.value.isNotEmpty()) queries.value.split(',') else null
                    val searchValue = search.value.ifEmpty { null }
                    val data = team.listTeam(query, searchValue)
                    output.value = data.toString()
                    loading.value = false
                } catch (e: Exception) {
                    output.value = e
                    loading.value = false
                }
            }
        }
    }
    output(output)
}

@Composable
fun createTeam(team: Teams) {
    val output: MutableState<Any> = remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(false) }

    val name = remember { mutableStateOf("") }
    val roles = remember { mutableStateOf("") }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().drawBehind {
        drawLine(
            color = Color.LightGray,
            start = Offset(0f, size.height),
            end = Offset(size.width, size.height),
            strokeWidth = 1f
        )
    }) {
        Spacer(Modifier.height(10.dp))
        CustomTextField(name, "Name")
        Spacer(Modifier.height(8.dp))
        Text("Add the elements list separated by commas", color = Color.White, fontSize = 10.sp)
        CustomTextField(roles, "Roles")
        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val role = if (roles.value.isNotEmpty()) roles.value.split(',') else null
                    val data = team.createTeam(ID.unique(), name.value, role)
                    output.value = data.toString()
                    loading.value = false
                } catch (e: Exception) {
                    output.value = e
                    loading.value = false
                }
            }
        }
    }
    output(output)
}

@Composable
fun getTeam(team: Teams) {
    val output: MutableState<Any> = remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(false) }

    val teamId = remember { mutableStateOf("") }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().drawBehind {
        drawLine(
            color = Color.LightGray,
            start = Offset(0f, size.height),
            end = Offset(size.width, size.height),
            strokeWidth = 1f
        )
    }) {
        Spacer(Modifier.height(10.dp))
        CustomTextField(teamId, "teamId")

        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val data = team.getTeam(teamId.value)
                    output.value = data.toString()
                    loading.value = false
                } catch (e: Exception) {
                    output.value = e
                    loading.value = false
                }
            }
        }
    }
    output(output)
}

@Composable
fun updateNameTeam(team: Teams) {
    val output: MutableState<Any> = remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(false) }

    val teamId = remember { mutableStateOf("") }
    val name = remember { mutableStateOf("") }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().drawBehind {
        drawLine(
            color = Color.LightGray,
            start = Offset(0f, size.height),
            end = Offset(size.width, size.height),
            strokeWidth = 1f
        )
    }) {
        Spacer(Modifier.height(10.dp))
        CustomTextField(teamId, "TeamId")
        CustomTextField(name, "Name")

        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val data = team.updateNameTeam(teamId.value, name.value)
                    output.value = data.toString()
                    loading.value = false
                } catch (e: Exception) {
                    output.value = e
                    loading.value = false
                }
            }
        }
    }
    output(output)
}

@Composable
fun deleteTeam(team: Teams) {
    val output: MutableState<Any> = remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(false) }

    val teamId = remember { mutableStateOf("") }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().drawBehind {
        drawLine(
            color = Color.LightGray,
            start = Offset(0f, size.height),
            end = Offset(size.width, size.height),
            strokeWidth = 1f
        )
    }) {
        Spacer(Modifier.height(10.dp))
        CustomTextField(teamId, "TeamId")

        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val data = team.delete(teamId.value)
                    output.value = data.toString()
                    loading.value = false
                } catch (e: Exception) {
                    output.value = e
                    loading.value = false
                }
            }
        }
    }
    output(output)
}

@Composable
fun listMembershipsTeam(team: Teams) {
    val output: MutableState<Any> = remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(false) }

    val teamId = remember { mutableStateOf("") }
    val queries = remember { mutableStateOf("") }
    val search = remember { mutableStateOf("") }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().drawBehind {
        drawLine(
            color = Color.LightGray,
            start = Offset(0f, size.height),
            end = Offset(size.width, size.height),
            strokeWidth = 1f
        )
    }) {
        Spacer(Modifier.height(10.dp))
        CustomTextField(teamId, "TeamId")
        Spacer(Modifier.height(8.dp))
        Text("Add the elements list separated by commas", color = Color.White, fontSize = 10.sp)
        CustomTextField(queries, "Queries")
        CustomTextField(search, "Search")

        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val query = if (queries.value.isNotEmpty()) queries.value.split(',') else null
                    val searchValue = search.value.ifEmpty { null }
                    val data = team.listMemberships(teamId.value, query, searchValue)
                    output.value = data.toString()
                    loading.value = false
                } catch (e: Exception) {
                    output.value = e
                    loading.value = false
                }
            }
        }
    }
    output(output)
}

@Composable
fun createMembershipsTeam(team: Teams) {
    val output: MutableState<Any> = remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(false) }

    val teamId = remember { mutableStateOf("652c1f520bd6e52ada7f") }
    val email = remember { mutableStateOf("orelvis15@gmail.com") }
    val roles = remember { mutableStateOf("Admin,User") }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().drawBehind {
        drawLine(
            color = Color.LightGray,
            start = Offset(0f, size.height),
            end = Offset(size.width, size.height),
            strokeWidth = 1f
        )
    }) {
        Spacer(Modifier.height(10.dp))
        CustomTextField(teamId, "TeamId")
        CustomTextField(email, "Email")
        Spacer(Modifier.height(8.dp))
        Text("Add the elements list separated by commas", color = Color.White, fontSize = 10.sp)
        CustomTextField(roles, "Roles")

        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val data = team.createMembership(teamId = teamId.value, roles = roles.value.split(','), email = email.value, url = "http://localhost")
                    output.value = data.toString()
                    loading.value = false
                } catch (e: Exception) {
                    output.value = e
                    loading.value = false
                }
            }
        }
    }
    output(output)
}

@Composable
fun getMembershipsTeam(team: Teams) {
    val output: MutableState<Any> = remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(false) }

    val teamId = remember { mutableStateOf("652c1f520bd6e52ada7f") }
    val membership = remember { mutableStateOf("652c2d8276c9ee63496b") }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().drawBehind {
        drawLine(
            color = Color.LightGray,
            start = Offset(0f, size.height),
            end = Offset(size.width, size.height),
            strokeWidth = 1f
        )
    }) {
        Spacer(Modifier.height(10.dp))
        CustomTextField(teamId, "TeamId")
        CustomTextField(membership, "Membership ID")

        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val data = team.getMembership(teamId = teamId.value, membershipId = membership.value)
                    output.value = data.toString()
                    loading.value = false
                } catch (e: Exception) {
                    output.value = e
                    loading.value = false
                }
            }
        }
    }
    output(output)
}

@Composable
fun updateMembershipsTeam(team: Teams) {
    val output: MutableState<Any> = remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(false) }

    val teamId = remember { mutableStateOf("652c1f520bd6e52ada7f") }
    val membership = remember { mutableStateOf("652c2d8276c9ee63496b") }
    val roles = remember { mutableStateOf("Admin,User") }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().drawBehind {
        drawLine(
            color = Color.LightGray,
            start = Offset(0f, size.height),
            end = Offset(size.width, size.height),
            strokeWidth = 1f
        )
    }) {
        Spacer(Modifier.height(10.dp))
        CustomTextField(teamId, "TeamId")
        CustomTextField(membership, "Membership ID")
        Spacer(Modifier.height(8.dp))
        Text("Add the elements list separated by commas", color = Color.White, fontSize = 10.sp)
        CustomTextField(roles, "Roles")

        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val data = team.updateMembership(teamId = teamId.value, membershipId = membership.value, roles.value.split(','))
                    output.value = data.toString()
                    loading.value = false
                } catch (e: Exception) {
                    output.value = e
                    loading.value = false
                }
            }
        }
    }
    output(output)
}

@Composable
fun deleteMembershipsTeam(team: Teams) {
    val output: MutableState<Any> = remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(false) }

    val teamId = remember { mutableStateOf("") }
    val membership = remember { mutableStateOf("") }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().drawBehind {
        drawLine(
            color = Color.LightGray,
            start = Offset(0f, size.height),
            end = Offset(size.width, size.height),
            strokeWidth = 1f
        )
    }) {
        Spacer(Modifier.height(10.dp))
        CustomTextField(teamId, "TeamId")
        CustomTextField(membership, "Membership ID")

        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val data = team.deleteMembership(teamId = teamId.value, membershipId = membership.value)
                    output.value = data.toString()
                    loading.value = false
                } catch (e: Exception) {
                    output.value = e
                    loading.value = false
                }
            }
        }
    }
    output(output)
}

@Composable
fun updateMembershipsTeamStatus(team: Teams) {
    val output: MutableState<Any> = remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(false) }

    val teamId = remember { mutableStateOf("") }
    val membership = remember { mutableStateOf("") }
    val userId = remember { mutableStateOf("") }
    val secret = remember { mutableStateOf("") }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().drawBehind {
        drawLine(
            color = Color.LightGray,
            start = Offset(0f, size.height),
            end = Offset(size.width, size.height),
            strokeWidth = 1f
        )
    }) {
        Spacer(Modifier.height(10.dp))
        CustomTextField(teamId, "TeamId")
        CustomTextField(membership, "Membership ID")
        CustomTextField(userId, "UserId")
        CustomTextField(secret, "Secret")

        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val data = team.updateMembershipStatus(teamId = teamId.value, membershipId = membership.value, userId = userId.value, secret = secret.value)
                    output.value = data.toString()
                    loading.value = false
                } catch (e: Exception) {
                    output.value = e
                    loading.value = false
                }
            }
        }
    }
    output(output)
}

@Composable
fun getTeamPref(team: Teams) {
    val output: MutableState<Any> = remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(false) }

    val teamId = remember { mutableStateOf("") }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().drawBehind {
        drawLine(
            color = Color.LightGray,
            start = Offset(0f, size.height),
            end = Offset(size.width, size.height),
            strokeWidth = 1f
        )
    }) {
        Spacer(Modifier.height(10.dp))
        CustomTextField(teamId, "TeamId")

        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val data = team.getPrefsTeam(teamId = teamId.value)
                    output.value = data.toString()
                    loading.value = false
                } catch (e: Exception) {
                    output.value = e
                    loading.value = false
                }
            }
        }
    }
    output(output)
}

@Composable
fun updateTeamPref(team: Teams) {
    val output: MutableState<Any> = remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(false) }

    val teamId = remember { mutableStateOf("") }
    val key = remember { mutableStateOf("") }
    val value = remember { mutableStateOf("") }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().drawBehind {
        drawLine(
            color = Color.LightGray,
            start = Offset(0f, size.height),
            end = Offset(size.width, size.height),
            strokeWidth = 1f
        )
    }) {
        Spacer(Modifier.height(10.dp))
        CustomTextField(teamId, "TeamId")
        CustomTextField(key, "key")
        CustomTextField(value, "value")

        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val data = team.updatePrefsTeam(teamId = teamId.value, prefs = mapOf(key.value to value.value))
                    output.value = data.toString()
                    loading.value = false
                } catch (e: Exception) {
                    output.value = e
                    loading.value = false
                }
            }
        }
    }
    output(output)
}