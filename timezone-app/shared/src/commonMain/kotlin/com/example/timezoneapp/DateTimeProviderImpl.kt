package com.example.timezoneapp

import io.github.aakira.napier.Napier
import kotlin.time.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class DateTimeProviderImpl : DateTimeProvider {

    override fun getCurrentDateTime(): String {
        Napier.d(tag = "DateTimeProviderImpl") { "getCurrentDateTime() called" }
        val currentInstant = Clock.System.now()
        val systemZone = TimeZone.currentSystemDefault()
        return currentInstant.toLocalDateTime(systemZone).toString()
    }

    override fun getCurrentTimeZone(): String {
        Napier.d(tag = "DateTimeProviderImpl") { "getCurrentTimeZone() called" }
        return TimeZone.currentSystemDefault().id
    }

    override fun getAvailableTimeZones(): List<String> {
        Napier.d(tag = "DateTimeProviderImpl") { "getAvailableTimeZones() called" }
        return TimeZone.availableZoneIds.toList().sorted()
    }

    override fun getCurrentDateTimeInZone(timeZone: String): String {
        Napier.d(tag = "DateTimeProviderImpl") { "getCurrentDateTimeInZone(timeZone = $timeZone) called" }
        val currentInstant = Clock.System.now()
        val zone = TimeZone.of(timeZone)
        return currentInstant.toLocalDateTime(zone).toString()
    }
}
