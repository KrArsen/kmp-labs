package com.example.timezoneapp.ui.meeting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.timezoneapp.DateTimeProvider
import com.example.timezoneapp.theme.AppColors
import com.example.timezoneapp.ui.dialog.showDialog
import kotlin.time.Clock
import kotlinx.datetime.*

@Composable
fun MeetingResultDialog(
    dateTimeProvider: DateTimeProvider,
    selectedTimeZones: List<String>,
    startHour: Int,
    startMinute: Int,
    endHour: Int,
    endMinute: Int,
    onDismiss: () -> Unit
) {
    val results = remember {
        calculateOverlap(
            dateTimeProvider = dateTimeProvider,
            selectedTimeZones = selectedTimeZones,
            startHour = startHour,
            startMinute = startMinute,
            endHour = endHour,
            endMinute = endMinute
        )
    }

    showDialog(
        title = "Suitable Meeting Slots",
        onDismiss = onDismiss
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 400.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Preferred local window: ${startHour.toString().padStart(2, '0')}:${startMinute.toString().padStart(2, '0')} - ${endHour.toString().padStart(2, '0')}:${endMinute.toString().padStart(2, '0')}",
                style = MaterialTheme.typography.bodyMedium,
                color = AppColors.textSecondary
            )

            if (results.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No overlapping time slots found.\nTry expanding your preferred time range.",
                        style = MaterialTheme.typography.bodyLarge,
                        color = AppColors.textSecondary,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(results) { slot ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(containerColor = AppColors.background)
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text(
                                    text = "${slot.localStart} - ${slot.localEnd} (Local)",
                                    style = MaterialTheme.typography.titleMedium,
                                    color = AppColors.primary,
                                    fontWeight = FontWeight.Bold
                                )
                                
                                HorizontalDivider(color = AppColors.divider)
                                
                                slot.zoneBreakdowns.forEach { breakdown ->
                                    Text(
                                        text = breakdown,
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = AppColors.text
                                    )
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = onDismiss,
                    colors = ButtonDefaults.buttonColors(containerColor = AppColors.primary)
                ) {
                    Text("Close", color = androidx.compose.ui.graphics.Color.White)
                }
            }
        }
    }
}

private data class OverlapSlot(
    val localStart: String,
    val localEnd: String,
    val zoneBreakdowns: List<String>
)

private fun calculateOverlap(
    dateTimeProvider: DateTimeProvider,
    selectedTimeZones: List<String>,
    startHour: Int,
    startMinute: Int,
    endHour: Int,
    endMinute: Int
): List<OverlapSlot> {
    val localZoneId = dateTimeProvider.getCurrentTimeZone()
    val localZone = TimeZone.of(localZoneId)
    val targetZones = selectedTimeZones.map { TimeZone.of(it) }
    
    val now = Clock.System.now()
    val today = now.toLocalDateTime(localZone).date
    
    val startMinutes = startHour * 60 + startMinute
    val endMinutes = endHour * 60 + endMinute
    
    val validMinutes = mutableListOf<Int>()
    
    for (minuteOfDay in 0 until 1440) {
        val h = minuteOfDay / 60
        val m = minuteOfDay % 60
        
        // 1. Is this minute within the local preferred range?
        val isLocalValid = if (startMinutes <= endMinutes) {
            minuteOfDay in startMinutes..endMinutes
        } else {
            minuteOfDay >= startMinutes || minuteOfDay <= endMinutes
        }
        
        if (!isLocalValid) continue
        
        // 2. Convert to Instant to check other timezones
        val localLdt = LocalDateTime(today.year, today.month, today.day, h, m, 0, 0)
        val instant = localLdt.toInstant(localZone)
        
        var allZonesValid = true
        for (zone in targetZones) {
            val zoneLdt = instant.toLocalDateTime(zone)
            val zoneMinutes = zoneLdt.hour * 60 + zoneLdt.minute
            
            val isZoneValid = if (startMinutes <= endMinutes) {
                zoneMinutes in startMinutes..endMinutes
            } else {
                zoneMinutes >= startMinutes || zoneMinutes <= endMinutes
            }
            
            if (!isZoneValid) {
                allZonesValid = false
                break
            }
        }
        
        if (allZonesValid) {
            validMinutes.add(minuteOfDay)
        }
    }
    
    // Group contiguous minutes
    val intervals = mutableListOf<Pair<Int, Int>>()
    if (validMinutes.isNotEmpty()) {
        var start = validMinutes.first()
        var prev = start
        for (i in 1 until validMinutes.size) {
            val curr = validMinutes[i]
            if (curr != prev + 1) {
                intervals.add(Pair(start, prev))
                start = curr
            }
            prev = curr
        }
        intervals.add(Pair(start, prev))
    }
    
    // Convert intervals to OverlapSlot representations
    return intervals.map { (startMin, endMin) ->
        val localStartStr = "${(startMin / 60).toString().padStart(2, '0')}:${(startMin % 60).toString().padStart(2, '0')}"
        val localEndStr = "${(endMin / 60).toString().padStart(2, '0')}:${(endMin % 60).toString().padStart(2, '0')}"
        
        // Build breakdown lists for each target timezone
        val startLdt = LocalDateTime(today.year, today.month, today.day, startMin / 60, startMin % 60, 0, 0)
        val endLdt = LocalDateTime(today.year, today.month, today.day, endMin / 60, endMin % 60, 0, 0)
        
        val startInstant = startLdt.toInstant(localZone)
        val endInstant = endLdt.toInstant(localZone)
        
        val breakdowns = targetZones.map { zone ->
            val targetStart = startInstant.toLocalDateTime(zone)
            val targetEnd = endInstant.toLocalDateTime(zone)
            
            val zoneStartStr = "${targetStart.hour.toString().padStart(2, '0')}:${targetStart.minute.toString().padStart(2, '0')}"
            val zoneEndStr = "${targetEnd.hour.toString().padStart(2, '0')}:${targetEnd.minute.toString().padStart(2, '0')}"
            
            val diffDays = targetStart.date.toEpochDays() - today.toEpochDays()
            val daySuffix = when {
                diffDays > 0 -> " (+1 day)"
                diffDays < 0 -> " (-1 day)"
                else -> ""
            }
            
            "${zone.id}: $zoneStartStr - $zoneEndStr$daySuffix"
        }
        
        OverlapSlot(
            localStart = localStartStr,
            localEnd = localEndStr,
            zoneBreakdowns = breakdowns
        )
    }
}
