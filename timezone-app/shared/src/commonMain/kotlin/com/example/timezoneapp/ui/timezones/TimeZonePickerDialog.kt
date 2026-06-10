package com.example.timezoneapp.ui.timezones

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.timezoneapp.DateTimeProvider
import com.example.timezoneapp.theme.AppColors
import com.example.timezoneapp.ui.dialog.showDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeZonePickerDialog(
    dateTimeProvider: DateTimeProvider,
    selectedTimeZones: SnapshotStateList<String>,
    onDismiss: () -> Unit
) {
    val allTimezones = remember { dateTimeProvider.getAvailableTimeZones() }
    val tempSelected = remember { 
        mutableStateListOf<String>().apply { 
            addAll(selectedTimeZones) 
        } 
    }
    var searchQuery by remember { mutableStateOf("") }
    
    val filteredTimezones = remember(searchQuery) {
        allTimezones.filter { it.contains(searchQuery, ignoreCase = true) }
    }

    showDialog(
        title = "Select Timezones",
        onDismiss = onDismiss
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 400.dp)
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text("Search timezone...") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = AppColors.primary,
                    focusedLabelColor = AppColors.primary,
                    cursorColor = AppColors.primary
                ),
                singleLine = true
            )
            
            if (filteredTimezones.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No timezones found",
                        style = MaterialTheme.typography.bodyMedium,
                        color = AppColors.textSecondary
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    items(filteredTimezones) { zone ->
                        val isChecked = tempSelected.contains(zone)
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    if (isChecked) {
                                        tempSelected.remove(zone)
                                    } else {
                                        tempSelected.add(zone)
                                    }
                                }
                                .padding(vertical = 8.dp, horizontal = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = isChecked,
                                onCheckedChange = { checked ->
                                    if (checked == true) {
                                        if (!tempSelected.contains(zone)) tempSelected.add(zone)
                                    } else {
                                        tempSelected.remove(zone)
                                    }
                                },
                                colors = CheckboxDefaults.colors(
                                    checkedColor = AppColors.primary
                                )
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = zone,
                                style = MaterialTheme.typography.bodyMedium,
                                color = AppColors.text
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(
                    onClick = onDismiss,
                    colors = ButtonDefaults.textButtonColors(contentColor = AppColors.primary)
                ) {
                    Text("Cancel")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = {
                        selectedTimeZones.clear()
                        selectedTimeZones.addAll(tempSelected)
                        onDismiss()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = AppColors.primary)
                ) {
                    Text("OK", color = androidx.compose.ui.graphics.Color.White)
                }
            }
        }
    }
}
