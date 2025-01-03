package org.gspi.protrack

import android.app.Application
import org.gspi.protrack.common.di.coreModule
import org.gspi.protrack.di.androidModule
import org.gspi.protrack.feature.feat_dashboard.di.dashboardModule
import org.gspi.protrack.feature.feat_detail_project.di.detailProjectModule
import org.gspi.protrack.feature.feat_login.di.loginModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MyApplication)
            modules(
                coreModule,
                androidModule,
                loginModule,
                dashboardModule,
                detailProjectModule
            )
        }
    }
}