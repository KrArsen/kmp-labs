package com.example.timezoneapp

import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import com.example.timezoneapp.ui.theme.AppTheme
import com.example.timezoneapp.navigation.AppNavigation

@Composable
@Preview
fun App() {
    val provider = remember { DateTimeProviderImpl() }
    var currentDateTime by remember { mutableStateOf("") }
    var currentTimeZone by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        // Initialize Napier logging base
        Napier.base(DebugAntilog())
        
        currentDateTime = provider.getCurrentDateTime()
        currentTimeZone = provider.getCurrentTimeZone()
        
        Napier.i(tag = "App") { "Current date and time: $currentDateTime" }
        Napier.i(tag = "App") { "Current timezone: $currentTimeZone" }
    }

    AppTheme {
        AppNavigation()
    }
}