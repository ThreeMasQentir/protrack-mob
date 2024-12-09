package org.gspi.protrack.feature.feat_login.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.gspi.protrack.feature.feat_login.data.api.LoginApi
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class LoginViewModel : ViewModel(), KoinComponent {
    private val loginApi: LoginApi by inject()

    fun login() {
        viewModelScope.launch {
            loginApi.login()
        }
    }

    fun getName(): String {
        return "LoginViewModel"
    }
}