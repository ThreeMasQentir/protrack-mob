package org.gspi.protrack.feature.feat_dashboard.data.source.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import org.gspi.protrack.common.model.BaseResponse
import org.gspi.protrack.common.model.Meta
import org.gspi.protrack.feature.feat_dashboard.data.model.request.AddUpdateUserRequest
import org.gspi.protrack.feature.feat_dashboard.data.model.response.ProjectResponseItem
import org.gspi.protrack.feature.feat_dashboard.data.model.response.UserResponseItem

class DashboardRemoteDataSource(private val client: HttpClient) {
    private val baseUrl = "https://gspi-protrack.my.id/api"

    suspend fun getProjectList(): BaseResponse<List<ProjectResponseItem>>{
        return runCatching {
            client.get("$baseUrl/project/lists/") {
                contentType(ContentType.Application.Json)
            }.body<BaseResponse<List<ProjectResponseItem>>>()
        }.getOrElse { exception ->
            throw exception
        }
    }

    suspend fun getUserList(): BaseResponse<List<UserResponseItem>>{
        return runCatching {
            client.get("$baseUrl/user/") {
                contentType(ContentType.Application.Json)
            }.body<BaseResponse<List<UserResponseItem>>>()
        }.getOrElse { exception ->
            throw exception
        }
    }

    suspend fun postCreateUser(request: AddUpdateUserRequest): Meta {
        return runCatching {
            client.post("$baseUrl/user/") {
                contentType(ContentType.Application.Json)
                setBody(request)
            }.body<Meta>()
        }.getOrElse { exception ->
            throw exception
        }
    }

    suspend fun postUpdateUser(id: Int, request: AddUpdateUserRequest): Meta {
        return runCatching {
            client.post("$baseUrl/user/$id") {
                contentType(ContentType.Application.Json)
                setBody(request)
            }.body<Meta>()
        }.getOrElse { exception ->
            throw exception
        }
    }

    //delete user
    suspend fun deleteUser(id: Int): Meta {
        return runCatching {
            client.delete("$baseUrl/user/$id") {
                contentType(ContentType.Application.Json)
            }.body<Meta>()
        }.getOrElse { exception ->
            throw exception
        }
    }

    suspend fun deactivateUser(id: Int): Meta {
        return runCatching {
            client.post("$baseUrl/user/deactivate/$id") {
            }.body<Meta>()
        }.getOrElse { exception ->
            throw exception
        }
    }

    suspend fun activateUser(id: Int): Meta {
        return runCatching {
            client.post("$baseUrl/user/activate/$id") {
            }.body<Meta>()
        }.getOrElse { exception ->
            throw exception
        }
    }


}