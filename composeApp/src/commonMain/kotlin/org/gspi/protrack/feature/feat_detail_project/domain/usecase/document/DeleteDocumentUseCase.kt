package org.gspi.protrack.feature.feat_detail_project.domain.usecase.document

import org.gspi.protrack.common.model.Meta
import org.gspi.protrack.feature.feat_detail_project.domain.repository.DetailProjectRepository

class DeleteDocumentUseCase(private val repository: DetailProjectRepository) {
    suspend fun execute(id: Int): Result<Meta> {
        return repository.deleteDocument(id)
    }
}