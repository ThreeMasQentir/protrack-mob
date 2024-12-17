package org.gspi.protrack.feature.feat_dashboard.presentation.eventstate

import org.gspi.protrack.feature.feat_dashboard.presentation.model.ContentType

sealed class DashboardEvent {
    data class OnSearchValueChange(val searchValue: String) : DashboardEvent()
    data object OnLogout : DashboardEvent()
    data object ClearError : DashboardEvent()
    data object LoadListProject : DashboardEvent()
    data class OnAddProjectClick(val isDialogVisible: Boolean) : DashboardEvent()
    data class OnContentTypeChange(val contentType: ContentType) : DashboardEvent()
    data class OnProjectNameChange(val projectName: String) : DashboardEvent()
    data class OnStartDateChange(val startDate: String) : DashboardEvent()
    data class OnEndDateChange(val endDate: String) : DashboardEvent()
    data class OnAoiChange(val aoiFileName: String, val aoiByteArray: ByteArray?) : DashboardEvent()
    data class OnRencanaTitikControlChange(val rencanaTitikControlFileName: String, val rencanaTitikControlByteArray: ByteArray?) : DashboardEvent()
    data class OnSaveProjectClick(val projectName: String, val startDate: String, val endDate: String, val aoiByteArray: ByteArray, val rencanaTitikControlByteArray: ByteArray) : DashboardEvent()
}