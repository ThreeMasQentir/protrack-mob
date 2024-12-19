package org.gspi.protrack.feature.feat_detail_project.data.model.request

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class UpdateProgressRequest(
    @SerialName("gps_current") val gpsCurrent: Int,
    @SerialName("gps_total") val gpsTotal: Int,
    @SerialName("uav_current") val uavCurrent: Int,
    @SerialName("uav_total") val uavTotal: Int,
    @SerialName("process_current") val processCurrent: Int,
    @SerialName("process_total") val processTotal: Int
)