package com.example.systeminfoapp.platform

actual fun getSystemInfo(): SystemInfo = SystemInfo(
    osName = System.getProperty("os.name") ?: "Unknown",
    osVersion = System.getProperty("os.version") ?: "Unknown",
    deviceModel = System.getProperty("os.arch") ?: "Unknown",
    manufacturer = "Desktop"
)
