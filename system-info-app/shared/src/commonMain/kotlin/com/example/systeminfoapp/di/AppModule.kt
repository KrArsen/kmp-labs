package com.example.systeminfoapp.di

import com.example.systeminfoapp.data.SystemInfoRepository
import org.koin.dsl.module

val appModule = module {
    single { SystemInfoRepository() }
}
