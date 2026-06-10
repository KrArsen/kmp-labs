package com.example.timezoneapp.ui.dialog

import androidx.compose.runtime.Composable

@Composable
expect fun showDialog(title: String, onDismiss: () -> Unit, content: @Composable () -> Unit)
