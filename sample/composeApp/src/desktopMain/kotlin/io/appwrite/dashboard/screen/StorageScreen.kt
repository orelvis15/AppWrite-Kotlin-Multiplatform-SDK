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
fun storageScreen() {

    val scrollToIndex = remember { mutableStateOf(0) }
    val scrollState = rememberLazyListState()
    val scrollStateIndex = rememberLazyListState()
    var currentIndex: Int by remember { mutableStateOf(0) }

    val client = Client("https://cloud.appwrite.io/v1", "", false)
        .project("651ffe9ce6229e1f5efb")

    val storage = Storage(client)

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
                            buildForms(dto.id, storage)
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
private fun buildForms(index: Int, storage: Storage) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().border(0.5.dp, Color.LightGray)
    ) {
        Spacer(Modifier.height(5.dp))
        androidx.compose.material3.Text("Test it", color = Color.White)
        Spacer(Modifier.height(5.dp))
        when (index) {
            0 -> listFiles(storage)
            1 -> createFiles(storage)
            2 -> getFile(storage)
            3 -> updateFile(storage)
            4 -> deleteFile(storage)
            5 -> getFileDownload(storage)
            6 -> getFilePreview(storage)
            7 -> getFileForView(storage)
        }
    }
}

private fun getElementList(): List<ItemList> {
    return listOf(
        ItemList(0, "List Files", "Get a list of all the user files. You can use the query params to filter your results"),
        ItemList(1, "Create File", "Create a new file. Before using this route, you should create a new bucket resource using either a [server integration](/docs/server/storage#storageCreateBucket) API or directly from your Appwrite console.Larger files should be uploaded using multiple requests with the [content-range](https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Content-Range) header to send a partial request with a maximum supported chunk of `5MB`. The `content-range` header values should always be in bytes.When the first request is sent, the server will return the **File** object, and the subsequent part request must include the file&#039;s **id** in `x-appwrite-id` header to allow the server to know that the partial upload is for the existing file and not for a new one.If you&#039;re creating a new file using one of the Appwrite SDKs, all the chunking logic will be managed by the SDK internally"),
        ItemList(2, "Get File", "Get a file by its unique ID. This endpoint response returns a JSON object with the file metadata"),
        ItemList(3, "Update File", "Update a file by its unique ID. Only users with write permissions have access to update this resource"),
        ItemList(4, "Delete File", "Delete a file by its unique ID. Only users with write permissions have access to delete this resource"),
        ItemList(5, "Get File for Download", "Get a file content by its unique ID. The endpoint response return with a &#039;Content-Disposition: attachment&#039; header that tells the browser to start downloading the file to user downloads directory"),
        ItemList(6, "Get File Preview", "Get a file preview image. Currently, this method supports preview for image files (jpg, png, and gif), other supported formats, like pdf, docs, slides, and spreadsheets, will return the file icon image. You can also pass query string arguments for cutting and resizing your preview image. Preview is supported only for image files smaller than 10MB"),
        ItemList(7, "Get File for View", "Get a file content by its unique ID. This endpoint is similar to the download method but returns with no  &#039;Content-Disposition: attachment&#039; header"),
    )
}