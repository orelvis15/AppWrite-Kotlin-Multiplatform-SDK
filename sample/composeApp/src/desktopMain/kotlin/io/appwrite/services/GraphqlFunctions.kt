package io.appwrite.services

import androidx.compose.foundation.layout.*
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
import io.appwrite.CustomTextField
import io.appwrite.output
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun queryGraphql(graphql: Graphql) {
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
        Spacer(Modifier.height(5.dp))
        CustomTextField(query, "Query")
        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val data = graphql.query(query.value)
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
fun mutationGraphql(graphql: Graphql) {
    val output: MutableState<Any> = remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(false) }

    val mutation = remember { mutableStateOf("") }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().drawBehind {
        drawLine(
            color = Color.LightGray,
            start = Offset(0f, size.height),
            end = Offset(size.width, size.height),
            strokeWidth = 1f
        )
    }) {
        Spacer(Modifier.height(5.dp))
        CustomTextField(mutation, "Mutation")
        CustomButton("Query", loading) {
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val data = graphql.mutation(mutation.value)
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