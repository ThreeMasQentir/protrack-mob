package org.gspi.protrack.feature.feat_detail_project.data.model.request

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class UpdateProjectRequest(
    @SerialName("project_name") val projectName: String,
    @SerialName("start_date") val startDate: String,
    @SerialName("deadline_date") val deadlineDate: String,
    @SerialName("aoiFileName") val aoiFileName: String,
    @SerialName("titikKontrolFileName") val titikKontrolFileName: String,
    @SerialName("aoi") val aoi: ByteArray? = null,
    @SerialName("rencana_titik_control") val rencanaTitikKontrol: ByteArray? = null,
)