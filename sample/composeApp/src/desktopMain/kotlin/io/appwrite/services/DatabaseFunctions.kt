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
fun listDocumentDatabase(database: Databases) {
    val output: MutableState<Any> = remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(false) }

    val databaseId = remember { mutableStateOf("") }
    val collectionId = remember { mutableStateOf("") }
    val queries = remember { mutableStateOf("") }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().drawBehind {
        drawLine(
            color = Color.LightGray,
            start = Offset(0f, size.height),
            end = Offset(size.width, size.height),
            strokeWidth = 1f
        )
    }) {
        Spacer(Modifier.height(5.dp))
        CustomTextField(databaseId, "databaseId")
        CustomTextField(collectionId, "collectionId")
        Spacer(Modifier.height(10.dp))
        Text("Add the elements list separated by commas", color = Color.White, fontSize = 10.sp)
        CustomTextField(queries, "Queries")
        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val query = if(queries.value.isNotEmpty()) queries.value.split(',') else null
                    val data = database.listDocumentsDatabase(databaseId.value, collectionId.value, query)
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
fun createDocumentDatabase(database: Databases) {
    val output: MutableState<Any> = remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(false) }

    val databaseId = remember { mutableStateOf("652eb54994b36d0d7f79") }
    val collectionId = remember { mutableStateOf("652eb55b8277dd84011f") }
    val documentData = remember { mutableStateOf("") }
    val permissions = remember { mutableStateOf("") }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().drawBehind {
        drawLine(
            color = Color.LightGray,
            start = Offset(0f, size.height),
            end = Offset(size.width, size.height),
            strokeWidth = 1f
        )
    }) {
        Spacer(Modifier.height(5.dp))
        CustomTextField(databaseId, "databaseId")
        CustomTextField(collectionId, "collectionId")
        CustomTextField(documentData, "Data")
        Spacer(Modifier.height(10.dp))
        Text("Add the elements list separated by commas", color = Color.White, fontSize = 10.sp)
        CustomTextField(permissions, "Permissions")
        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val permission = if(permissions.value.isNotEmpty()) permissions.value.split(',') else null
                    val data = database.createDocumentDatabase(databaseId.value, collectionId.value, ID.unique(), documentData.value, permission)
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
fun getDocumentDatabase(database: Databases) {
    val output: MutableState<Any> = remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(false) }

    val databaseId = remember { mutableStateOf("") }
    val collectionId = remember { mutableStateOf("") }
    val documentId = remember { mutableStateOf("") }
    val queries = remember { mutableStateOf("") }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().drawBehind {
        drawLine(
            color = Color.LightGray,
            start = Offset(0f, size.height),
            end = Offset(size.width, size.height),
            strokeWidth = 1f
        )
    }) {
        Spacer(Modifier.height(5.dp))
        CustomTextField(databaseId, "databaseId")
        CustomTextField(collectionId, "collectionId")
        CustomTextField(documentId, "documentId")
        Spacer(Modifier.height(10.dp))
        Text("Add the elements list separated by commas", color = Color.White, fontSize = 10.sp)
        CustomTextField(queries, "Queries")
        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val query = if(queries.value.isNotEmpty()) queries.value.split(',') else null
                    val data = database.getDocumentDatabase(databaseId.value, collectionId.value, documentId.value, query)
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
fun updateDocumentDatabase(database: Databases) {
    val output: MutableState<Any> = remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(false) }

    val databaseId = remember { mutableStateOf("") }
    val collectionId = remember { mutableStateOf("") }
    val documentId = remember { mutableStateOf("") }
    val documentData = remember { mutableStateOf("") }
    val permissions = remember { mutableStateOf("") }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().drawBehind {
        drawLine(
            color = Color.LightGray,
            start = Offset(0f, size.height),
            end = Offset(size.width, size.height),
            strokeWidth = 1f
        )
    }) {
        Spacer(Modifier.height(5.dp))
        CustomTextField(databaseId, "databaseId")
        CustomTextField(collectionId, "collectionId")
        CustomTextField(documentId, "documentId")
        CustomTextField(documentData, "Data")
        Spacer(Modifier.height(10.dp))
        Text("Add the elements list separated by commas", color = Color.White, fontSize = 10.sp)
        CustomTextField(permissions, "Permissions")
        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val permission = if(permissions.value.isNotEmpty()) permissions.value.split(',') else null
                    val data = database.updateDocumentDatabase(databaseId.value, collectionId.value, documentId.value, documentData.value, permission)
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
fun deleteDocumentDatabase(database: Databases) {
    val output: MutableState<Any> = remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(false) }

    val databaseId = remember { mutableStateOf("") }
    val collectionId = remember { mutableStateOf("") }
    val documentId = remember { mutableStateOf("") }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().drawBehind {
        drawLine(
            color = Color.LightGray,
            start = Offset(0f, size.height),
            end = Offset(size.width, size.height),
            strokeWidth = 1f
        )
    }) {
        Spacer(Modifier.height(5.dp))
        CustomTextField(databaseId, "databaseId")
        CustomTextField(collectionId, "collectionId")
        CustomTextField(documentId, "documentId")
        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val data = database.deleteDocument(databaseId.value, collectionId.value, documentId.value)
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