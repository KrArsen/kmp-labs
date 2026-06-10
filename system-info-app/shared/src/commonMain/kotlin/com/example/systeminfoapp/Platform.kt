package com.example.systeminfoapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform