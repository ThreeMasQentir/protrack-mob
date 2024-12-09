package org.gspi.protrack.feature.feat_login.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.gspi.protrack.core.UserPreferences
import org.gspi.protrack.feature.feat_login.data.api.LoginApi
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class LoginViewModel : ViewModel(), KoinComponent {
    private val loginApi: LoginApi by inject()
    private val userPreferences: UserPreferences by inject()

    fun login() {
        viewModelScope.launch {
            loginApi.login()
        }
        setToken("dsfdsfdsfdsfdsfds")
        getToken()
    }

    private fun setToken(token: String) {
        viewModelScope.launch {
            userPreferences.saveToken(token)
        }
    }

    private fun getToken() {
        viewModelScope.launch {
            userPreferences.token.collect {
                println("getToken: $it")
            }
        }
    }

    fun getName(): String {
        return "LoginViewModel"
    }
}