package com.example.systeminfoapp

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.systeminfoapp.ui.AboutScreen
import com.example.systeminfoapp.ui.reminders.RemindersScreen
import com.example.systeminfoapp.ui.theme.AppTheme
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

enum class Screen {
    About, Reminders
}

@Composable
@Preview
fun App() {
    remember {
        Napier.base(DebugAntilog())
    }

    var currentScreen by remember { mutableStateOf(Screen.About) }

    AppTheme {
        Scaffold(
            bottomBar = {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.surface,
                    tonalElevation = NavigationBarDefaults.Elevation
                ) {
                    NavigationBarItem(
                        selected = currentScreen == Screen.About,
                        onClick = { currentScreen = Screen.About },
                        label = { Text("About") },
                        icon = { Text("ℹ️", fontSize = 20.sp) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                            unselectedIconColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                            unselectedTextColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        )
                    )
                    NavigationBarItem(
                        selected = currentScreen == Screen.Reminders,
                        onClick = { currentScreen = Screen.Reminders },
                        label = { Text("Reminders") },
                        icon = { Text("🔔", fontSize = 20.sp) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                            unselectedIconColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                            unselectedTextColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        )
                    )
                }
            }
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                Crossfade(
                    targetState = currentScreen,
                    modifier = Modifier.fillMaxSize()
                ) { screen ->
                    when (screen) {
                        Screen.About -> AboutScreen()
                        Screen.Reminders -> RemindersScreen()
                    }
                }
            }
        }
    }
}