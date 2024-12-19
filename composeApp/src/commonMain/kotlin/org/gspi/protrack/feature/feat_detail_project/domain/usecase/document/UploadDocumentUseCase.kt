package org.gspi.protrack.feature.feat_detail_project.domain.usecase.document

import io.ktor.client.request.request
import org.gspi.protrack.common.model.Meta
import org.gspi.protrack.feature.feat_detail_project.data.model.request.AddDocumentRequest
import org.gspi.protrack.feature.feat_detail_project.domain.repository.DetailProjectRepository

class UploadDocumentUseCase(private val repository: DetailProjectRepository) {
    suspend fun execute(id: Int, documentName: String, documentFile: ByteArray): Result<Meta> {
        val request = AddDocumentRequest(documentName, documentFile)
        return repository.addDocument(id, request)
    }
}