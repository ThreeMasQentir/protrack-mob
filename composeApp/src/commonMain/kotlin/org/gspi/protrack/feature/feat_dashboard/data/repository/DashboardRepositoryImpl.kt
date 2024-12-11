package org.gspi.protrack.feature.feat_dashboard.data.repository

import kotlinx.coroutines.flow.first
import org.gspi.protrack.common.model.BaseResponse
import org.gspi.protrack.common.model.Meta
import org.gspi.protrack.common.network.ConnectivityChecker
import org.gspi.protrack.feature.feat_dashboard.data.model.response.ProjectResponseItem
import org.gspi.protrack.feature.feat_dashboard.data.source.local.DashboardLocalDataSource
import org.gspi.protrack.feature.feat_dashboard.data.source.remote.DashboardRemoteDataSource
import org.gspi.protrack.feature.feat_dashboard.domain.DashboardRepository

class DashboardRepositoryImpl(
    private val remoteDataSource: DashboardRemoteDataSource,
    private val localDataSource: DashboardLocalDataSource,
    private val connectivityChecker: ConnectivityChecker
) : DashboardRepository {

    override suspend fun getProjectList(): Result<BaseResponse<List<ProjectResponseItem>>> {
        val isNetworkAvailable = connectivityChecker.isConnected()
        println("isNetworkAvailable: $isNetworkAvailable")

        return if (isNetworkAvailable) {
            runCatching {
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
                val localProjects = localDataSource.getAllProjects().first()
                BaseResponse(
                    meta = Meta(code = 500, message = "Error fetching from remote, showing local data"),
                    data = localProjects
                )
            }
        } else {
            runCatching {
                val localProjects = localDataSource.getAllProjects().first()
                BaseResponse(
                    meta = Meta(code = 200, message = "No internet connection, showing local data"),
                    data = localProjects
                )
            }
        }
    }
}