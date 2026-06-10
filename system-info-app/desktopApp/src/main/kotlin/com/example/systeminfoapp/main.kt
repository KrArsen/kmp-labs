package com.example.systeminfoapp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.koin.core.context.startKoin
import com.example.systeminfoapp.di.appModule

fun main() {
    startKoin {
        modules(appModule)
    }
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "Systeminfoapp",
        ) {
            App()
        }
    }
}