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
fun DatepickerScreen(onBack: () -> Unit) {
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    var selectedDate by remember { mutableStateOf("No date selected") }

    AppTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("DatePicker") },
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
                    text = selectedDate,
                    style = MaterialTheme.typography.bodyLarge
                )

                Button(onClick = { showDatePicker = true }) {
                    Text("Select Date")
                }

                if (showDatePicker) {
                    DatePickerDialog(
                        onDismissRequest = { showDatePicker = false },
                        confirmButton = {
                            TextButton(
                                onClick = {
                                    val timeInMillis = datePickerState.selectedDateMillis
                                    selectedDate = if (timeInMillis != null) {
                                        "Selected Millis: $timeInMillis"
                                    } else {
                                        "No date selected"
                                    }
                                    showDatePicker = false
                                }
                            ) {
                                Text("OK")
                            }
                        },
                        dismissButton = {
                            TextButton(onClick = { showDatePicker = false }) {
                                Text("Cancel")
                            }
                        }
                    ) {
                        DatePicker(state = datePickerState)
                    }
                }
            }
        }
    }
}
