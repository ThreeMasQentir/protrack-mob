package org.gspi.protrack.feature.feat_dashboard.domain.usersusecase

import org.gspi.protrack.common.model.Meta
import org.gspi.protrack.feature.feat_dashboard.domain.repository.DashboardRepository

class PostActiveUserUseCase(private val repository: DashboardRepository) {
    suspend fun execute(id: Int): Result<Meta> {
        return repository.activateUser(id)
    }
}