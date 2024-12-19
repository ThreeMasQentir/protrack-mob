package org.gspi.protrack.feature.feat_detail_project.di

import org.gspi.protrack.feature.feat_detail_project.data.repository.DetailProjectRepositoryImpl
import org.gspi.protrack.feature.feat_detail_project.data.source.remote.DetailRemoteDataSource
import org.gspi.protrack.feature.feat_detail_project.domain.repository.DetailProjectRepository
import org.gspi.protrack.feature.feat_detail_project.domain.usecase.DeleteProjectUseCase
import org.gspi.protrack.feature.feat_detail_project.domain.usecase.GetDetailProjectUseCase
import org.gspi.protrack.feature.feat_detail_project.domain.usecase.PostSettingProjectUseCase
import org.gspi.protrack.feature.feat_detail_project.domain.usecase.PostUpdateProgressUseCase
import org.gspi.protrack.feature.feat_detail_project.presentation.viewmodel.DetailProjectViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val detailProjectModule = module {
    single { DetailRemoteDataSource(get()) }
    single<DetailProjectRepository> { DetailProjectRepositoryImpl(get()) }
    single { GetDetailProjectUseCase(get()) }
    single { DeleteProjectUseCase(get()) }
    single { PostUpdateProgressUseCase(get()) }
    single { PostSettingProjectUseCase(get()) }
    viewModel { DetailProjectViewModel(get(), get(), get(), get()) }
}