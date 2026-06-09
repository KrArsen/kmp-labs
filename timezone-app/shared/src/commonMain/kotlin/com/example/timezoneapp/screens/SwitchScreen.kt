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
fun SwitchScreen(onBack: () -> Unit) {
    var isChecked by remember { mutableStateOf(false) }

    AppTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Switch") },
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
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { isChecked = !isChecked }
                        .padding(vertical = 8.dp)
                ) {
                    Text("Toggle Setting Option", style = MaterialTheme.typography.bodyLarge)
                    Switch(
                        checked = isChecked,
                        onCheckedChange = { isChecked = it }
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Current State: ${if (isChecked) "ON" else "OFF"}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
