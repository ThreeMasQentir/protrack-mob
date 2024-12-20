package org.gspi.protrack.feature.feat_detail_project.data.source.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.forms.formData
import io.ktor.client.request.forms.submitFormWithBinaryData
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsChannel
import io.ktor.http.ContentType
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.utils.io.copyAndClose
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import org.gspi.protrack.common.model.BaseResponse
import org.gspi.protrack.common.model.Meta
import org.gspi.protrack.feature.feat_detail_project.data.model.request.AddDocumentRequest
import org.gspi.protrack.feature.feat_detail_project.data.model.request.UpdateProgressRequest
import org.gspi.protrack.feature.feat_detail_project.data.model.request.UpdateProjectRequest
import org.gspi.protrack.feature.feat_detail_project.data.model.response.DetailProjectResponse
import org.gspi.protrack.feature.feat_detail_project.data.model.response.ItemDocumentResponse
import org.gspi.protrack.feature.feat_detail_project.data.model.response.ItemLogResponse
import org.gspi.protrack.feature.feat_detail_project.presentation.dialog.getCurrentDate

class DetailRemoteDataSource(private val client: HttpClient) {
    private val baseUrl = "https://gspi-protrack.my.id/api-dev"

    suspend fun getDetailProject(id: Int): BaseResponse<DetailProjectResponse>{
        return runCatching{
            client.get("$baseUrl/project/lists/$id") {
                contentType(ContentType.Application.Json)
            }.body<BaseResponse<DetailProjectResponse>>()
        }.getOrElse { exception ->
            throw exception
        }
    }

    suspend fun deleteProject(id: Int): Meta{
        return runCatching{
            client.delete("$baseUrl/project/delete/$id") {
                contentType(ContentType.Application.Json)
            }.body<Meta>()
        }.getOrElse { exception ->
            throw exception
        }
    }

    suspend fun updateProgress(id: Int, request: UpdateProgressRequest): Meta{
        return runCatching{
            client.post("$baseUrl/project/progress/$id") {
                contentType(ContentType.Application.Json)
                setBody(request)
            }.body<Meta>()
        }.getOrElse { exception ->
            throw exception
        }
    }

    suspend fun updateProject(id: Int, request: UpdateProjectRequest): Meta{
        return runCatching{
            val response = client.submitFormWithBinaryData(
                url = "$baseUrl/project/update/$id",
                formData = formData {
                    append("project_name", request.projectName)
                    append("start_date", request.startDate)
                    append("deadline_date", request.deadlineDate)
                    append("aoiFileName", request.aoiFileName)
                    append("titikKontrolFileName", request.titikKontrolFileName)
                    request.aoi?.let {
                        append("aoi", it, Headers.build {
                            append(HttpHeaders.ContentDisposition, "filename=aoi${request.projectName} ${getCurrentDate()}.zip")
                        })
                    }
                    request.rencanaTitikKontrol?.let {
                        append("rencana_titik_control", it, Headers.build {
                            append(HttpHeaders.ContentDisposition, "filename=rtk${request.projectName} ${getCurrentDate()}.zip")
                        })
                    }
                }
            )
            response.body<Meta>()
        }.getOrElse { exception ->
            throw exception
        }
    }

    suspend fun getLogList(id: Int): BaseResponse<List<ItemLogResponse>>{
        return runCatching{
            client.get("$baseUrl/project/logs/$id") {
                contentType(ContentType.Application.Json)
            }.body<BaseResponse<List<ItemLogResponse>>>()
        }.getOrElse { exception ->
            throw exception
        }
    }

    suspend fun getDocumentList(id: Int): BaseResponse<List<ItemDocumentResponse>>{
        return runCatching{
            client.get("$baseUrl/project/lists-document/$id") {
                contentType(ContentType.Application.Json)
            }.body<BaseResponse<List<ItemDocumentResponse>>>()
        }.getOrElse { exception ->
            throw exception
        }
    }

    suspend fun deleteDocument(id: Int): Meta{
        return runCatching{
            client.delete("$baseUrl/project/delete-document/$id") {
                contentType(ContentType.Application.Json)
            }.body<Meta>()
        }.getOrElse { exception ->
            throw exception
        }
    }

    suspend fun addDocument(id: Int, request: AddDocumentRequest): Meta{
        return runCatching{
            val response = client.submitFormWithBinaryData(
                url = "$baseUrl/project/add-document/$id",
                formData = formData {
                    append("document_name", request.documentName)
                    append("document", request.document, Headers.build {
                        append(HttpHeaders.ContentDisposition, "filename=${request.documentName}.pdf")
                    })
                }
            )
            response.body<Meta>()
        }.getOrElse { exception ->
            throw exception
        }
    }

    suspend fun downloadPdf(url: String) {
        val client = HttpClient()
        withContext(Dispatchers.IO) {
        }
        client.close()
    }
}