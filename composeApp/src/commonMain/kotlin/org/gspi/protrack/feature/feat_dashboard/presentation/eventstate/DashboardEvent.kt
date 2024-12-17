package org.gspi.protrack.feature.feat_dashboard.presentation.eventstate

import org.gspi.protrack.feature.feat_dashboard.presentation.model.ContentType

sealed class DashboardEvent {
    data class OnSearchValueChange(val searchValue: String) : DashboardEvent()
    data object OnLogout : DashboardEvent()
    data object ClearError : DashboardEvent()
    data object LoadListProject : DashboardEvent()
    data class OnAddProjectClick(val isDialogVisible: Boolean) : DashboardEvent()
    data class OnContentTypeChange(val contentType: ContentType) : DashboardEvent()

}