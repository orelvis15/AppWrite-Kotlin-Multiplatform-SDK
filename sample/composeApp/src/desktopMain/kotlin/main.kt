import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import io.appwrite.navigation.mainNavigationHost
import io.appwrite.navigation.rememberNavController
import io.appwrite.models.context.Context
import io.appwrite.navigation.Screen
import java.awt.Dimension

fun main() = application {

    val dashboardNavController by rememberNavController(Screen.Account.name)
    val mainNavController by rememberNavController(Screen.DashBoard.name)

    val context = Context(
        dashboardNavController = dashboardNavController,
        mainNavController = mainNavController
    )

    Window(
        title = "Multiplatform Appwrite SDK",
        state = rememberWindowState(width = 1000.dp, height = 800.dp),
        onCloseRequest = ::exitApplication,
    ) {
        window.minimumSize = Dimension(350, 600)
        MaterialTheme {
            mainNavigationHost(context)
        }
    }
}