package org.gspi.protrack.feature.feat_dashboard.domain

import org.gspi.protrack.common.model.Meta

class PostActiveUserUseCase(private val repository: DashboardRepository) {
    suspend fun execute(id: Int): Result<Meta> {
        return repository.activateUser(id)
    }
}