package com.example.systeminfoapp.data

import com.example.systeminfoapp.platform.SystemInfo

class SystemInfoRepository {
    fun getSystemInfo(): SystemInfo = com.example.systeminfoapp.platform.getSystemInfo()
    fun getOsName(): String = com.example.systeminfoapp.platform.getSystemInfo().osName
    fun getOsVersion(): String = com.example.systeminfoapp.platform.getSystemInfo().osVersion
    fun getDeviceModel(): String = com.example.systeminfoapp.platform.getSystemInfo().deviceModel
    fun getManufacturer(): String = com.example.systeminfoapp.platform.getSystemInfo().manufacturer
}
