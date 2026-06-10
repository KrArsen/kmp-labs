package com.example.systeminfoapp

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport

import org.koin.core.context.startKoin
import com.example.systeminfoapp.di.appModule

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    startKoin {
        modules(appModule)
    }
    ComposeViewport {
        App()
    }
}