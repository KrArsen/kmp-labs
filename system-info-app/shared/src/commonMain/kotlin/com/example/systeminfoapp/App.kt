package com.example.systeminfoapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.example.systeminfoapp.ui.AboutScreen
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

@Composable
@Preview
fun App() {
    remember {
        Napier.base(DebugAntilog())
    }
    AboutScreen()
}