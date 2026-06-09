package com.example.timezoneapp

import androidx.compose.runtime.*
import com.example.timezoneapp.theme.AppTheme
import com.example.timezoneapp.navigation.Screen
import com.example.timezoneapp.ui.HomeScreen
import com.example.timezoneapp.ui.components.*
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

private var isNapierInitialized = false

@Composable
fun App() {
    if (!isNapierInitialized) {
        Napier.base(DebugAntilog())
        isNapierInitialized = true
    }

    AppTheme {
        var currentScreen: Screen by remember { mutableStateOf<Screen>(Screen.Home) }

        when (currentScreen) {
            is Screen.Home -> HomeScreen(onNavigate = { currentScreen = it })
            is Screen.Buttons -> ButtonsScreen(onBack = { currentScreen = Screen.Home })
            is Screen.Checkboxes -> CheckboxesScreen(onBack = { currentScreen = Screen.Home })
            is Screen.Chips -> ChipsScreen(onBack = { currentScreen = Screen.Home })
            is Screen.Datepicker -> DatePickerScreen(onBack = { currentScreen = Screen.Home })
            is Screen.Dialog -> DialogScreen(onBack = { currentScreen = Screen.Home })
            is Screen.Divider -> DividerScreen(onBack = { currentScreen = Screen.Home })
            is Screen.ProgressBar -> ProgressBarScreen(onBack = { currentScreen = Screen.Home })
            is Screen.RadioButtons -> RadioButtonsScreen(onBack = { currentScreen = Screen.Home })
            is Screen.Switch -> SwitchScreen(onBack = { currentScreen = Screen.Home })
            is Screen.Timepicker -> TimePickerScreen(onBack = { currentScreen = Screen.Home })
        }
    }
}