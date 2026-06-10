package com.example.systeminfoapp.platform

actual fun getSystemInfo(): SystemInfo = SystemInfo(
    osName = "Android",
    osVersion = android.os.Build.VERSION.RELEASE,
    deviceModel = android.os.Build.MODEL,
    manufacturer = android.os.Build.MANUFACTURER
)
