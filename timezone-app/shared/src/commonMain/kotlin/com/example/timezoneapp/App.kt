package com.example.timezoneapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.timezoneapp.screens.*
import com.example.timezoneapp.theme.AppColors
import com.example.timezoneapp.theme.AppTheme
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

object Routes {
    const val MAIN = "main"
    const val BUTTONS = "buttons"
    const val CHECKBOXES = "checkboxes"
    const val CHIPS = "chips"
    const val DATEPICKER = "datepicker"
    const val DIALOG = "dialog"
    const val DIVIDER = "divider"
    const val PROGRESS = "progress"
    const val RADIOBUTTONS = "radiobuttons"
    const val SWITCH = "switch"
    const val TIMEPICKER = "timepicker"
}

@Composable
fun App() {
    LaunchedEffect(Unit) {
        Napier.base(DebugAntilog())
    }

    AppTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Routes.MAIN,
            modifier = Modifier.fillMaxSize().background(AppColors.background)
        ) {
            composable(Routes.MAIN) {
                MainScreen(
                    onNavigateTo = { route ->
                        navController.navigate(route)
                    }
                )
            }
            composable(Routes.BUTTONS) {
                ButtonsScreen(onBack = { navController.popBackStack() })
            }
            composable(Routes.CHECKBOXES) {
                CheckboxesScreen(onBack = { navController.popBackStack() })
            }
            composable(Routes.CHIPS) {
                ChipsScreen(onBack = { navController.popBackStack() })
            }
            composable(Routes.DATEPICKER) {
                DatepickerScreen(onBack = { navController.popBackStack() })
            }
            composable(Routes.DIALOG) {
                DialogScreen(onBack = { navController.popBackStack() })
            }
            composable(Routes.DIVIDER) {
                DividerScreen(onBack = { navController.popBackStack() })
            }
            composable(Routes.PROGRESS) {
                ProgressBarScreen(onBack = { navController.popBackStack() })
            }
            composable(Routes.RADIOBUTTONS) {
                RadioButtonsScreen(onBack = { navController.popBackStack() })
            }
            composable(Routes.SWITCH) {
                SwitchScreen(onBack = { navController.popBackStack() })
            }
            composable(Routes.TIMEPICKER) {
                TimepickerScreen(onBack = { navController.popBackStack() })
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(onNavigateTo: (String) -> Unit) {
    val menuItems = remember {
        listOf(
            "Buttons Demo" to Routes.BUTTONS,
            "Checkboxes Demo" to Routes.CHECKBOXES,
            "Chips Demo" to Routes.CHIPS,
            "DatePicker Demo" to Routes.DATEPICKER,
            "Dialog Demo" to Routes.DIALOG,
            "Divider Demo" to Routes.DIVIDER,
            "Progress Bar Demo" to Routes.PROGRESS,
            "RadioButtons Demo" to Routes.RADIOBUTTONS,
            "Switch Demo" to Routes.SWITCH,
            "TimePicker Demo" to Routes.TIMEPICKER
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Component Catalog") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = AppColors.surface,
                    titleContentColor = AppColors.text
                )
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(AppColors.background)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(menuItems) { (title, route) ->
                ElevatedButton(
                    onClick = { onNavigateTo(route) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .shadow(elevation = 2.dp, shape = RoundedCornerShape(12.dp)),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.elevatedButtonColors(
                        containerColor = AppColors.primary,
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}