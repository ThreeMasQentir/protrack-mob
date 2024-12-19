package org.gspi.protrack.feature.feat_dashboard.di

import org.gspi.protrack.feature.feat_dashboard.data.repository.DashboardRepositoryImpl
import org.gspi.protrack.feature.feat_dashboard.data.source.local.DashboardLocalDataSource
import org.gspi.protrack.feature.feat_dashboard.data.source.remote.DashboardRemoteDataSource
import org.gspi.protrack.feature.feat_dashboard.domain.projectsusecase.CreateProjectUseCase
import org.gspi.protrack.feature.feat_dashboard.domain.repository.DashboardRepository
import org.gspi.protrack.feature.feat_dashboard.domain.usersusecase.DeleteUserUseCase
import org.gspi.protrack.feature.feat_dashboard.domain.projectsusecase.GetListProjectUseCase
import org.gspi.protrack.feature.feat_dashboard.domain.usersusecase.GetListUserUseCase
import org.gspi.protrack.feature.feat_dashboard.domain.usersusecase.PostActiveUserUseCase
import org.gspi.protrack.feature.feat_dashboard.domain.usersusecase.PostCreateUserUseCase
import org.gspi.protrack.feature.feat_dashboard.domain.usersusecase.PostDeactiveUserUseCase
import org.gspi.protrack.feature.feat_dashboard.domain.usersusecase.PostUpdateUserUseCase
import org.gspi.protrack.feature.feat_dashboard.presentation.viewmodel.DashboardViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import kotlin.math.sin

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
    single { CreateProjectUseCase(get()) }
    viewModel { DashboardViewModel(get(), get(), get(), get(), get(), get(), get(), get(), get()) }
}