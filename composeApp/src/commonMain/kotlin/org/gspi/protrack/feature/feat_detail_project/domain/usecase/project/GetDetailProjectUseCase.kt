package org.gspi.protrack.feature.feat_detail_project.domain.usecase.project

import org.gspi.protrack.common.model.BaseResponse
import org.gspi.protrack.feature.feat_detail_project.data.model.response.DetailProjectResponse
import org.gspi.protrack.feature.feat_detail_project.domain.repository.DetailProjectRepository

class GetDetailProjectUseCase(val repository: DetailProjectRepository) {
    suspend fun execute(id: Int): Result<BaseResponse<DetailProjectResponse>> {
        return repository.getDetailProject(id)
    }
}