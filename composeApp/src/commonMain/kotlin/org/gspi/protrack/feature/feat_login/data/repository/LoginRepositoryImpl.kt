package org.gspi.protrack.feature.feat_login.data.repository

import org.gspi.protrack.common.model.BaseResponse
import org.gspi.protrack.feature.feat_login.data.api.LoginApi
import org.gspi.protrack.feature.feat_login.data.model.request.LoginRequest
import org.gspi.protrack.feature.feat_login.data.model.response.DecoderTokenResponse
import org.gspi.protrack.feature.feat_login.data.model.response.LoginResponse
import org.gspi.protrack.feature.feat_login.domain.LoginRepository

class LoginRepositoryImpl(private val api: LoginApi): LoginRepository {
    override suspend fun login(request: LoginRequest): Result<BaseResponse<LoginResponse>> {
        return runCatching {
            api.login(request)
        }.recoverCatching {
            throw it
        }
    }

    override suspend fun decodeToken(): Result<BaseResponse<DecoderTokenResponse>> {
        return runCatching {
            api.decodeToken()
        }.recoverCatching {
            throw it
        }
    }
}