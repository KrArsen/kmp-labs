package com.example.timezoneapp.screens

import androidx.compose.foundation.clickable
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
fun RadioButtonsScreen(onBack: () -> Unit) {
    val options = listOf("Option 1", "Option 2", "Option 3")
    var selectedOption by remember { mutableStateOf(options[0]) }

    AppTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Radio Buttons") },
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
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text("Select an Option", style = MaterialTheme.typography.titleLarge)
                
                options.forEach { option ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { selectedOption = option }
                            .padding(vertical = 8.dp)
                    ) {
                        RadioButton(
                            selected = (option == selectedOption),
                            onClick = { selectedOption = option }
                        )
                        Text(
                            text = option,
                            modifier = Modifier.padding(start = 8.dp),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                Text("Selected: $selectedOption", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}
