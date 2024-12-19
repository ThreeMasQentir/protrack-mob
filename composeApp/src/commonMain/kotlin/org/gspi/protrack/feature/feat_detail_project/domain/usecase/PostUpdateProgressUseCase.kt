package org.gspi.protrack.feature.feat_detail_project.domain.usecase

import org.gspi.protrack.common.model.Meta
import org.gspi.protrack.feature.feat_detail_project.data.model.request.UpdateProgressRequest
import org.gspi.protrack.feature.feat_detail_project.domain.repository.DetailProjectRepository

class PostUpdateProgressUseCase(val repository: DetailProjectRepository) {
    suspend fun execute(id: Int,
                        currentTitikControl: Int,
                        totalTitikControl: Int,
                        currentFotoUdara: Int,
                        totalFotoUdara: Int,
                        currentPengolahanLahan: Int,
                        totalPengolahanLahan: Int
                        ): Result<Meta> {
        val request = UpdateProgressRequest(
            gpsCurrent = currentTitikControl,
            gpsTotal = totalTitikControl,
            uavCurrent = currentFotoUdara,
            uavTotal = totalFotoUdara,
            processCurrent = currentPengolahanLahan,
            processTotal = totalPengolahanLahan
        )
        return repository.updateProgress(id, request)
    }
}