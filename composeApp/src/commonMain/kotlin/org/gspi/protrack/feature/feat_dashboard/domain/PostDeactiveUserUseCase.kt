package org.gspi.protrack.feature.feat_dashboard.domain

import org.gspi.protrack.common.model.Meta

class PostDeactiveUserUseCase(private val repository: DashboardRepository) {
    suspend fun execute(id: Int): Result<Meta> {
        return repository.deactivateUser(id)
    }
}