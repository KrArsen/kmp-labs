package com.example.systeminfoapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.systeminfoapp.platform.getSystemInfo
import com.example.systeminfoapp.platform.SystemInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AboutViewModel : ViewModel() {
    private val _systemInfo = MutableStateFlow<SystemInfo?>(null)
    val systemInfo: StateFlow<SystemInfo?> = _systemInfo

    init {
        viewModelScope.launch {
            _systemInfo.value = getSystemInfo()
        }
    }
}