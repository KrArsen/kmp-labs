package com.example.systeminfoapp.platform

actual fun getSystemInfo(): SystemInfo = SystemInfo(
    osName = "Web",
    osVersion = "Browser",
    deviceModel = "Web Browser",
    manufacturer = "Web"
)
