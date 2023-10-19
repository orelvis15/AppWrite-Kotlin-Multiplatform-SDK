package io.appwrite.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.appwrite.navigation.customNavigationHost
import io.appwrite.models.context.Context

@Composable
fun dashBoard(context: Context) {
    Box {
        Column(modifier = Modifier.fillMaxSize().background(Color(0XFF141415))) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.height(30.dp).fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.width(10.dp))
            }

            Row {
                barMenu(context.dashboardNavController)
                Column {
                    Box(modifier = Modifier.weight(1f).fillMaxWidth()) {
                        customNavigationHost(context)
                    }
                }
            }
        }
    }
}