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
fun databaseScreen() {

    val scrollToIndex = remember { mutableStateOf(0) }
    val scrollState = rememberLazyListState()
    val scrollStateIndex = rememberLazyListState()
    var currentIndex: Int by remember { mutableStateOf(0) }

    val client = Client("https://cloud.appwrite.io/v1", "", false)
        .project("651ffe9ce6229e1f5efb")

    val database = Databases(client)

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
                            buildForms(dto.id, database)
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
private fun buildForms(index: Int, database: Databases) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().border(0.5.dp, Color.LightGray)
    ) {
        Spacer(Modifier.height(5.dp))
        androidx.compose.material3.Text("Test it", color = Color.White)
        Spacer(Modifier.height(5.dp))
        when (index) {
            0 -> listDocumentDatabase(database)
            1 -> createDocumentDatabase(database)
            2 -> getDocumentDatabase(database)
            3 -> updateDocumentDatabase(database)
            4 -> deleteDocumentDatabase(database)
        }
    }
}

private fun getElementList(): List<ItemList> {
    return listOf(
        ItemList(0, "List Documents", "Get a list of all the user&#039;s documents in a given collection. You can use the query params to filter your results"),
        ItemList(1, "Create Document", "Create a new Document. Before using this route, you should create a new collection resource using either a [server integration](/docs/server/databases#databasesCreateCollection) API or directly from your database console"),
        ItemList(2, "Get Document", "Get a document by its unique ID. This endpoint response returns a JSON object with the document data"),
        ItemList(3, "Update Document", "Update a document by its unique ID. Using the patch method you can pass only specific fields that will get updated"),
        ItemList(4, "Delete Document", "Delete a document by its unique ID"),
    )
}