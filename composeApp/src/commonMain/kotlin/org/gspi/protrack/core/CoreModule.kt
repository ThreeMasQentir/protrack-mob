package org.gspi.protrack.core

import org.koin.dsl.module

val coreModule = module {
    single { UserPreferences(get()) }
}