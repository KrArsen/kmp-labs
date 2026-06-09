package com.example.timezoneapp.ui.timezones

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.timezoneapp.DateTimeProvider
import com.example.timezoneapp.theme.AppColors
import kotlinx.coroutines.delay
import kotlin.time.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.offsetAt

@Composable
fun TimeZonesScreen(
    dateTimeProvider: DateTimeProvider,
    selectedTimeZones: SnapshotStateList<String>
) {
    var showDialog by remember { mutableStateOf(false) }
    
    // Ticker to update times dynamically every second
    var tick by remember { mutableStateOf(0) }
    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            tick++
        }
    }

    val localZoneId = remember { dateTimeProvider.getCurrentTimeZone() }
    val currentDateTimeStr = remember(tick) { dateTimeProvider.getCurrentDateTime() }
    val (localDate, localTime) = formatDateTimeString(currentDateTimeStr)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Local Timezone Card with beautiful gradient
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Transparent)
            ) {
                Box(
                    modifier = Modifier
                        .background(
                            Brush.linearGradient(
                                colors = listOf(
                                    AppColors.primary,
                                    Color(0xFF8E2DE2), // premium violet
                                    Color(0xFF4A00E0)
                                )
                            )
                        )
                        .padding(24.dp)
                        .fillMaxWidth()
                ) {
                    Column {
                        Text(
                            text = "MY LOCATION",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.White.copy(alpha = 0.7f),
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.5.sp
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = localZoneId,
                            style = MaterialTheme.typography.titleLarge,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = localTime,
                            fontSize = 48.sp,
                            fontWeight = FontWeight.Light,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = formatDate(localDate),
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.White.copy(alpha = 0.8f)
                        )
                    }
                }
            }

            Text(
                text = "Monitored Timezones",
                style = MaterialTheme.typography.titleMedium,
                color = AppColors.text,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(horizontal = 4.dp)
            )

            if (selectedTimeZones.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No timezones added yet.\nTap + to add some!",
                        style = MaterialTheme.typography.bodyLarge,
                        color = AppColors.textSecondary,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(selectedTimeZones) { zone ->
                        val zoneTimeStr = remember(tick) { dateTimeProvider.getCurrentDateTimeInZone(zone) }
                        val (_, zoneTime) = formatDateTimeString(zoneTimeStr)
                        val offsetLabel = getOffsetLabel(localZoneId, zone)

                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = AppColors.surface),
                            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = zone,
                                        style = MaterialTheme.typography.bodyLarge,
                                        color = AppColors.text,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = offsetLabel,
                                        style = MaterialTheme.typography.labelSmall,
                                        color = AppColors.textSecondary
                                    )
                                }
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Text(
                                        text = zoneTime,
                                        style = MaterialTheme.typography.titleLarge,
                                        color = AppColors.primary,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                    IconButton(
                                        onClick = { selectedTimeZones.remove(zone) }
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Delete,
                                            contentDescription = "Remove timezone",
                                            tint = MaterialTheme.colorScheme.error.copy(alpha = 0.8f)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        // Floating Action Button to add timezone
        FloatingActionButton(
            onClick = { showDialog = true },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            containerColor = AppColors.primary,
            contentColor = Color.White,
            shape = CircleShape
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add Timezone"
            )
        }
    }

    if (showDialog) {
        TimeZonePickerDialog(
            dateTimeProvider = dateTimeProvider,
            selectedTimeZones = selectedTimeZones,
            onDismiss = { showDialog = false }
        )
    }
}

// Format LocalDateTime string to Pair of (Date, Time)
private fun formatDateTimeString(dtStr: String): Pair<String, String> {
    return try {
        val parts = dtStr.split('T')
        val datePart = parts[0]
        val timePart = parts[1].split('.')[0].substring(0, 5) // HH:MM
        Pair(datePart, timePart)
    } catch (e: Exception) {
        Pair(dtStr, "")
    }
}

// Convert "2026-06-10" -> "10 Jun 2026"
private fun formatDate(dateStr: String): String {
    return try {
        val parts = dateStr.split('-')
        val year = parts[0]
        val month = parts[1].toInt()
        val day = parts[2].toInt()
        val months = listOf("", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec")
        "$day ${months[month]} $year"
    } catch (e: Exception) {
        dateStr
    }
}

// Compute difference relative to local timezone
private fun getOffsetLabel(localZoneId: String, targetZoneId: String): String {
    return try {
        val instant = Clock.System.now()
        val localOffset = TimeZone.of(localZoneId).offsetAt(instant)
        val targetOffset = TimeZone.of(targetZoneId).offsetAt(instant)
        val diffSeconds = targetOffset.totalSeconds - localOffset.totalSeconds
        val diffHours = diffSeconds / 3600.0
        
        val sign = if (diffHours >= 0) "+" else ""
        val diffText = if (diffHours == 0.0) "Same time" else "$sign${if (diffHours % 1.0 == 0.0) diffHours.toInt().toString() else diffHours.toString()}h"
        
        val prefix = if (targetOffset.totalSeconds >= 0) "GMT+" else "GMT"
        val offsetHours = targetOffset.totalSeconds / 3600.0
        val offsetText = "$prefix${if (offsetHours % 1.0 == 0.0) offsetHours.toInt().toString() else offsetHours.toString()}"
        
        "$offsetText ($diffText)"
    } catch (e: Exception) {
        ""
    }
}
