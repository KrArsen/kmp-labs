package com.example.timezoneapp.ui.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.timezoneapp.theme.AppColors

@Composable
actual fun showDialog(title: String, onDismiss: () -> Unit, content: @Composable () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(title) },
        text = content,
        confirmButton = {},
        containerColor = AppColors.surface
    )
}
