package org.gspi.protrack.feature.feat_detail_project.domain.usecase.document

import org.gspi.protrack.common.model.BaseResponse
import org.gspi.protrack.feature.feat_detail_project.data.model.response.ItemDocumentResponse
import org.gspi.protrack.feature.feat_detail_project.data.model.response.ItemLogResponse
import org.gspi.protrack.feature.feat_detail_project.domain.repository.DetailProjectRepository

class GetListDocumentUseCase(private val repository: DetailProjectRepository) {
    suspend fun execute(id: Int):Result<BaseResponse<List<ItemDocumentResponse>>> {
        return repository.getDocumentList(id)
    }
}