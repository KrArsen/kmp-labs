package com.example.timezoneapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.timezoneapp.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChipsScreen(onBack: () -> Unit) {
    var filterSelected by remember { mutableStateOf(false) }
    var inputChipVisible by remember { mutableStateOf(true) }

    AppTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Chips") },
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
                Text("Assist Chip", style = MaterialTheme.typography.titleLarge)
                AssistChip(
                    onClick = {},
                    label = { Text("Settings") },
                    leadingIcon = {
                        Icon(
                            Icons.Default.Settings,
                            contentDescription = null,
                            modifier = Modifier.size(AssistChipDefaults.IconSize)
                        )
                    }
                )

                Text("Filter Chip", style = MaterialTheme.typography.titleLarge)
                FilterChip(
                    selected = filterSelected,
                    onClick = { filterSelected = !filterSelected },
                    label = { Text("Filterable Option") },
                    leadingIcon = if (filterSelected) {
                        {
                            Icon(
                                Icons.Default.Check,
                                contentDescription = null,
                                modifier = Modifier.size(FilterChipDefaults.IconSize)
                            )
                        }
                    } else null
                )

                Text("Input Chip", style = MaterialTheme.typography.titleLarge)
                if (inputChipVisible) {
                    InputChip(
                        selected = false,
                        onClick = {},
                        label = { Text("Removable item") },
                        trailingIcon = {
                            IconButton(onClick = { inputChipVisible = false }) {
                                Icon(
                                    Icons.Default.Close,
                                    contentDescription = "Remove",
                                    modifier = Modifier.size(InputChipDefaults.IconSize)
                                )
                            }
                        }
                    )
                } else {
                    TextButton(onClick = { inputChipVisible = true }) {
                        Text("Reset Input Chip")
                    }
                }

                Text("Suggestion Chip", style = MaterialTheme.typography.titleLarge)
                SuggestionChip(
                    onClick = {},
                    label = { Text("Suggestion") }
                )
            }
        }
    }
}
