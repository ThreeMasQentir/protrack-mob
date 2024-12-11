package org.gspi.protrack.feature.feat_dashboard.domain

import org.gspi.protrack.common.model.BaseResponse
import org.gspi.protrack.feature.feat_dashboard.data.model.response.ProjectResponseItem

interface DashboardRepository {
    suspend fun getProjectList(): Result<BaseResponse<List<ProjectResponseItem>>>
}