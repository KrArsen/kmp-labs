package com.example.systeminfoapp.data

import com.example.systeminfoapp.db.AppDatabase
import com.example.systeminfoapp.db.Reminder
import kotlin.time.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class ReminderRepository(private val database: AppDatabase) {
    fun getAllReminders(): List<Reminder> = database.reminderQueries.getAllReminders().executeAsList()

    fun addReminder(title: String, description: String) {
        val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
            .let {
                val hourStr = it.hour.toString().padStart(2, '0')
                val minStr = it.minute.toString().padStart(2, '0')
                val dayStr = it.dayOfMonth.toString().padStart(2, '0')
                val monthStr = it.monthNumber.toString().padStart(2, '0')
                "$dayStr.$monthStr.${it.year} $hourStr:$minStr"
            }
        database.reminderQueries.insertReminder(title, description, now)
    }

    fun deleteReminder(id: Long) = database.reminderQueries.deleteReminder(id)
}
