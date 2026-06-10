package com.example.systeminfoapp.di

import com.example.systeminfoapp.data.ReminderRepository
import com.example.systeminfoapp.data.SystemInfoRepository
import com.example.systeminfoapp.db.AppDatabase
import com.example.systeminfoapp.db.DatabaseDriverFactory
import com.example.systeminfoapp.ui.AboutViewModel
import com.example.systeminfoapp.ui.reminders.RemindersViewModel
import com.russhwolf.settings.Settings
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { Settings() }
    single { DatabaseDriverFactory() }
    single { AppDatabase(get<DatabaseDriverFactory>().createDriver()) }
    single { SystemInfoRepository(get()) }
    single { ReminderRepository(get()) }
    viewModel { AboutViewModel(get()) }
    viewModel { RemindersViewModel(get()) }
}
