package com.example.timezoneapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.timezoneapp.ui.MainScreen
import com.example.timezoneapp.ui.components.*

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    
    NavHost(
        navController = navController,
        startDestination = "main"
    ) {
        composable("main") {
            MainScreen(onNavigate = { route -> navController.navigate(route) })
        }
        composable("buttons") {
            ButtonsScreen(onBack = { navController.popBackStack() })
        }
        composable("checkboxes") {
            CheckboxesScreen(onBack = { navController.popBackStack() })
        }
        composable("chips") {
            ChipsScreen(onBack = { navController.popBackStack() })
        }
        composable("datepicker") {
            DatePickerScreen(onBack = { navController.popBackStack() })
        }
        composable("dialog") {
            DialogScreen(onBack = { navController.popBackStack() })
        }
        composable("divider") {
            DividerScreen(onBack = { navController.popBackStack() })
        }
        composable("progressbar") {
            ProgressBarScreen(onBack = { navController.popBackStack() })
        }
        composable("radiobuttons") {
            RadioButtonsScreen(onBack = { navController.popBackStack() })
        }
        composable("switch") {
            SwitchScreen(onBack = { navController.popBackStack() })
        }
        composable("timepicker") {
            TimePickerScreen(onBack = { navController.popBackStack() })
        }
    }
}
