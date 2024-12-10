package org.gspi.protrack.common.di

import com.example.ProjectDatabase
import org.gspi.protrack.common.local.UserPreferences
import org.koin.dsl.module

val coreModule = module {
    single { UserPreferences(get()) }
    single { ProjectDatabase(get()) }
}