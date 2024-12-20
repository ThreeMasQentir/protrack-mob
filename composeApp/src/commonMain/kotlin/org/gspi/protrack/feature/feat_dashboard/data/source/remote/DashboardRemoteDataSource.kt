package org.gspi.protrack.feature.feat_dashboard.data.source.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.forms.formData
import io.ktor.client.request.forms.submitFormWithBinaryData
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import org.gspi.protrack.common.model.BaseResponse
import org.gspi.protrack.common.model.Meta
import org.gspi.protrack.feature.feat_dashboard.data.model.request.AddEditProjectRequest
import org.gspi.protrack.feature.feat_dashboard.data.model.request.AddUpdateUserRequest
import org.gspi.protrack.feature.feat_dashboard.data.model.response.ProjectResponseItem
import org.gspi.protrack.feature.feat_dashboard.data.model.response.UserResponseItem

class DashboardRemoteDataSource(private val client: HttpClient) {
    private val baseUrl = "https://gspi-protrack.my.id/api-dev"

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

    suspend fun postCreateProject(request: AddEditProjectRequest): Meta {
        return runCatching {
            val response = client.submitFormWithBinaryData(
                url = "$baseUrl/project/create",
                formData = formData {
                    append("project_name", request.projectName)
                    append("start_date", request.startDate)
                    append("deadline_date", request.deadlineDate)
                    request.aoi?.let {
                        append("aoi", it, Headers.build {
                            append(HttpHeaders.ContentDisposition, "filename=aoi${request.projectName}.zip")
                        })
                    }
                    request.rencanaTitikControl?.let {
                        append("rencana_titik_control", it, Headers.build {
                            append(HttpHeaders.ContentDisposition, "filename=rtk${request.projectName}.zip")
                        })

                    }
                }
            )
            response.body<Meta>()
        }.getOrElse { exception ->
            throw exception
        }
    }


}