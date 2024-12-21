package org.gspi.protrack.feature.feat_dashboard.data.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddEditProjectRequest (
    @SerialName("project_name")
    val projectName: String,
    @SerialName("start_date")
    val startDate: String,
    @SerialName("deadline_date")
    val deadlineDate: String,
    @SerialName("aoi")
    val aoi: ByteArray?,
    @SerialName("rencana_titik_control")
    val rencanaTitikControl: ByteArray?,
    val aoiFileName: String? = null,
    val rencanaTitikControlFileName: String? = null
)