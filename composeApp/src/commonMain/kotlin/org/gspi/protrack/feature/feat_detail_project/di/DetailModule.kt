package org.gspi.protrack.feature.feat_detail_project.di

import org.gspi.protrack.feature.feat_detail_project.data.repository.DetailProjectRepositoryImpl
import org.gspi.protrack.feature.feat_detail_project.data.source.remote.DetailRemoteDataSource
import org.gspi.protrack.feature.feat_detail_project.domain.repository.DetailProjectRepository
import org.gspi.protrack.feature.feat_detail_project.domain.usecase.document.DeleteDocumentUseCase
import org.gspi.protrack.feature.feat_detail_project.domain.usecase.document.GetListDocumentUseCase
import org.gspi.protrack.feature.feat_detail_project.domain.usecase.document.UploadDocumentUseCase
import org.gspi.protrack.feature.feat_detail_project.domain.usecase.log.GetListLogUseCase
import org.gspi.protrack.feature.feat_detail_project.domain.usecase.project.DeleteProjectUseCase
import org.gspi.protrack.feature.feat_detail_project.domain.usecase.project.GetDetailProjectUseCase
import org.gspi.protrack.feature.feat_detail_project.domain.usecase.project.PostSettingProjectUseCase
import org.gspi.protrack.feature.feat_detail_project.domain.usecase.progress.PostUpdateProgressUseCase
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
    single { GetListLogUseCase(get()) }
    single { GetListDocumentUseCase(get()) }
    single { DeleteDocumentUseCase(get()) }
    single { UploadDocumentUseCase(get()) }
    viewModel { DetailProjectViewModel(get(), get(), get(), get(), get(), get(), get(), get()) }
}