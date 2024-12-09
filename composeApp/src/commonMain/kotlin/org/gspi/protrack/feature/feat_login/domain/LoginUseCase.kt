package org.gspi.protrack.feature.feat_login.domain

import org.gspi.protrack.common.model.BaseResponse
import org.gspi.protrack.feature.feat_login.data.model.request.LoginRequest
import org.gspi.protrack.feature.feat_login.data.model.response.LoginResponse

class LoginUseCase(private val repository: LoginRepository) {
    suspend fun execute(request: LoginRequest): Result<BaseResponse<LoginResponse>> {
        return repository.login(request)
    }
}