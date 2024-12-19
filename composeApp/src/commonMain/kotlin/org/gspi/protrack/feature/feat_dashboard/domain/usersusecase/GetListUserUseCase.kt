package org.gspi.protrack.feature.feat_dashboard.domain.usersusecase

import org.gspi.protrack.common.model.BaseResponse
import org.gspi.protrack.feature.feat_dashboard.data.model.response.UserResponseItem
import org.gspi.protrack.feature.feat_dashboard.domain.repository.DashboardRepository

class GetListUserUseCase(val dashboardRepository: DashboardRepository) {
    suspend fun execute(): Result<BaseResponse<List<UserResponseItem>>> {
        return dashboardRepository.getUserList()
    }
}