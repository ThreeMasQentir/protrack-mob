package org.gspi.protrack.feature.feat_dashboard.data.repository

import kotlinx.coroutines.flow.first
import org.gspi.protrack.common.model.BaseResponse
import org.gspi.protrack.common.model.Meta
import org.gspi.protrack.feature.feat_dashboard.data.model.response.ProjectResponseItem
import org.gspi.protrack.feature.feat_dashboard.data.source.local.DashboardLocalDataSource
import org.gspi.protrack.feature.feat_dashboard.data.source.remote.DashboardRemoteDataSource
import org.gspi.protrack.feature.feat_dashboard.domain.DashboardRepository

class DashboardRepositoryImpl(
    private val remoteDataSource: DashboardRemoteDataSource,
    private val localDataSource: DashboardLocalDataSource,
) : DashboardRepository {

    override suspend fun getProjectList(): Result<BaseResponse<List<ProjectResponseItem>>> {
        return runCatching {
            val response = remoteDataSource.getProjectList()
            val status = response.meta.code
            if (status == 200) {
                localDataSource.deleteAllProjects()
                response.data?.forEach { item ->
                    localDataSource.insertOrUpdateProject(item)
                }
            }

            val localProjects = localDataSource.getAllProjects().first()
            BaseResponse(
                meta = Meta(code = status, message = response.meta.message),
                data = localProjects
            )
        }.recoverCatching {
            throw it
        }
    }
}