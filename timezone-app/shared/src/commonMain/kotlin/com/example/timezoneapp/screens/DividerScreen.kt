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
fun DividerScreen(onBack: () -> Unit) {
    AppTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Divider") },
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
                verticalArrangement = Arrangement.spacedBy(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Horizontal Divider Demo", style = MaterialTheme.typography.titleLarge)
                
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Item Above")
                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                    Text("Item Below")
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text("Vertical Divider Demo", style = MaterialTheme.typography.titleLarge)
                
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Left Side")
                    VerticalDivider(modifier = Modifier.padding(horizontal = 16.dp))
                    Text("Right Side")
                }
            }
        }
    }
}
