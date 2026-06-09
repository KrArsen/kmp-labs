package com.example.timezoneapp.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.dp
import com.example.timezoneapp.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckboxesScreen(onBack: () -> Unit) {
    var checked1 by remember { mutableStateOf(false) }
    var checked2 by remember { mutableStateOf(true) }
    
    var child1 by remember { mutableStateOf(false) }
    var child2 by remember { mutableStateOf(false) }
    val parentState = remember(child1, child2) {
        if (child1 && child2) ToggleableState.On
        else if (!child1 && !child2) ToggleableState.Off
        else ToggleableState.Indeterminate
    }

    AppTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Checkboxes") },
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
                Text("Standard Checkboxes", style = MaterialTheme.typography.titleLarge)
                
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { checked1 = !checked1 }
                ) {
                    Checkbox(checked = checked1, onCheckedChange = { checked1 = it })
                    Text("Option A", modifier = Modifier.padding(start = 8.dp))
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { checked2 = !checked2 }
                ) {
                    Checkbox(checked = checked2, onCheckedChange = { checked2 = it })
                    Text("Option B", modifier = Modifier.padding(start = 8.dp))
                }

                Spacer(modifier = Modifier.height(16.dp))
                Text("Tri-State Checkbox", style = MaterialTheme.typography.titleLarge)

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable {
                        val target = parentState != ToggleableState.On
                        child1 = target
                        child2 = target
                    }
                ) {
                    TriStateCheckbox(
                        state = parentState,
                        onClick = {
                            val target = parentState != ToggleableState.On
                            child1 = target
                            child2 = target
                        }
                    )
                    Text("Parent option", modifier = Modifier.padding(start = 8.dp))
                }

                Column(modifier = Modifier.padding(start = 24.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable { child1 = !child1 }
                    ) {
                        Checkbox(checked = child1, onCheckedChange = { child1 = it })
                        Text("Child Option A", modifier = Modifier.padding(start = 8.dp))
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable { child2 = !child2 }
                    ) {
                        Checkbox(checked = child2, onCheckedChange = { child2 = it })
                        Text("Child Option B", modifier = Modifier.padding(start = 8.dp))
                    }
                }
            }
        }
    }
}
