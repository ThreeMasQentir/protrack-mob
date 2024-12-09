package org.gspi.protrack.common.di

import org.gspi.protrack.common.local.UserPreferences
import org.koin.dsl.module

val coreModule = module {
    single { UserPreferences(get()) }
}