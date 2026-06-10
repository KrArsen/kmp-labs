package com.example.systeminfoapp.ui.reminders

import androidx.lifecycle.ViewModel
import com.example.systeminfoapp.data.ReminderRepository
import com.example.systeminfoapp.db.Reminder
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class RemindersViewModel(private val repository: ReminderRepository) : ViewModel() {
    private val _reminders = MutableStateFlow<List<Reminder>>(emptyList())
    val reminders: StateFlow<List<Reminder>> = _reminders.asStateFlow()

    init {
        loadReminders()
    }

    fun loadReminders() {
        _reminders.value = repository.getAllReminders()
    }

    fun addReminder(title: String, description: String) {
        repository.addReminder(title, description)
        loadReminders()
    }

    fun deleteReminder(id: Long) {
        repository.deleteReminder(id)
        loadReminders()
    }
}
