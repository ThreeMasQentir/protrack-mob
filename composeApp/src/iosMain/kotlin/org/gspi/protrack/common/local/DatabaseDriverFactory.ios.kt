package org.gspi.protrack.common.local

import com.example.ProjectDatabase
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver


actual class DatabaseDriverFactory {
    actual fun create(): SqlDriver =
        NativeSqliteDriver(ProjectDatabase.Schema, "sample.db")
}