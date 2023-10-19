package io.appwrite.services

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.appwrite.CustomButton
import io.appwrite.CustomTextField
import io.appwrite.output
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun get(account: Account) {
    val output: MutableState<Any> = remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(false) }
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().drawBehind {
        drawLine(
            color = Color.LightGray,
            start = Offset(0f, size.height),
            end = Offset(size.width, size.height),
            strokeWidth = 1f
        )
    }) {
        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val data = account.getAccount()
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
fun create(account: Account) {
    val output: MutableState<Any> = remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(false) }

    val userId = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
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
        CustomTextField(userId, "UserId")
        CustomTextField(email, "Email")
        CustomTextField(password, "Password")
        CustomTextField(name, "Name")

        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val data = account.createAccount(userId.value, email.value, password.value, name.value)
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
fun updateEmail(account: Account) {
    val output: MutableState<Any> = remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(false) }

    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().drawBehind {
        drawLine(
            color = Color.LightGray,
            start = Offset(0f, size.height),
            end = Offset(size.width, size.height),
            strokeWidth = 1f
        )
    }) {
        Spacer(Modifier.height(10.dp))
        CustomTextField(email, "Email")
        CustomTextField(password, "Password")

        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val data = account.updateEmailAccount(email.value, password.value)
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
fun listIdentities(account: Account) {
    val output: MutableState<Any> = remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(false) }

    val query = remember { mutableStateOf("") }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().drawBehind {
        drawLine(
            color = Color.LightGray,
            start = Offset(0f, size.height),
            end = Offset(size.width, size.height),
            strokeWidth = 1f
        )
    }) {
        Spacer(Modifier.height(10.dp))
        CustomTextField(query, "Query")

        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val q = if (query.value.isEmpty()) query.value else null
                    val data = account.listIdentities(q)
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
fun deleteIdentities(account: Account) {
    val output: MutableState<Any> = remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(false) }

    val identityId = remember { mutableStateOf("") }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().drawBehind {
        drawLine(
            color = Color.LightGray,
            start = Offset(0f, size.height),
            end = Offset(size.width, size.height),
            strokeWidth = 1f
        )
    }) {
        Spacer(Modifier.height(10.dp))
        CustomTextField(identityId, "IdentityId")

        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val data = account.deleteIdentity(identityId.value)
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
fun createJWT(account: Account) {
    val output: MutableState<Any> = remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(false) }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().drawBehind {
        drawLine(
            color = Color.LightGray,
            start = Offset(0f, size.height),
            end = Offset(size.width, size.height),
            strokeWidth = 1f
        )
    }) {
        Spacer(Modifier.height(10.dp))

        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val data = account.createJWT()
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
fun listLogs(account: Account) {
    val output: MutableState<Any> = remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(false) }

    val identityId = remember { mutableStateOf("") }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().drawBehind {
        drawLine(
            color = Color.LightGray,
            start = Offset(0f, size.height),
            end = Offset(size.width, size.height),
            strokeWidth = 1f
        )
    }) {
        Spacer(Modifier.height(10.dp))
        Text("Add the ids separated by commas", color = Color.White, fontSize = 10.sp)
        CustomTextField(identityId, "IdentityId")

        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val data = account.listLogs(identityId.value.split(','))
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
fun updateName(account: Account) {
    val output: MutableState<Any> = remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(false) }

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
        CustomTextField(name, "Name")

        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val data = account.updateNameAccount(name.value)
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
fun updatePassword(account: Account) {
    val output: MutableState<Any> = remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(false) }

    val password = remember { mutableStateOf("") }
    val oldPassword = remember { mutableStateOf("") }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().drawBehind {
        drawLine(
            color = Color.LightGray,
            start = Offset(0f, size.height),
            end = Offset(size.width, size.height),
            strokeWidth = 1f
        )
    }) {
        Spacer(Modifier.height(10.dp))
        CustomTextField(password, "Password")
        CustomTextField(oldPassword, "Old Password")

        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val data = account.updatePasswordAccount(password.value, oldPassword.value)
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
fun updatePhone(account: Account) {
    val output: MutableState<Any> = remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(false) }

    val phone = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().drawBehind {
        drawLine(
            color = Color.LightGray,
            start = Offset(0f, size.height),
            end = Offset(size.width, size.height),
            strokeWidth = 1f
        )
    }) {
        Spacer(Modifier.height(10.dp))
        CustomTextField(phone, "Phone")
        CustomTextField(password, "Password")

        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val data = account.updatePhoneAccount(phone.value, password.value)
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
fun getPrefs(account: Account) {
    val output: MutableState<Any> = remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(false) }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().drawBehind {
        drawLine(
            color = Color.LightGray,
            start = Offset(0f, size.height),
            end = Offset(size.width, size.height),
            strokeWidth = 1f
        )
    }) {
        Spacer(Modifier.height(10.dp))
        CustomButton("Get Prefs", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val data = account.getPrefsAccount()
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
fun updatePrefs(account: Account) {
    val output: MutableState<Any> = remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(false) }

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
        CustomTextField(key, "key")
        CustomTextField(value, "value")

        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val data = account.updatePrefsAccount(prefs = mapOf(key.value to value.value))
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
fun createRecovery(account: Account) {
    val output: MutableState<Any> = remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(false) }

    val email = remember { mutableStateOf("") }
    val url = remember { mutableStateOf("https://localhost") }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().drawBehind {
        drawLine(
            color = Color.LightGray,
            start = Offset(0f, size.height),
            end = Offset(size.width, size.height),
            strokeWidth = 1f
        )
    }) {
        Spacer(Modifier.height(10.dp))
        CustomTextField(email, "Email")
        CustomTextField(url, "Url")

        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val data = account.createRecovery(email.value, url.value)
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
fun updateRecovery(account: Account) {
    val output: MutableState<Any> = remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(false) }

    val userId = remember { mutableStateOf("") }
    val secret = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val repeatPassword = remember { mutableStateOf("") }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().drawBehind {
        drawLine(
            color = Color.LightGray,
            start = Offset(0f, size.height),
            end = Offset(size.width, size.height),
            strokeWidth = 1f
        )
    }) {
        Spacer(Modifier.height(10.dp))
        CustomTextField(userId, "Email")
        CustomTextField(secret, "Secret")
        CustomTextField(password, "Password")
        CustomTextField(repeatPassword, "Repeat Password")

        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val data = account.updateRecovery(userId.value, secret.value, password.value, repeatPassword.value)
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
fun listSessions(account: Account) {
    val output: MutableState<Any> = remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(false) }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().drawBehind {
        drawLine(
            color = Color.LightGray,
            start = Offset(0f, size.height),
            end = Offset(size.width, size.height),
            strokeWidth = 1f
        )
    }) {
        Spacer(Modifier.height(10.dp))

        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val data = account.listSessions()
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
fun deleteSessions(account: Account) {
    val output: MutableState<Any> = remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(false) }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().drawBehind {
        drawLine(
            color = Color.LightGray,
            start = Offset(0f, size.height),
            end = Offset(size.width, size.height),
            strokeWidth = 1f
        )
    }) {
        Spacer(Modifier.height(10.dp))

        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val data = account.deleteSessions()
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
fun createAnonymousSession(account: Account) {
    val output: MutableState<Any> = remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(false) }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().drawBehind {
        drawLine(
            color = Color.LightGray,
            start = Offset(0f, size.height),
            end = Offset(size.width, size.height),
            strokeWidth = 1f
        )
    }) {
        Spacer(Modifier.height(10.dp))

        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val data = account.createAnonymousSession()
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
fun createEmailSession(account: Account) {
    val output: MutableState<Any> = remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(false) }

    val email = remember { mutableStateOf("orelvis15@gmail.com") }
    val password = remember { mutableStateOf("123123123") }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().drawBehind {
        drawLine(
            color = Color.LightGray,
            start = Offset(0f, size.height),
            end = Offset(size.width, size.height),
            strokeWidth = 1f
        )
    }) {
        Spacer(Modifier.height(10.dp))
        CustomTextField(email, "Email")
        CustomTextField(password, "Password")

        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val data = account.createEmailSession(email.value, password.value)
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
fun CreateMagicURLSession(account: Account) {
    val output: MutableState<Any> = remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(false) }

    val userId = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val url = remember { mutableStateOf("") }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().drawBehind {
        drawLine(
            color = Color.LightGray,
            start = Offset(0f, size.height),
            end = Offset(size.width, size.height),
            strokeWidth = 1f
        )
    }) {
        Spacer(Modifier.height(10.dp))
        CustomTextField(userId, "UserId")
        CustomTextField(email, "Email")
        CustomTextField(url, "Url")

        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val data = account.createMagicURLSession(userId.value, email.value, url.value)
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
fun UpdateMagicURLSession(account: Account) {
    val output: MutableState<Any> = remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(false) }

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
        CustomTextField(userId, "UserId")
        CustomTextField(secret, "Secret")

        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val data = account.updateMagicURLSession(userId.value, secret.value)
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
fun CreateOAuth2Session(account: Account) {
    val output: MutableState<Any> = remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(false) }

    val provider = remember { mutableStateOf("google") }
    val success = remember { mutableStateOf("http://localhost:5789/auth/oauth2/success") }
    val failure = remember { mutableStateOf("http://localhost:5789/auth/oauth2/failure") }
    val scope = remember { mutableStateOf("email") }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().drawBehind {
        drawLine(
            color = Color.LightGray,
            start = Offset(0f, size.height),
            end = Offset(size.width, size.height),
            strokeWidth = 1f
        )
    }) {
        Spacer(Modifier.height(10.dp))
        CustomTextField(provider, "Provider")
        CustomTextField(success, "Success")
        CustomTextField(failure, "Failure")
        CustomTextField(scope, "Scope")

        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val data = account.createOAuth2Session(provider.value, success.value, failure.value, null, 5789 )
                    output.value = if (data) "Auth Success" else "Auth Failure"
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
fun createPhoneSession(account: Account) {
    val output: MutableState<Any> = remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(false) }

    val userId = remember { mutableStateOf("") }
    val phone = remember { mutableStateOf("") }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().drawBehind {
        drawLine(
            color = Color.LightGray,
            start = Offset(0f, size.height),
            end = Offset(size.width, size.height),
            strokeWidth = 1f
        )
    }) {
        Spacer(Modifier.height(10.dp))
        CustomTextField(userId, "UserId")
        CustomTextField(phone, "Phone")

        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val data = account.createPhoneSession(userId.value, phone.value)
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
fun updatePhoneSession(account: Account) {
    val output: MutableState<Any> = remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(false) }

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
        CustomTextField(userId, "UserId")
        CustomTextField(secret, "Secret")

        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val data = account.updatePhoneSession(userId.value, secret.value)
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
fun getSession(account: Account) {
    val output: MutableState<Any> = remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(false) }

    val sessionId = remember { mutableStateOf("") }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().drawBehind {
        drawLine(
            color = Color.LightGray,
            start = Offset(0f, size.height),
            end = Offset(size.width, size.height),
            strokeWidth = 1f
        )
    }) {
        Spacer(Modifier.height(10.dp))
        CustomTextField(sessionId, "SessionId")

        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val data = account.getSession(sessionId.value)
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
fun updateSession(account: Account) {
    val output: MutableState<Any> = remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(false) }

    val sessionId = remember { mutableStateOf("") }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().drawBehind {
        drawLine(
            color = Color.LightGray,
            start = Offset(0f, size.height),
            end = Offset(size.width, size.height),
            strokeWidth = 1f
        )
    }) {
        Spacer(Modifier.height(10.dp))
        CustomTextField(sessionId, "Session Id")

        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val data = account.updateSession(sessionId.value)
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
fun deleteSession(account: Account) {
    val output: MutableState<Any> = remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(false) }

    val sessionId = remember { mutableStateOf("") }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().drawBehind {
        drawLine(
            color = Color.LightGray,
            start = Offset(0f, size.height),
            end = Offset(size.width, size.height),
            strokeWidth = 1f
        )
    }) {
        Spacer(Modifier.height(10.dp))
        CustomTextField(sessionId, "SessionId")

        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val data = account.deleteSession(sessionId.value)
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
fun updateStatus(account: Account) {
    val output: MutableState<Any> = remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(false) }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().drawBehind {
        drawLine(
            color = Color.LightGray,
            start = Offset(0f, size.height),
            end = Offset(size.width, size.height),
            strokeWidth = 1f
        )
    }) {
        Spacer(Modifier.height(10.dp))

        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val data = account.updateStatusAccount()
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
fun createVerification(account: Account) {
    val output: MutableState<Any> = remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(false) }

    val url = remember { mutableStateOf("") }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().drawBehind {
        drawLine(
            color = Color.LightGray,
            start = Offset(0f, size.height),
            end = Offset(size.width, size.height),
            strokeWidth = 1f
        )
    }) {
        Spacer(Modifier.height(10.dp))
        CustomTextField(url, "Url")

        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val data = account.createVerification(url.value)
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
fun updateVerification(account: Account) {
    val output: MutableState<Any> = remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(false) }

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
        CustomTextField(userId, "UserId")
        CustomTextField(secret, "Secret")

        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val data = account.updateVerification(userId.value, secret.value)
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
fun createPhoneVerification(account: Account) {
    val output: MutableState<Any> = remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(false) }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().drawBehind {
        drawLine(
            color = Color.LightGray,
            start = Offset(0f, size.height),
            end = Offset(size.width, size.height),
            strokeWidth = 1f
        )
    }) {
        Spacer(Modifier.height(10.dp))

        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val data = account.createPhoneVerification()
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
fun updatePhoneVerification(account: Account) {
    val output: MutableState<Any> = remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(false) }

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
        CustomTextField(userId, "UserId")
        CustomTextField(secret, "Secret")

        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val data = account.updatePhoneVerification(userId.value, secret.value)
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