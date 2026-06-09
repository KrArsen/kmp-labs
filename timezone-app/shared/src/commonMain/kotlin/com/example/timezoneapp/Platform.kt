package com.example.timezoneapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform