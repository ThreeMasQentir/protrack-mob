package org.gspi.protrack.feature.feat_dashboard.domain.projectsusecase

import io.ktor.client.request.request
import org.gspi.protrack.common.model.Meta
import org.gspi.protrack.feature.feat_dashboard.data.model.request.AddEditProjectRequest
import org.gspi.protrack.feature.feat_dashboard.domain.repository.DashboardRepository

class CreateProjectUseCase(private val repository: DashboardRepository) {
    suspend fun execute(
        name: String,
        startDate: String,
        endDate: String,
        aoi: ByteArray?,
        rencanaTitikControl: ByteArray?,
        aoifFileName: String?,
        rencanaTitikControlFileName: String?
    ): Result<Meta> {
        val request = AddEditProjectRequest(name,
            startDate,
            endDate,
            aoi,
            rencanaTitikControl,
            aoiFileName = aoifFileName,
            rencanaTitikControlFileName = rencanaTitikControlFileName
        )
        return repository.createProject(request)
    }
}