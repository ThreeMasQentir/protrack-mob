package org.gspi.protrack.common.local

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.example.ProjectDatabase

class AndroidDatabaseDriverFactory(private val context: Context) : DatabaseDriverFactory {
    override fun create(): SqlDriver {
        return AndroidSqliteDriver(ProjectDatabase.Schema, context, "sample.db")
    }
}