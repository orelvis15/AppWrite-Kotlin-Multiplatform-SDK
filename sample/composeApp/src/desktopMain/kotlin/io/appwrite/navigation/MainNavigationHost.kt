package io.appwrite.navigation

import androidx.compose.runtime.Composable
import io.appwrite.models.context.Context
import io.appwrite.dashboard.dashBoard

@Composable
fun mainNavigationHost(
    context: Context
) {
    NavigationHost(context.mainNavController) {

        composable(Screen.DashBoard.name) {
            dashBoard(context)
        }

    }.build()
}