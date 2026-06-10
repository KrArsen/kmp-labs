package com.example.systeminfoapp.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.worker.WebWorkerDriver
import org.w3c.dom.Worker

actual class DatabaseDriverFactory actual constructor() {
    actual fun createDriver(): SqlDriver = WebWorkerDriver(
        Worker("@cashapp/sqldelight-sqljs-worker/sqljs.worker.js")
    )
}
