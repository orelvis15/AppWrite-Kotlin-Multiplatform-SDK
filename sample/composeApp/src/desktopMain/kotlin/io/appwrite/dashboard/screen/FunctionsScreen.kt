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
fun functionsScreen() {

    val scrollToIndex = remember { mutableStateOf(0) }
    val scrollState = rememberLazyListState()
    val scrollStateIndex = rememberLazyListState()
    var currentIndex: Int by remember { mutableStateOf(0) }

    val client = Client("https://cloud.appwrite.io/v1", "", false)
        .project("651ffe9ce6229e1f5efb")

    val functions = Functions(client)

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
                            buildForms(dto.id, functions)
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
private fun buildForms(index: Int, functions: Functions) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().border(0.5.dp, Color.LightGray)
    ) {
        Spacer(Modifier.height(5.dp))
        androidx.compose.material3.Text("Test it", color = Color.White)
        Spacer(Modifier.height(5.dp))
        when (index) {
            0 -> listExecutionsFunctions(functions)
            1 -> createExecutionsFunctions(functions)
            2 -> getExecutionsFunctions(functions)
        }
    }
}

private fun getElementList(): List<ItemList> {
    return listOf(
        ItemList(0, "List Executions", "Get a list of all the current user function execution logs. You can use the query params to filter your results"),
        ItemList(1, "Create Execution", "Trigger a function execution. The returned object will return you the current execution status. You can ping the `Get Execution` endpoint to get updates on the current execution status. Once this endpoint is called, your function execution process will start asynchronously"),
        ItemList(2, "Get Execution", "Get a function execution log by its unique ID"),
    )
}