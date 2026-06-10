package com.example.systeminfoapp.data

import com.example.systeminfoapp.platform.SystemInfo
import com.russhwolf.settings.Settings
import kotlin.time.Clock
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.TimeZone
import io.github.aakira.napier.Napier

class SystemInfoRepository(
    private val settings: Settings
) {
    fun getSystemInfo(): SystemInfo = com.example.systeminfoapp.platform.getSystemInfo()
    
    fun getOpenCount(): Int = settings.getInt("open_count", 0)
    
    fun getLastOpenDate(): String = settings.getString("last_open_date", "Never")
    
    fun recordScreenOpen() {
        val count = getOpenCount() + 1
        settings.putInt("open_count", count)
        val now = Clock.System.now()
            .toLocalDateTime(TimeZone.currentSystemDefault())
            .let { "${it.dayOfMonth}.${it.monthNumber}.${it.year} ${it.hour}:${it.minute.toString().padStart(2, '0')}" }
        settings.putString("last_open_date", now)
        Napier.i(tag = "Repository") { "Screen opened $count times, last: $now" }
    }
}
