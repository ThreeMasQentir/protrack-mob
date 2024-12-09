package org.gspi.protrack.feature.feat_login.data.api

import org.gspi.protrack.common.model.BaseResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import org.gspi.protrack.feature.feat_login.data.model.request.LoginRequest
import org.gspi.protrack.feature.feat_login.data.model.response.LoginResponse

class LoginApi(private val client: HttpClient) {

    private val baseUrl = "https://gspi-protrack.my.id/api"

    suspend fun login(req: LoginRequest): BaseResponse<LoginResponse> {
        return runCatching {
            client.post("$baseUrl/auth/login/") {
                contentType(ContentType.Application.Json)
                setBody(req)
            }.body<BaseResponse<LoginResponse>>()
        }.getOrElse { exception ->
            throw exception
        }
    }
}