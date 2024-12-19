package org.gspi.protrack.feature.feat_detail_project.domain.usecase.log

import org.gspi.protrack.feature.feat_detail_project.domain.repository.DetailProjectRepository

class GetListLogUseCase(private val repository: DetailProjectRepository) {
    suspend fun execute(id: Int) = repository.getLogList(id)
}