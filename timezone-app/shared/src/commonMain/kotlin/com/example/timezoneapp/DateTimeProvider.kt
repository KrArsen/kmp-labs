package com.example.timezoneapp

interface DateTimeProvider {
    fun getCurrentDateTime(): String
    fun getCurrentTimeZone(): String
    fun getAvailableTimeZones(): List<String>
    fun getCurrentDateTimeInZone(timeZone: String): String
}
