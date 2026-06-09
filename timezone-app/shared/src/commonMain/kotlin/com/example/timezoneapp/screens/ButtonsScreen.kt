package com.example.timezoneapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.timezoneapp.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ButtonsScreen(onBack: () -> Unit) {
    AppTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Buttons") },
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
                ElevatedButton(onClick = {}) {
                    Text("Elevated Button")
                }
                
                OutlinedButton(onClick = {}) {
                    Text("Outlined Button")
                }
                
                TextButton(onClick = {}) {
                    Text("Text Button")
                }
                
                FilledTonalButton(onClick = {}) {
                    Text("Filled Tonal Button")
                }
            }
        }
    }
}
