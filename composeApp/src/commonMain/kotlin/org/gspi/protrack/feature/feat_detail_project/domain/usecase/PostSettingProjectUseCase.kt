package org.gspi.protrack.feature.feat_detail_project.domain.usecase

import org.gspi.protrack.common.model.Meta
import org.gspi.protrack.feature.feat_detail_project.data.model.request.UpdateProjectRequest
import org.gspi.protrack.feature.feat_detail_project.domain.repository.DetailProjectRepository

class PostSettingProjectUseCase(val repository: DetailProjectRepository) {
    suspend fun execute(
        id: Int,
        projectName: String,
        startDate: String,
        endDate: String,
        aoiFileName: String,
        aoiFile: ByteArray?,
        rencanaTitikControlFileName: String,
        rencanaTitikControlFile: ByteArray?,
    ): Result<Meta> {
        val request = UpdateProjectRequest(
            projectName = projectName,
            startDate = startDate,
            deadlineDate = endDate,
            aoiFileName = aoiFileName,
            titikKontrolFileName = rencanaTitikControlFileName,
            aoi = aoiFile,
            rencanaTitikKontrol = rencanaTitikControlFile
        )
        return repository.updateProject(id, request)
    }
}