package org.gspi.protrack.feature.feat_login.di

import org.gspi.protrack.feature.feat_login.data.source.LoginRemoteDataSource
import org.gspi.protrack.feature.feat_login.data.repository.LoginRepositoryImpl
import org.gspi.protrack.feature.feat_login.domain.DecoderTokenUseCase
import org.gspi.protrack.feature.feat_login.domain.LoginRepository
import org.gspi.protrack.feature.feat_login.domain.LoginUseCase
import org.gspi.protrack.feature.feat_login.presentation.viewmodel.LoginViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val loginModule: Module = module {
    single { LoginRemoteDataSource(get()) }
    single<LoginRepository> { LoginRepositoryImpl(get()) }
    single { LoginUseCase(get()) }
    single { DecoderTokenUseCase(get()) }
    viewModel { LoginViewModel(get(), get(), get()) }
}