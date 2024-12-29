package org.gspi.protrack.feature.feat_dashboard.presentation.eventstate

import org.gspi.protrack.feature.feat_dashboard.presentation.model.ContentType
import org.gspi.protrack.feature.feat_detail_project.presentation.eventstate.DetailProjectEvent

sealed class DashboardEvent {
    data class OnSearchValueChange(val searchValue: String) : DashboardEvent()
    data class OnSearchUserValueChange(val searchValue: String) : DashboardEvent()
    data object OnLogout : DashboardEvent()
    data object ClearError : DashboardEvent()
    data object LoadListProject : DashboardEvent()
    data object LoadListUser : DashboardEvent()

    //project dialog
    data class OnAddProjectClick(val isDialogVisible: Boolean) : DashboardEvent()
    data class OnContentTypeChange(val contentType: ContentType) : DashboardEvent()
    data class OnProjectNameChange(val projectName: String) : DashboardEvent()
    data class OnStartDateChange(val startDate: String) : DashboardEvent()
    data class OnEndDateChange(val endDate: String) : DashboardEvent()
    data class OnAoiChange(val aoiFileName: String, val aoiByteArray: ByteArray?) : DashboardEvent()
    data class OnRencanaTitikControlChange(val rencanaTitikControlFileName: String, val rencanaTitikControlByteArray: ByteArray?) : DashboardEvent()
    data object OnSaveProjectClick : DashboardEvent()
    data object OnDeleteAoiFileName : DashboardEvent()
    data object OnDeleteRencanaTitikControlFileName : DashboardEvent()
    data object OnDecoderToken : DashboardEvent()

    //user dialog
    data class OnAddUserClick(val isDialogVisible: Boolean) : DashboardEvent()
    data class OnUserNameChange(val userName: String) : DashboardEvent()
    data class OnUserUsernameChange(val userUsername: String) : DashboardEvent()
    data class OnUserPasswordChange(val userPassword: String) : DashboardEvent()
    data class OnUserEmailChange(val userEmail: String) : DashboardEvent()
    data class OnUserPhoneNumberChange(val userPhoneNumber: String) : DashboardEvent()
    data object OnSaveUserClick : DashboardEvent()
    data object OnEditUserClick : DashboardEvent()
    data class OnDeleteUserClick(val id: Int) : DashboardEvent()
    data class OnUserStateChange(val isActive: Boolean, val id: Int) : DashboardEvent()
    data class ShowEditUserDialog(val id: Int, val userName: String, val userUsername: String, val userPassword: String, val userEmail: String, val userPhoneNumber: String) : DashboardEvent()
}