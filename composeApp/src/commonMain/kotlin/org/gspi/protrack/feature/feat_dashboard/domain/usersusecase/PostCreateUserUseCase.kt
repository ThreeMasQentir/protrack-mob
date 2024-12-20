package org.gspi.protrack.feature.feat_dashboard.domain.usersusecase

import org.gspi.protrack.common.model.Meta
import org.gspi.protrack.feature.feat_dashboard.data.model.request.AddUpdateUserRequest
import org.gspi.protrack.feature.feat_dashboard.domain.repository.DashboardRepository

class PostCreateUserUseCase(private val repository: DashboardRepository) {
    suspend fun execute(name: String, password: String, email: String, phoneNumber: String): Result<Meta> {
        val request = AddUpdateUserRequest(name, password, email, phoneNumber)
        return repository.postCreateUser(request)
    }
}