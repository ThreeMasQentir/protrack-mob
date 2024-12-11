package org.gspi.protrack.common.local

import com.example.ProjectDatabase
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver


class IOSDatabaseDriverFactory : DatabaseDriverFactory {
    override fun create(): SqlDriver {
        return NativeSqliteDriver(ProjectDatabase.Schema, "sample.db")
    }
}