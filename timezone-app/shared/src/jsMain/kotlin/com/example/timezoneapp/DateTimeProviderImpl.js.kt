package com.example.timezoneapp

import io.github.aakira.napier.Napier
import kotlin.time.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

private fun getBrowserTimeZone(): String {
    return js("Intl.DateTimeFormat().resolvedOptions().timeZone") as String
}

private fun getBrowserSupportedTimeZones(): String {
    return js("Intl.supportedValuesOf('timeZone').join(',')") as String
}

actual class DateTimeProviderImpl actual constructor() : DateTimeProvider {

    override fun getCurrentDateTime(): String {
        Napier.d(tag = "DateTimeProviderImpl") { "getCurrentDateTime() called (JS)" }
        val currentInstant = Clock.System.now()
        val systemZone = TimeZone.of(getCurrentTimeZone())
        return currentInstant.toLocalDateTime(systemZone).toString()
    }

    override fun getCurrentTimeZone(): String {
        Napier.d(tag = "DateTimeProviderImpl") { "getCurrentTimeZone() called (JS)" }
        return getBrowserTimeZone()
    }

    override fun getAvailableTimeZones(): List<String> {
        Napier.d(tag = "DateTimeProviderImpl") { "getAvailableTimeZones() called (JS)" }
        return try {
            getBrowserSupportedTimeZones().split(",").sorted()
        } catch (e: Throwable) {
            TimeZone.availableZoneIds.toList().sorted()
        }
    }

    override fun getCurrentDateTimeInZone(timeZone: String): String {
        Napier.d(tag = "DateTimeProviderImpl") { "getCurrentDateTimeInZone(timeZone = $timeZone) called (JS)" }
        val currentInstant = Clock.System.now()
        val zone = TimeZone.of(timeZone)
        return currentInstant.toLocalDateTime(zone).toString()
    }
}
