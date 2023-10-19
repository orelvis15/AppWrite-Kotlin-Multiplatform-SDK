package io.appwrite.dashboard

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.appwrite.models.MainActionDto
import io.appwrite.navigation.NavController
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import io.appwrite.navigation.Screen
import androidx.compose.foundation.lazy.items

@Composable
fun barMenu(navController: NavController) {

    var index: Int by remember { mutableStateOf(0) }
    var showMenuText by remember { mutableStateOf(true) }
    var expanded by remember { mutableStateOf(true) }

    val widthMenu by animateDpAsState(
        if (expanded) 150.dp else 60.dp,
        tween(durationMillis = 200, easing = LinearEasing)
    )

    val actions = remember {
        listOf(
            MainActionDto(0, "Account", Screen.Account),
            MainActionDto(1, "Team", Screen.Team),
            MainActionDto(2, "Database", Screen.Database),
            MainActionDto(3, "Locale", Screen.Locale),
            MainActionDto(4, "Functions", Screen.Functions),
            MainActionDto(5, "Graphql", Screen.Graphql),
            MainActionDto(6, "Storage", Screen.Storage),
            MainActionDto(7, "Avatar", Screen.Avatar),
            //MainActionDto(8, "RealTime", Screen.RealTime),
        )
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxHeight()
            .width(widthMenu)
            .drawBehind {
                drawLine(
                    color = Color.LightGray,
                    start = Offset(size.width, 0f),
                    end = Offset(size.width, size.height),
                    strokeWidth = 1f
                )
            }
    ) {
        LazyColumn(
            contentPadding = PaddingValues(2.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(actions) {
                mainActionScreen(it, index, { myIndex ->
                    index = myIndex
                }, navController, showMenuText)
            }
        }
    }
}