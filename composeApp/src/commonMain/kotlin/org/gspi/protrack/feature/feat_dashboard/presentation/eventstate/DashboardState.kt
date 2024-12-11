package org.gspi.protrack.feature.feat_dashboard.presentation.eventstate

import org.gspi.protrack.feature.feat_dashboard.data.model.response.ProjectResponseItem

data class DashboardState (
    val isLoading: Boolean = false,
    val errorMessage: String?= null,
    val searchValue: String = "",
    val listProject: List<ProjectResponseItem> = emptyList()
)