package org.gspi.protrack.feature.feat_dashboard.presentation.eventstate

import org.gspi.protrack.common.model.Meta
import org.gspi.protrack.feature.feat_dashboard.data.model.response.ProjectResponseItem
import org.gspi.protrack.feature.feat_dashboard.data.model.response.UserResponseItem
import org.gspi.protrack.feature.feat_dashboard.presentation.model.ContentType
import org.gspi.protrack.feature.feat_login.data.model.response.DecoderTokenResponse

data class DashboardState (
    val isLoading: Boolean = false,
    val token: String = "",
    val errorMessage: String?= null,
    val errorMessageDecoder: String?= null,
    val decoderTokenResponse: DecoderTokenResponse? = null,
    val searchValue: String = "",
    val listProject: List<ProjectResponseItem> = emptyList(),
    val listProjectFiltered: List<ProjectResponseItem> = emptyList(),
    val listUsers: List<UserResponseItem> = emptyList(),
    val listUsersFiltered: List<UserResponseItem> = emptyList(),
    val isDialogVisible: Boolean = false,
    val contentType : ContentType = ContentType.PROJECTS,
    val metaResponse: Meta? = null,


    //project dialog
    val projectName: String = "",
    val projectstartDate: String = "",
    val projectEndDate: String = "",
    val projectAoiFileName: String = "",
    val projectAoiByteArray: ByteArray? = null,
    val projectRencanaTitikControlFileName: String = "",
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

)