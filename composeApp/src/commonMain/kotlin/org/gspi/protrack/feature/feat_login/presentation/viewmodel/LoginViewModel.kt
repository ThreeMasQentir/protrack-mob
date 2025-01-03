package org.gspi.protrack.feature.feat_login.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.gspi.protrack.common.local.UserPreferences
import org.gspi.protrack.common.utils.handleApiResponse
import org.gspi.protrack.feature.feat_login.data.model.request.LoginRequest
import org.gspi.protrack.feature.feat_login.data.model.response.DecoderTokenResponse
import org.gspi.protrack.feature.feat_login.domain.DecoderTokenUseCase
import org.gspi.protrack.feature.feat_login.domain.LoginUseCase
import org.gspi.protrack.feature.feat_login.presentation.eventstate.LoginEvent
import org.gspi.protrack.feature.feat_login.presentation.eventstate.LoginState

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val decoderTokenUseCase: DecoderTokenUseCase,
    private val userPreferences: UserPreferences
    ) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginState())
    val uiState: StateFlow<LoginState> = _uiState

    private fun updateUiState(state: LoginState) {
        _uiState.value = state
    }

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.PhoneChanged -> {
                updateUiState(_uiState.value.copy(
                    loginRequest = _uiState.value.loginRequest.copy(phone = event.phone)
                ))
            }
            is LoginEvent.PasswordChanged -> {
                updateUiState(_uiState.value.copy(
                    loginRequest = _uiState.value.loginRequest.copy(password = event.password)
                ))
            }
            is LoginEvent.LoginClicked -> {
                login(_uiState.value.loginRequest)
            }
            is LoginEvent.ClearError -> {
                updateUiState(_uiState.value.copy(errorMessage = null))
            }
            is LoginEvent.OnDecoderToken -> {
                decodeToken()
            }
        }
    }

    private fun login(request: LoginRequest) {
        updateUiState(_uiState.value.copy(isLoading = true))
        viewModelScope.launch {
            handleApiResponse(
                apiCall = { loginUseCase.execute(request) },
                onSuccess = { response ->
                    updateUiState(_uiState.value.copy(isLoading = false, loginResponse = response))
                    saveToken(response.token)
                },
                onError = { error ->
                    updateUiState(_uiState.value.copy(isLoading = false, errorMessage = error))
                }
            )
        }
    }

    private fun decodeToken() {
        updateUiState(_uiState.value.copy(isLoading = true))
        viewModelScope.launch {
            handleApiResponse(
                apiCall = { decoderTokenUseCase.execute() },
                onSuccess = { response ->
                    updateUiState(_uiState.value.copy(isLoading = false, decoderTokenResponse = response))
                    saveUserInformation(response)
                },
                onError = { error ->
                    updateUiState(_uiState.value.copy(isLoading = false, errorMessage = error))
                }
            )
        }
    }

    private fun saveToken(token: String) {
        viewModelScope.launch {
            userPreferences.saveToken(token)
        }
    }

    private fun saveUserInformation(user: DecoderTokenResponse) {
        viewModelScope.launch {
            userPreferences.saveName(user.name)
            userPreferences.savePhone(user.phone)
            userPreferences.saveListRole(user.role)
        }
    }

    fun getToken(): String? {
        return userPreferences.getToken()
    }
}