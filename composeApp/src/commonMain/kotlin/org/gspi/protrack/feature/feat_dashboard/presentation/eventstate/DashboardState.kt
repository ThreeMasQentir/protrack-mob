package org.gspi.protrack.feature.feat_dashboard.presentation.eventstate

import org.gspi.protrack.feature.feat_dashboard.data.model.response.ProjectResponseItem
import org.gspi.protrack.feature.feat_dashboard.presentation.model.ContentType

data class DashboardState (
    val isLoading: Boolean = false,
    val errorMessage: String?= null,
    val searchValue: String = "",
    val listProject: List<ProjectResponseItem> = emptyList(),
    val isDialogVisible: Boolean = false,
    val contentType : ContentType = ContentType.PROJECTS,

    //new project dialog
    val projectName: String = "",
    val startDate: String = "",
    val endDate: String = "",
    val aoiFileName: String = "",
    val aoiByteArray: ByteArray? = null,
    val rencanaTitikControlFileName: String = "",
    val rencanaTitikControlByteArray: ByteArray? = null,

)