package com.example.systeminfoapp.di

import com.example.systeminfoapp.data.SystemInfoRepository
import com.example.systeminfoapp.ui.AboutViewModel
import com.russhwolf.settings.Settings
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { Settings() }
    single { SystemInfoRepository(get()) }
    viewModel { AboutViewModel(get()) }
}
