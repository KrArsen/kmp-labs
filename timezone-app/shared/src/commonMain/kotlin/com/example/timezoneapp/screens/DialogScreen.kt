package com.example.timezoneapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.timezoneapp.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogScreen(onBack: () -> Unit) {
    var showDialog by remember { mutableStateOf(false) }

    AppTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Dialog") },
                    navigationIcon = {
                        IconButton(onClick = onBack) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                        }
                    }
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(onClick = { showDialog = true }) {
                    Text("Show Dialog")
                }

                if (showDialog) {
                    AlertDialog(
                        onDismissRequest = { showDialog = false },
                        icon = { Icon(Icons.Default.Info, contentDescription = null) },
                        title = { Text("Information") },
                        text = { Text("This is a demonstration of AlertDialog in Jetpack Compose.") },
                        confirmButton = {
                            TextButton(onClick = { showDialog = false }) {
                                Text("Confirm")
                            }
                        },
                        dismissButton = {
                            TextButton(onClick = { showDialog = false }) {
                                Text("Dismiss")
                            }
                        }
                    )
                }
            }
        }
    }
}
