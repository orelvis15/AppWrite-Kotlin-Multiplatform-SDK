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
import io.appwrite.output
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun listExecutionsFunctions(function: Functions) {
    val output: MutableState<Any> = remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(false) }

    val functionId = remember { mutableStateOf("") }
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
        Spacer(Modifier.height(5.dp))
        CustomTextField(functionId, "FunctionId")
        Spacer(Modifier.height(10.dp))
        Text("Add the elements list separated by commas", color = Color.White, fontSize = 10.sp)
        CustomTextField(queries, "Queries")
        CustomTextField(search, "collectionId")

        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val query = if(queries.value.isNotEmpty()) queries.value.split(',') else null
                    val data = function.listExecutions(functionId.value, query, search.value)
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
fun createExecutionsFunctions(function: Functions) {
    val output: MutableState<Any> = remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(false) }

    val functionId = remember { mutableStateOf("") }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().drawBehind {
        drawLine(
            color = Color.LightGray,
            start = Offset(0f, size.height),
            end = Offset(size.width, size.height),
            strokeWidth = 1f
        )
    }) {
        Spacer(Modifier.height(5.dp))
        CustomTextField(functionId, "FunctionId")

        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val data = function.createExecution(functionId.value)
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
fun getExecutionsFunctions(function: Functions) {
    val output: MutableState<Any> = remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(false) }

    val functionId = remember { mutableStateOf("") }
    val executionId = remember { mutableStateOf("") }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().drawBehind {
        drawLine(
            color = Color.LightGray,
            start = Offset(0f, size.height),
            end = Offset(size.width, size.height),
            strokeWidth = 1f
        )
    }) {
        Spacer(Modifier.height(5.dp))
        CustomTextField(functionId, "FunctionId")
        CustomTextField(executionId, "ExecutionId")

        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val data = function.getExecution(functionId.value, executionId.value)
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