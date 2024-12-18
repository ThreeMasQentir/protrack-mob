package org.gspi.protrack.feature.feat_dashboard.domain

import org.gspi.protrack.common.model.Meta
import org.gspi.protrack.feature.feat_dashboard.data.model.request.AddUpdateUserRequest

class PostUpdateUserUseCase(private val repository: DashboardRepository) {
    suspend fun execute(id: Int, name: String, password: String, email: String, phoneNumber: String): Result<Meta> {
        val request = AddUpdateUserRequest(name, password, email, phoneNumber)
        return repository.postUpdateUser(id, request)
    }
}