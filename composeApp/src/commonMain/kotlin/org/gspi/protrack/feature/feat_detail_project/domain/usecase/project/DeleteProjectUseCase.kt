package org.gspi.protrack.feature.feat_detail_project.domain.usecase.project

import org.gspi.protrack.common.model.Meta
import org.gspi.protrack.feature.feat_detail_project.domain.repository.DetailProjectRepository

class DeleteProjectUseCase(val repository: DetailProjectRepository) {
    suspend fun execute(id: Int): Result<Meta> {
        return repository.deleteProject(id)
    }
}