package com.example.timezoneapp.navigation

sealed class Screen {
    object Home : Screen()
    object Buttons : Screen()
    object Checkboxes : Screen()
    object Chips : Screen()
    object Datepicker : Screen()
    object Dialog : Screen()
    object Divider : Screen()
    object ProgressBar : Screen()
    object RadioButtons : Screen()
    object Switch : Screen()
    object Timepicker : Screen()
}
