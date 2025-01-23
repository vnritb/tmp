import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.runtime.Composable

@Composable
fun app() {
    MaterialTheme {
        Text("Hello, World!")
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        app()
    }
}