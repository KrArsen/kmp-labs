package com.example.timezoneapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.timezoneapp.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimepickerScreen(onBack: () -> Unit) {
    var showTimePicker by remember { mutableStateOf(false) }
    val timePickerState = rememberTimePickerState()
    var selectedTime by remember { mutableStateOf("No time selected") }

    AppTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("TimePicker") },
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
                Text(
                    text = selectedTime,
                    style = MaterialTheme.typography.bodyLarge
                )

                Button(onClick = { showTimePicker = true }) {
                    Text("Select Time")
                }

                if (showTimePicker) {
                    AlertDialog(
                        onDismissRequest = { showTimePicker = false },
                        confirmButton = {
                            TextButton(
                                onClick = {
                                    val hour = timePickerState.hour
                                    val minute = timePickerState.minute
                                    selectedTime = "Selected Time: ${hour.toString().padStart(2, '0')}:${minute.toString().padStart(2, '0')}"
                                    showTimePicker = false
                                }
                            ) {
                                Text("OK")
                            }
                        },
                        dismissButton = {
                            TextButton(onClick = { showTimePicker = false }) {
                                Text("Cancel")
                            }
                        },
                        text = {
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                TimePicker(state = timePickerState)
                            }
                        }
                    )
                }
            }
        }
    }
}
