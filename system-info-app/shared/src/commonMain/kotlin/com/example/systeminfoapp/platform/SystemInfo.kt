package com.example.systeminfoapp.platform

data class SystemInfo(
    val osName: String,
    val osVersion: String,
    val deviceModel: String,
    val manufacturer: String
)

expect fun getSystemInfo(): SystemInfo
