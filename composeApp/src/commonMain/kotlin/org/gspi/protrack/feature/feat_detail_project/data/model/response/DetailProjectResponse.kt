package org.gspi.protrack.feature.feat_detail_project.data.model.response

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class DetailProjectResponse(
    @SerialName("id") val id: Int,
    @SerialName("project_name") val projectName: String,
    @SerialName("start_date") val startDate: String,
    @SerialName("deadline_date") val deadlineDate: String,
    @SerialName("gps_current") val gpsCurrent: Int,
    @SerialName("gps_total") val gpsTotal: Int,
    @SerialName("uav_current") val uavCurrent: Int,
    @SerialName("uav_total") val uavTotal: Int,
    @SerialName("process_current") val processCurrent: Int,
    @SerialName("process_total") val processTotal: Int,
    @SerialName("aoiFileName") val aoiFileName: String?,
    @SerialName("titikKontrolFileName") val titikKontrolFileName: String?
)