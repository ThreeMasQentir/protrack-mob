package org.gspi.protrack.feature.feat_login.domain

import org.gspi.protrack.common.model.BaseResponse
import org.gspi.protrack.feature.feat_login.data.model.request.LoginRequest
import org.gspi.protrack.feature.feat_login.data.model.response.DecoderTokenResponse
import org.gspi.protrack.feature.feat_login.data.model.response.LoginResponse

interface LoginRepository {
    suspend fun login(request: LoginRequest): Result<BaseResponse<LoginResponse>>
    suspend fun decodeToken(): Result<BaseResponse<DecoderTokenResponse>>
}