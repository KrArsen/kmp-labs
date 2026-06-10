package com.example.systeminfoapp.ui

import androidx.lifecycle.ViewModel
import com.example.systeminfoapp.data.SystemInfoRepository
import com.example.systeminfoapp.platform.SystemInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AboutViewModel(
    private val repository: SystemInfoRepository
) : ViewModel() {
    private val _systemInfo = MutableStateFlow<SystemInfo?>(null)
    val systemInfo: StateFlow<SystemInfo?> = _systemInfo.asStateFlow()
    
    private val _openCount = MutableStateFlow(0)
    val openCount: StateFlow<Int> = _openCount.asStateFlow()
    
    private val _lastOpenDate = MutableStateFlow("Never")
    val lastOpenDate: StateFlow<String> = _lastOpenDate.asStateFlow()

    init {
        repository.recordScreenOpen()
        _systemInfo.value = repository.getSystemInfo()
        _openCount.value = repository.getOpenCount()
        _lastOpenDate.value = repository.getLastOpenDate()
    }
}
