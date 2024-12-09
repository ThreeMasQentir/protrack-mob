package org.gspi.protrack.feature.feat_login.domain

import org.gspi.protrack.common.model.BaseResponse
import org.gspi.protrack.feature.feat_login.data.model.response.DecoderTokenResponse

class DecoderTokenUseCase(private val repository: LoginRepository) {
    suspend fun execute(): Result<BaseResponse<DecoderTokenResponse>> {
        return repository.decodeToken()
    }
}