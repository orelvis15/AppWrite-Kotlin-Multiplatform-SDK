package io.appwrite.services

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.unit.dp
import io.appwrite.CustomButton
import io.appwrite.CustomTextField
import io.appwrite.output
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun getBrowser(avatar: Avatars) {
    val output: MutableState<Any> = remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(false) }

    val code = remember { mutableStateOf("") }
    var bitmap: ImageBitmap? by remember { mutableStateOf(null) }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().drawBehind {
        drawLine(
            color = Color.LightGray,
            start = Offset(0f, size.height),
            end = Offset(size.width, size.height),
            strokeWidth = 1f
        )
    }) {
        Spacer(Modifier.height(5.dp))
        CustomTextField(code, "Code")

        if (bitmap != null){
            Image(bitmap!!, "")
        }
        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val data = avatar.getBrowser(code.value)
                    bitmap = loadImageBitmap(data.inputStream())
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
fun getCreditCard(avatar: Avatars) {
    val output: MutableState<Any> = remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(false) }

    val code = remember { mutableStateOf("discover") }
    var bitmap: ImageBitmap? by remember { mutableStateOf(null) }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().drawBehind {
        drawLine(
            color = Color.LightGray,
            start = Offset(0f, size.height),
            end = Offset(size.width, size.height),
            strokeWidth = 1f
        )
    }) {
        Spacer(Modifier.height(5.dp))
        CustomTextField(code, "Code")

        if (bitmap != null){
            Image(bitmap!!, "")
        }
        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val data = avatar.getCreditCard(code.value)
                    bitmap = loadImageBitmap(data.inputStream())
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
fun getFavicon(avatar: Avatars) {
    val output: MutableState<Any> = remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(false) }

    val code = remember { mutableStateOf("https://www.google.com") }
    var bitmap: ImageBitmap? by remember { mutableStateOf(null) }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().drawBehind {
        drawLine(
            color = Color.LightGray,
            start = Offset(0f, size.height),
            end = Offset(size.width, size.height),
            strokeWidth = 1f
        )
    }) {
        Spacer(Modifier.height(5.dp))
        CustomTextField(code, "URL")

        if (bitmap != null){
            Image(bitmap!!, "")
        }
        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val data = avatar.getFavicon(code.value)
                    bitmap = loadImageBitmap(data.inputStream())
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
fun getFlag(avatar: Avatars) {
    val output: MutableState<Any> = remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(false) }

    val code = remember { mutableStateOf("US") }
    var bitmap: ImageBitmap? by remember { mutableStateOf(null) }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().drawBehind {
        drawLine(
            color = Color.LightGray,
            start = Offset(0f, size.height),
            end = Offset(size.width, size.height),
            strokeWidth = 1f
        )
    }) {
        Spacer(Modifier.height(5.dp))
        CustomTextField(code, "Code")

        if (bitmap != null){
            Image(bitmap!!, "")
        }
        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val data = avatar.getFlag(code.value)
                    bitmap = loadImageBitmap(data.inputStream())
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
fun getImage(avatar: Avatars) {
    val output: MutableState<Any> = remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(false) }

    val code = remember { mutableStateOf("https://pbs.twimg.com/profile_images/1469760681276813319/6_BLNyHB_400x400.jpg") }
    var bitmap: ImageBitmap? by remember { mutableStateOf(null) }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().drawBehind {
        drawLine(
            color = Color.LightGray,
            start = Offset(0f, size.height),
            end = Offset(size.width, size.height),
            strokeWidth = 1f
        )
    }) {
        Spacer(Modifier.height(5.dp))
        CustomTextField(code, "URL")

        if (bitmap != null){
            Image(bitmap!!, "")
        }
        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val data = avatar.getImage(code.value)
                    bitmap = loadImageBitmap(data.inputStream())
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
fun getInitials(avatar: Avatars) {
    val output: MutableState<Any> = remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(false) }

    val code = remember { mutableStateOf("Benjamin Carter Smith") }
    var bitmap: ImageBitmap? by remember { mutableStateOf(null) }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().drawBehind {
        drawLine(
            color = Color.LightGray,
            start = Offset(0f, size.height),
            end = Offset(size.width, size.height),
            strokeWidth = 1f
        )
    }) {
        Spacer(Modifier.height(5.dp))
        CustomTextField(code, "Name")

        if (bitmap != null){
            Image(bitmap!!, "")
        }
        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val data = avatar.getInitials(code.value)
                    bitmap = loadImageBitmap(data.inputStream())
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
fun getQR(avatar: Avatars) {
    val output: MutableState<Any> = remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(false) }

    val code = remember { mutableStateOf("Text to QR") }
    var bitmap: ImageBitmap? by remember { mutableStateOf(null) }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().drawBehind {
        drawLine(
            color = Color.LightGray,
            start = Offset(0f, size.height),
            end = Offset(size.width, size.height),
            strokeWidth = 1f
        )
    }) {
        Spacer(Modifier.height(5.dp))
        CustomTextField(code, "Text")

        if (bitmap != null){
            Image(bitmap!!, "")
        }
        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val data = avatar.getQR(code.value)
                    bitmap = loadImageBitmap(data.inputStream())
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