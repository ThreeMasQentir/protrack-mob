package org.gspi.protrack.feature.feat_detail_project.domain.usecase.document

import org.gspi.protrack.common.model.Meta
import org.gspi.protrack.feature.feat_detail_project.data.model.request.AddDocumentRequest
import org.gspi.protrack.feature.feat_detail_project.domain.repository.DetailProjectRepository

class UploadDocumentUseCase(private val repository: DetailProjectRepository) {
    suspend fun execute(id: Int, documentName: String, documentFile: ByteArray, documentRealName: String): Result<Meta> {
        val request = AddDocumentRequest(documentName, documentFile, documentRealName)
        return repository.addDocument(id, request)
    }
}