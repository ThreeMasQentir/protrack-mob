package org.gspi.protrack.feature.feat_dashboard.presentation.eventstate

import org.gspi.protrack.common.model.Meta
import org.gspi.protrack.feature.feat_dashboard.data.model.response.ProjectResponseItem
import org.gspi.protrack.feature.feat_dashboard.data.model.response.UserResponseItem
import org.gspi.protrack.feature.feat_dashboard.presentation.model.ContentType

data class DashboardState (
    val isLoading: Boolean = false,
    val errorMessage: String?= null,
    val searchValue: String = "",
    val listProject: List<ProjectResponseItem> = emptyList(),
    val listUsers: List<UserResponseItem> = emptyList(),
    val isDialogVisible: Boolean = false,
    val contentType : ContentType = ContentType.PROJECTS,

    //project dialog
    val projectName: String = "",
    val projectstartDate: String = "",
    val projectEndDate: String = "",
    val projectAoiFileName: String = "Select File",
    val projectAoiByteArray: ByteArray? = null,
    val projectRencanaTitikControlFileName: String = "Select File",
    val projectRencanaTitikControlByteArray: ByteArray? = null,

    //user dialog
    val userId: Int = 0,
    val userName: String = "",
    val userUsername: String = "",
    val userPassword: String = "",
    val userEmail: String = "",
    val userPhoneNumber: String = "",
    val userIsActive: Boolean = true,
    val userIsEdit : Boolean = false,
    val metaCreateUser: Meta? = null

)