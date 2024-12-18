package org.gspi.protrack.feature.feat_dashboard.di

import org.gspi.protrack.feature.feat_dashboard.data.repository.DashboardRepositoryImpl
import org.gspi.protrack.feature.feat_dashboard.data.source.local.DashboardLocalDataSource
import org.gspi.protrack.feature.feat_dashboard.data.source.remote.DashboardRemoteDataSource
import org.gspi.protrack.feature.feat_dashboard.domain.DashboardRepository
import org.gspi.protrack.feature.feat_dashboard.domain.DeleteUserUseCase
import org.gspi.protrack.feature.feat_dashboard.domain.GetListProjectUseCase
import org.gspi.protrack.feature.feat_dashboard.domain.GetListUserUseCase
import org.gspi.protrack.feature.feat_dashboard.domain.PostActiveUserUseCase
import org.gspi.protrack.feature.feat_dashboard.domain.PostCreateUserUseCase
import org.gspi.protrack.feature.feat_dashboard.domain.PostDeactiveUserUseCase
import org.gspi.protrack.feature.feat_dashboard.domain.PostUpdateUserUseCase
import org.gspi.protrack.feature.feat_dashboard.presentation.viewmodel.DashboardViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val dashboardModule = module {
    single { DashboardLocalDataSource(get()) }
    single { DashboardRemoteDataSource(get()) }
    single<DashboardRepository> { DashboardRepositoryImpl(get(), get(), get()) }
    single { GetListProjectUseCase(get()) }
    single { GetListUserUseCase(get()) }
    single { PostCreateUserUseCase(get()) }
    single { PostUpdateUserUseCase(get()) }
    single { DeleteUserUseCase(get()) }
    single { PostDeactiveUserUseCase(get()) }
    single { PostActiveUserUseCase(get()) }
    viewModel { DashboardViewModel(get(), get(), get(), get(), get(), get(), get(), get()) }
}