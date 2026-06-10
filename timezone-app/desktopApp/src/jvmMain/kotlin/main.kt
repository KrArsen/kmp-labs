import androidx.compose.runtime.*
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyShortcut
import androidx.compose.ui.window.MenuBar
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.example.timezoneapp.App

fun main() = application {
    val windowIds = remember { mutableStateListOf(0) }
    var nextId by remember { mutableStateOf(1) }

    if (windowIds.isEmpty()) {
        exitApplication()
    }

    windowIds.toList().forEach { id ->
        key(id) {
            Window(
                onCloseRequest = { windowIds.remove(id) },
                title = "Timezone App"
            ) {
                MenuBar {
                    Menu("File") {
                        Item("New Window", shortcut = KeyShortcut(Key.N, ctrl = true)) {
                            windowIds.add(nextId)
                            nextId++
                        }
                        Item("Exit", shortcut = KeyShortcut(Key.Q, ctrl = true)) {
                            exitApplication()
                        }
                    }
                }
                App()
            }
        }
    }
}
