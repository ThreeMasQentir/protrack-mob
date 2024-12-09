package org.gspi.protrack


import org.gspi.protrack.di.iosModule
import org.gspi.protrack.feature.feat_login.di.loginModule
import org.koin.core.context.startKoin

fun iosInitKoin() {
    startKoin {
        modules(
            iosModule,
            loginModule)
    }
}
