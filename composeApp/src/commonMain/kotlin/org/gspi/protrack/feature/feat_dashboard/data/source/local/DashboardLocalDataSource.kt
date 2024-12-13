package org.gspi.protrack.feature.feat_dashboard.data.source.local

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.example.ProjectDatabase
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.gspi.protrack.feature.feat_dashboard.data.model.response.ProjectResponseItem

class DashboardLocalDataSource(db: ProjectDatabase) {
    private val queries = db.projectDatabaseQueries

    fun insertOrUpdateProject(project: ProjectResponseItem) {
        queries.insertProject(
            id = project.id.toLong(),
            project_name = project.projectName,
            start_date = project.startDate,
            deadline_date = project.deadlineDate,
            gps_current = project.gpsCurrent.toLong(),
            gps_total = project.gpsTotal.toLong(),
            uav_current = project.uavCurrent.toLong(),
            uav_total = project.uavTotal.toLong()
        )
    }

    fun getAllProjects(): Flow<List<ProjectResponseItem>> {
        return queries.getAllProjects()
            .asFlow()
            .mapToList(
                context = kotlinx.coroutines.Dispatchers.IO
            )
            .map { projects ->
                projects.map { project ->
                    ProjectResponseItem(
                        id = project.id.toInt(),
                        projectName = project.project_name,
                        startDate = project.start_date,
                        deadlineDate = project.deadline_date,
                        gpsCurrent = project.gps_current.toInt(),
                        gpsTotal = project.gps_total.toInt(),
                        uavCurrent = project.uav_current.toInt(),
                        uavTotal = project.uav_total.toInt()
                    )
                }
            }
    }

    fun deleteAllProjects() {
        queries.deleteAllProjects()
    }
}
