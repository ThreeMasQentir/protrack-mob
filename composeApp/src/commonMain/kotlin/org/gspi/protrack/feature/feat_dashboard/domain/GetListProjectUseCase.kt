package org.gspi.protrack.feature.feat_dashboard.domain

import org.gspi.protrack.common.model.BaseResponse
import org.gspi.protrack.feature.feat_dashboard.data.model.response.ProjectResponseItem

class GetListProjectUseCase(private val repository: DashboardRepository) {
    suspend fun execute(): Result<BaseResponse<List<ProjectResponseItem>>> {
        return repository.getProjectList()
    }
}