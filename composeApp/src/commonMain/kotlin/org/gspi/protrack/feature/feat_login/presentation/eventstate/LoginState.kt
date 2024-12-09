package org.gspi.protrack.feature.feat_login.presentation.eventstate

import org.gspi.protrack.feature.feat_login.data.model.request.LoginRequest
import org.gspi.protrack.feature.feat_login.data.model.response.LoginResponse

data class LoginState(
    val isLoading: Boolean = false,
    val errorMessage: String?= null,
    val loginResponse: LoginResponse? = null,
    val loginRequest: LoginRequest = LoginRequest()
)