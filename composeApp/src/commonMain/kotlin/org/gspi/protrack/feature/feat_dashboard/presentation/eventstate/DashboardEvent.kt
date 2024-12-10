package org.gspi.protrack.feature.feat_dashboard.presentation.eventstate

sealed class DashboardEvent {
    data class OnSearchValueChange(val searchValue: String) : DashboardEvent()
    data object OnLogout : DashboardEvent()
    data object ClearError : DashboardEvent()
    data object LoadListProject : DashboardEvent()
}