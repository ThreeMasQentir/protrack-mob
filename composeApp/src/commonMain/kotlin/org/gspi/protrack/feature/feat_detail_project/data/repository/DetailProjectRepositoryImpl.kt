package org.gspi.protrack.feature.feat_detail_project.data.repository

import org.gspi.protrack.common.model.BaseResponse
import org.gspi.protrack.common.model.Meta
import org.gspi.protrack.feature.feat_detail_project.data.model.request.UpdateProgressRequest
import org.gspi.protrack.feature.feat_detail_project.data.model.request.UpdateProjectRequest
import org.gspi.protrack.feature.feat_detail_project.data.model.response.DetailProjectResponse
import org.gspi.protrack.feature.feat_detail_project.data.source.remote.DetailRemoteDataSource
import org.gspi.protrack.feature.feat_detail_project.domain.repository.DetailProjectRepository

class DetailProjectRepositoryImpl(
    val remoteDataSource: DetailRemoteDataSource
): DetailProjectRepository {
    override suspend fun getDetailProject(id: Int): Result<BaseResponse<DetailProjectResponse>> {
        return runCatching {
            remoteDataSource.getDetailProject(id)
        }
    }

    override suspend fun deleteProject(id: Int): Result<Meta> {
        return runCatching {
            remoteDataSource.deleteProject(id)
        }
    }

    override suspend fun updateProgress(
        id: Int,
        request: UpdateProgressRequest
    ): Result<Meta> {
        return runCatching {
            remoteDataSource.updateProgress(id, request)
        }
    }

    override suspend fun updateProject(
        id: Int,
        request: UpdateProjectRequest
    ): Result<Meta> {
        return runCatching {
            remoteDataSource.updateProject(id, request)
        }
    }
}