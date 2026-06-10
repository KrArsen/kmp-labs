package com.example.systeminfoapp.db

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import org.koin.core.context.GlobalContext

actual class DatabaseDriverFactory(private val context: Context) {
    actual constructor() : this(GlobalContext.get().get<Context>())

    actual fun createDriver(): SqlDriver =
        AndroidSqliteDriver(AppDatabase.Schema, context, "app.db")
}
