package com.example.timezoneapp.ui.meeting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.timezoneapp.DateTimeProvider
import com.example.timezoneapp.theme.AppColors

@Composable
fun MeetingScreen(
    dateTimeProvider: DateTimeProvider,
    selectedTimeZones: List<String>
) {
    var startHour by remember { mutableStateOf(9) }
    var startMinute by remember { mutableStateOf(0) }
    var endHour by remember { mutableStateOf(18) }
    var endMinute by remember { mutableStateOf(0) }
    
    var showResultDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Preferred Meeting Window",
            style = MaterialTheme.typography.titleLarge,
            color = AppColors.text,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start
        )
        
        Text(
            text = "Define the working hours range (in your local time) that should be intersected with other selected timezones.",
            style = MaterialTheme.typography.bodyMedium,
            color = AppColors.textSecondary,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start
        )

        // Custom Time Range Selectors
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TimeRangePicker(
                label = "START TIME",
                hour = startHour,
                minute = startMinute,
                onTimeChanged = { h, m ->
                    startHour = h
                    startMinute = m
                }
            )

            TimeRangePicker(
                label = "END TIME",
                hour = endHour,
                minute = endMinute,
                onTimeChanged = { h, m ->
                    endHour = h
                    endMinute = m
                }
            )
        }

        // Selected Participants / Timezones Summary Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = AppColors.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "TARGET PARTICIPANTS",
                    style = MaterialTheme.typography.labelSmall,
                    color = AppColors.textSecondary,
                    fontWeight = FontWeight.Bold
                )
                if (selectedTimeZones.isEmpty()) {
                    Text(
                        text = "No other timezones selected. Head back to the 'Timezones' tab to add locations.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.error
                    )
                } else {
                    selectedTimeZones.forEach { zone ->
                        Text(
                            text = "• $zone",
                            style = MaterialTheme.typography.bodyMedium,
                            color = AppColors.text
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { showResultDialog = true },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = AppColors.primary,
                disabledContainerColor = AppColors.divider
            ),
            enabled = selectedTimeZones.isNotEmpty()
        ) {
            Text(
                text = "Find Suitable Meeting Time",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }

    if (showResultDialog) {
        MeetingResultDialog(
            dateTimeProvider = dateTimeProvider,
            selectedTimeZones = selectedTimeZones,
            startHour = startHour,
            startMinute = startMinute,
            endHour = endHour,
            endMinute = endMinute,
            onDismiss = { showResultDialog = false }
        )
    }
}

@Composable
private fun TimeRangePicker(
    label: String,
    hour: Int,
    minute: Int,
    onTimeChanged: (Int, Int) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = AppColors.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = AppColors.textSecondary,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                // Hour Stepper
                TimeStepper(
                    value = hour,
                    maxValue = 23,
                    onValueChanged = { onTimeChanged(it, minute) }
                )

                Text(
                    text = ":",
                    style = MaterialTheme.typography.displayMedium.copy(fontSize = 36.sp),
                    color = AppColors.text,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                // Minute Stepper (Increments/decrements by 15 min for convenience)
                TimeStepper(
                    value = minute,
                    maxValue = 59,
                    step = 15,
                    onValueChanged = { onTimeChanged(hour, it) }
                )
            }
        }
    }
}

@Composable
private fun TimeStepper(
    value: Int,
    maxValue: Int,
    step: Int = 1,
    onValueChanged: (Int) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        IconButton(
            onClick = {
                val newValue = (value + step) % (maxValue + 1)
                onValueChanged(newValue)
            }
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowUp,
                contentDescription = "Increment",
                tint = AppColors.primary
            )
        }

        Box(
            modifier = Modifier
                .background(AppColors.background, shape = RoundedCornerShape(12.dp))
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .width(60.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = value.toString().padStart(2, '0'),
                style = MaterialTheme.typography.titleLarge.copy(fontSize = 28.sp),
                color = AppColors.text,
                fontWeight = FontWeight.SemiBold
            )
        }

        IconButton(
            onClick = {
                val newValue = (value - step + (maxValue + 1)) % (maxValue + 1)
                onValueChanged(newValue)
            }
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "Decrement",
                tint = AppColors.primary
            )
        }
    }
}
