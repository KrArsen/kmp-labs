import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.example.timezoneapp.App

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Timezone App") {
        App()
    }
}
