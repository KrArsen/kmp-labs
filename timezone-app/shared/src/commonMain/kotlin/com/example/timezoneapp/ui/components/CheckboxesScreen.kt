package com.example.timezoneapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckboxesScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Checkboxes Demo") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var checkedVal1 by remember { mutableStateOf(false) }
            var checkedVal2 by remember { mutableStateOf(true) }
            
            // For tri-state checkbox demo
            var parentState by remember { mutableStateOf(ToggleableState.Indeterminate) }
            var childState1 by remember { mutableStateOf(false) }
            var childState2 by remember { mutableStateOf(true) }

            // Parent state calculation
            parentState = if (childState1 && childState2) {
                ToggleableState.On
            } else if (!childState1 && !childState2) {
                ToggleableState.Off
            } else {
                ToggleableState.Indeterminate
            }

            ElevatedCard(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text("Basic Checkboxes", style = MaterialTheme.typography.titleMedium)
                    
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { checkedVal1 = !checkedVal1 }
                            .padding(vertical = 8.dp)
                    ) {
                        Checkbox(
                            checked = checkedVal1,
                            onCheckedChange = { checkedVal1 = it }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Option 1 (Starts unchecked)")
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { checkedVal2 = !checkedVal2 }
                            .padding(vertical = 8.dp)
                    ) {
                        Checkbox(
                            checked = checkedVal2,
                            onCheckedChange = { checkedVal2 = it }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Option 2 (Starts checked)")
                    }
                }
            }

            ElevatedCard(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text("Tri-State / Parent-Child Checkboxes", style = MaterialTheme.typography.titleMedium)

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                val newState = parentState != ToggleableState.On
                                childState1 = newState
                                childState2 = newState
                            }
                            .padding(vertical = 8.dp)
                    ) {
                        TriStateCheckbox(
                            state = parentState,
                            onClick = {
                                val newState = parentState != ToggleableState.On
                                childState1 = newState
                                childState2 = newState
                            }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Select All", style = MaterialTheme.typography.bodyLarge)
                    }

                    Column(modifier = Modifier.padding(start = 24.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { childState1 = !childState1 }
                                .padding(vertical = 8.dp)
                        ) {
                            Checkbox(
                                checked = childState1,
                                onCheckedChange = { childState1 = it }
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Child Option 1")
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { childState2 = !childState2 }
                                .padding(vertical = 8.dp)
                        ) {
                            Checkbox(
                                checked = childState2,
                                onCheckedChange = { childState2 = it }
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Child Option 2")
                        }
                    }
                }
            }

            ElevatedCard(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text("Disabled Checkboxes", style = MaterialTheme.typography.titleMedium)
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                    ) {
                        Checkbox(
                            checked = true,
                            onCheckedChange = null,
                            enabled = false
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Disabled Checked")
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                    ) {
                        Checkbox(
                            checked = false,
                            onCheckedChange = null,
                            enabled = false
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Disabled Unchecked")
                    }
                }
            }
        }
    }
}
