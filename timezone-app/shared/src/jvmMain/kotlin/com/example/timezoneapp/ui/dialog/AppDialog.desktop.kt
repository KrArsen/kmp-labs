package com.example.timezoneapp.ui.dialog

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.rememberWindowState
import com.example.timezoneapp.theme.AppColors
import com.example.timezoneapp.theme.AppTheme

@Composable
actual fun showDialog(title: String, onDismiss: () -> Unit, content: @Composable () -> Unit) {
    Window(
        onCloseRequest = onDismiss,
        title = title,
        state = rememberWindowState(width = 450.dp, height = 550.dp)
    ) {
        AppTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = AppColors.background
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    content()
                }
            }
        }
    }
}
