package com.example.timezoneapp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.resources.painterResource

import timezoneapp.shared.generated.resources.Res
import timezoneapp.shared.generated.resources.compose_multiplatform

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

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

    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(onClick = { showContent = !showContent }) {
                Text("Click me!")
            }
            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text("Compose: $greeting")
                }
            }
            
            if (currentDateTime.isNotEmpty()) {
                Text("Local Time: $currentDateTime")
                Text("Timezone: $currentTimeZone")
            }
        }
    }
}