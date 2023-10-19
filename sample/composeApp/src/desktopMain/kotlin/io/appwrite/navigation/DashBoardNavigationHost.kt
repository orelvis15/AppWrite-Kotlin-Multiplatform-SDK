package io.appwrite.navigation

import androidx.compose.runtime.Composable
import io.appwrite.dashboard.screen.*
import io.appwrite.models.context.Context

@Composable
fun customNavigationHost(
    context: Context
) {
    NavigationHost(context.dashboardNavController) {
        composable(Screen.Account.name) {
            accountScreen()
        }

        composable(Screen.Team.name) {
            teamScreen()
        }

        composable(Screen.Database.name) {
            databaseScreen()
        }

        composable(Screen.Locale.name) {
            localeScreen()
        }

        composable(Screen.Functions.name) {
            functionsScreen()
        }
        composable(Screen.Graphql.name) {
            graphqlScreen()
        }

        composable(Screen.Storage.name) {
            storageScreen()
        }

        composable(Screen.Avatar.name) {
            avatarScreen()
        }

        composable(Screen.RealTime.name) {
            realTimeScreen()
        }
    }.build()
}