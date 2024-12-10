package org.gspi.protrack.feature.feat_dashboard.di

import org.gspi.protrack.feature.feat_dashboard.data.source.local.DashboardLocalDataSource
import org.gspi.protrack.feature.feat_dashboard.presentation.viewmodel.DashboardViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val dashboardModule = module {
    single { DashboardLocalDataSource(get()) }
    viewModel { DashboardViewModel(get(), get()) }
}