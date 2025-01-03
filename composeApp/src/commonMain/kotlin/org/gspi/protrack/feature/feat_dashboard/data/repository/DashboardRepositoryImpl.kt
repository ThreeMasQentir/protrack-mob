package org.gspi.protrack.feature.feat_dashboard.data.repository

import kotlinx.coroutines.flow.first
import org.gspi.protrack.common.model.BaseResponse
import org.gspi.protrack.common.model.Meta
import org.gspi.protrack.common.network.ConnectivityChecker
import org.gspi.protrack.feature.feat_dashboard.data.model.request.AddEditProjectRequest
import org.gspi.protrack.feature.feat_dashboard.data.model.request.AddUpdateUserRequest
import org.gspi.protrack.feature.feat_dashboard.data.model.response.ProjectResponseItem
import org.gspi.protrack.feature.feat_dashboard.data.model.response.UserResponseItem
import org.gspi.protrack.feature.feat_dashboard.data.source.local.DashboardLocalDataSource
import org.gspi.protrack.feature.feat_dashboard.data.source.remote.DashboardRemoteDataSource
import org.gspi.protrack.feature.feat_dashboard.domain.repository.DashboardRepository

class DashboardRepositoryImpl(
    private val remoteDataSource: DashboardRemoteDataSource,
    private val localDataSource: DashboardLocalDataSource,
    connectivityChecker: ConnectivityChecker
) : DashboardRepository {

    val isNetworkAvailable = connectivityChecker.isConnected()
    override suspend fun getProjectList(): Result<BaseResponse<List<ProjectResponseItem>>> {
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
                    meta = Meta(
                        code = 500,
                        message = "Error fetching from remote, showing local data"
                    ),
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

    override suspend fun getUserList(): Result<BaseResponse<List<UserResponseItem>>> {
        return runCatching {
            if (!isNetworkAvailable) {
                BaseResponse(
                    meta = Meta(code = 500, message = "No internet connection"),
                    data = null
                )
            } else {
                val response = remoteDataSource.getUserList()
                BaseResponse(
                    meta = Meta(code = response.meta.code, message = response.meta.message),
                    data = response.data
                )
            }

        }
    }

    override suspend fun postCreateUser(request: AddUpdateUserRequest): Result<Meta> {
        return runCatching {
            remoteDataSource.postCreateUser(request)
        }
    }

    override suspend fun postUpdateUser(
        id: Int,
        request: AddUpdateUserRequest
    ): Result<Meta> {
        return runCatching {
            remoteDataSource.postUpdateUser(id, request)
        }
    }

    override suspend fun deleteUser(id: Int): Result<Meta> {
        return runCatching {
            remoteDataSource.deleteUser(id)
        }
    }

    override suspend fun activateUser(id: Int): Result<Meta> {
        return runCatching {
            remoteDataSource.activateUser(id)
        }
    }

    override suspend fun deactivateUser(id: Int): Result<Meta> {
        return runCatching {
            remoteDataSource.deactivateUser(id)
        }
    }

    override suspend fun createProject(request: AddEditProjectRequest): Result<Meta> {
        return runCatching {
            remoteDataSource.postCreateProject(request)
        }
    }
}