package org.gspi.protrack.feature.feat_dashboard.data.source.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType
import org.gspi.protrack.common.model.BaseResponse
import org.gspi.protrack.feature.feat_dashboard.data.model.response.ProjectResponseItem

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
}