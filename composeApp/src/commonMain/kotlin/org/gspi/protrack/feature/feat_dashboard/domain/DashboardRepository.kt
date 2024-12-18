package org.gspi.protrack.feature.feat_dashboard.domain

import org.gspi.protrack.common.model.BaseResponse
import org.gspi.protrack.feature.feat_dashboard.data.model.response.ProjectResponseItem
import org.gspi.protrack.feature.feat_dashboard.data.model.response.UserResponseItem

interface DashboardRepository {
    suspend fun getProjectList(): Result<BaseResponse<List<ProjectResponseItem>>>
    suspend fun getUserList(): Result<BaseResponse<List<UserResponseItem>>>
}