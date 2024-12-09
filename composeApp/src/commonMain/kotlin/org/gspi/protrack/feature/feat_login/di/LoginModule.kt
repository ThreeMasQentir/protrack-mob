package org.gspi.protrack.feature.feat_login.di

import org.gspi.protrack.feature.feat_login.data.api.LoginApi
import org.koin.core.module.Module
import org.koin.dsl.module

val loginModule: Module = module {
    single { LoginApi(get()) }
}