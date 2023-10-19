package io.appwrite.services

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.ComposeWindow
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.unit.dp
import io.appwrite.*
import io.appwrite.models.storage.InputFile
import io.appwrite.models.storage.UploadProgress
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.awt.FileDialog
import java.io.File

@Composable
fun listFiles(account: Storage) {
    val output: MutableState<Any> = remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(false) }

    val bucketId = remember { mutableStateOf("652ece6fdfdf1af6e951") }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().drawBehind {
        drawLine(
            color = Color.LightGray,
            start = Offset(0f, size.height),
            end = Offset(size.width, size.height),
            strokeWidth = 1f
        )
    }) {
        Spacer(Modifier.height(5.dp))
        CustomTextField(bucketId, "BucketId")
        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val data = account.listFiles(bucketId.value)
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
fun createFiles(account: Storage) {
    val output: MutableState<Any> = remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(false) }

    val bucketId = remember { mutableStateOf("652ece6fdfdf1af6e951") }
    var fileDialog: FileDialog? by remember { mutableStateOf(null) }
    var progress by remember { mutableStateOf(0.0) }

    var onProgress: (UploadProgress) -> Unit = {
        progress = it.progress
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().drawBehind {
        drawLine(
            color = Color.LightGray,
            start = Offset(0f, size.height),
            end = Offset(size.width, size.height),
            strokeWidth = 1f
        )
    }) {
        Spacer(Modifier.height(5.dp))
        CustomTextField(bucketId, "BucketId")

        Row {
            Text("File: ${File(fileDialog?.file.toString()).name}", color = Color.White)
            CustomButton("Load File", mutableStateOf(false)){
                fileDialog = FileDialog(ComposeWindow()).apply {
                    isVisible = true
                }
            }
        }

        Text("Progress: $progress", color = Color.White)

        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val inputFile = InputFile.fromFile(fileDialog?.files?.first()!!)
                    val data = account.createFile(bucketId.value, ID.unique(), inputFile, listOf(Permission.write(Role.any())), onProgress)
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
fun getFile(account: Storage) {
    val output: MutableState<Any> = remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(false) }

    val bucketId = remember { mutableStateOf("652ece6fdfdf1af6e951") }
    val fileId = remember { mutableStateOf("") }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().drawBehind {
        drawLine(
            color = Color.LightGray,
            start = Offset(0f, size.height),
            end = Offset(size.width, size.height),
            strokeWidth = 1f
        )
    }) {
        Spacer(Modifier.height(5.dp))
        CustomTextField(bucketId, "BucketId")
        CustomTextField(fileId, "FileId")
        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val data = account.getFile(bucketId.value, fileId.value)
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
fun updateFile(account: Storage) {
    val output: MutableState<Any> = remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(false) }

    val bucketId = remember { mutableStateOf("652ece6fdfdf1af6e951") }
    val fileId = remember { mutableStateOf("") }
    val name = remember { mutableStateOf("") }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().drawBehind {
        drawLine(
            color = Color.LightGray,
            start = Offset(0f, size.height),
            end = Offset(size.width, size.height),
            strokeWidth = 1f
        )
    }) {
        Spacer(Modifier.height(5.dp))
        CustomTextField(bucketId, "BucketId")
        CustomTextField(fileId, "FileId")
        CustomTextField(name, "Name")
        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val data = account.updateFile(bucketId.value, fileId.value, name.value)
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
fun deleteFile(account: Storage) {
    val output: MutableState<Any> = remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(false) }

    val bucketId = remember { mutableStateOf("652ece6fdfdf1af6e951") }
    val fileId = remember { mutableStateOf("") }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().drawBehind {
        drawLine(
            color = Color.LightGray,
            start = Offset(0f, size.height),
            end = Offset(size.width, size.height),
            strokeWidth = 1f
        )
    }) {
        Spacer(Modifier.height(5.dp))
        CustomTextField(bucketId, "BucketId")
        CustomTextField(fileId, "FileId")
        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val data = account.deleteFile(bucketId.value, fileId.value)
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
fun getFileDownload(account: Storage) {
    val output: MutableState<Any> = remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(false) }

    val bucketId = remember { mutableStateOf("652ece6fdfdf1af6e951") }
    val fileId = remember { mutableStateOf("") }
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
        CustomTextField(bucketId, "BucketId")
        CustomTextField(fileId, "FileId")

        if (bitmap != null){
            Image(bitmap!!, "")
        }

        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val data = account.getFileDownload(bucketId.value, fileId.value)
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
fun getFilePreview(account: Storage) {
    val output: MutableState<Any> = remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(false) }

    val bucketId = remember { mutableStateOf("652ece6fdfdf1af6e951") }
    val fileId = remember { mutableStateOf("") }
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
        CustomTextField(bucketId, "BucketId")
        CustomTextField(fileId, "FileId")
        if (bitmap != null){
            Image(bitmap!!, "")
        }
        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val data = account.getFilePreview(bucketId.value, fileId.value)
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
fun getFileForView(account: Storage) {
    val output: MutableState<Any> = remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(false) }

    val bucketId = remember { mutableStateOf("652ece6fdfdf1af6e951") }
    val fileId = remember { mutableStateOf("") }
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
        CustomTextField(bucketId, "BucketId")
        CustomTextField(fileId, "FileId")
        if (bitmap != null){
            Image(bitmap!!, "")
        }
        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val data = account.getFileView(bucketId.value, fileId.value)
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