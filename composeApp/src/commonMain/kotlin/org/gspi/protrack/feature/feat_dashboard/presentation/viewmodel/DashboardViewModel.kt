package org.gspi.protrack.feature.feat_dashboard.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.gspi.protrack.common.local.UserPreferences
import org.gspi.protrack.feature.feat_dashboard.data.model.response.ProjectResponseItem
import org.gspi.protrack.feature.feat_dashboard.data.source.local.DashboardLocalDataSource

class DashboardViewModel(
    private val userPreferences: UserPreferences,
    private val dashboardLocalDataSource: DashboardLocalDataSource
) : ViewModel() {


    init {
        viewModelScope.launch {
            // Fetch projects from local database
            dashboardLocalDataSource.insertOrUpdateProject(
                ProjectResponseItem(
                    id = 2,
                    projectName = "Project 2",
                    startDate = "2021-01-01",
                    deadlineDate = "2021-12-31",
                    gpsCurrent = 0,
                    gpsTotal = 10,
                    uavCurrent = 0,
                    uavTotal = 5
                )
            )
        }
    }

    fun logout() {
        viewModelScope.launch {
            userPreferences.clearAll()
        }
    }

    fun getAllProjects() {
        viewModelScope.launch {
            dashboardLocalDataSource.getAllProjects().collectLatest {
                println("Projects: $it")
            }
        }
    }
}