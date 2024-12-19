package org.gspi.protrack.feature.feat_detail_project.domain.repository

import org.gspi.protrack.common.model.BaseResponse
import org.gspi.protrack.common.model.Meta
import org.gspi.protrack.feature.feat_detail_project.data.model.request.UpdateProgressRequest
import org.gspi.protrack.feature.feat_detail_project.data.model.request.UpdateProjectRequest
import org.gspi.protrack.feature.feat_detail_project.data.model.response.DetailProjectResponse

interface DetailProjectRepository {
    suspend fun getDetailProject(id: Int): Result<BaseResponse<DetailProjectResponse>>
    suspend fun deleteProject(id: Int): Result<Meta>
    suspend fun updateProgress(id: Int,request: UpdateProgressRequest): Result<Meta>
    suspend fun updateProject(id: Int, request: UpdateProjectRequest): Result<Meta>
}