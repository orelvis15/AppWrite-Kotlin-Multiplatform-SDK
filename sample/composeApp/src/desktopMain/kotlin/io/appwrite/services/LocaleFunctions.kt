package io.appwrite.services

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import io.appwrite.CustomButton
import io.appwrite.output
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun getUserLocale(locale: Locale) {
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
        Spacer(Modifier.height(5.dp))
        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val data = locale.get()
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
fun listCode(locale: Locale) {
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
        Spacer(Modifier.height(5.dp))
        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val data = locale.listCodes()
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
fun listContinentsLocale(locale: Locale) {
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
        Spacer(Modifier.height(5.dp))
        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val data = locale.listContinents()
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
fun listCountriesLocale(locale: Locale) {
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
        Spacer(Modifier.height(5.dp))
        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val data = locale.listCountries()
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
fun listCountriesEULocale(locale: Locale) {
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
        Spacer(Modifier.height(5.dp))
        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val data = locale.listCountriesEU()
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
fun listCountriesPhonesLocale(locale: Locale) {
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
        Spacer(Modifier.height(5.dp))
        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val data = locale.listCountriesPhones()
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
fun listCurrencyLocale(locale: Locale) {
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
        Spacer(Modifier.height(5.dp))
        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val data = locale.listCurrencies()
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
fun listLanguageLocale(locale: Locale) {
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
        Spacer(Modifier.height(5.dp))
        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val data = locale.listLanguages()
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