package org.gspi.protrack.feature.feat_dashboard.data.model.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProjectResponseItem(
    @SerialName("deadline_date")
    val deadlineDate: String = "",
    @SerialName("gps_current")
    val gpsCurrent: Int = 0,
    @SerialName("gps_total")
    val gpsTotal: Int = 0,
    @SerialName("id")
    val id: Int = 0,
    @SerialName("project_name")
    val projectName: String = "",
    @SerialName("start_date")
    val startDate: String = "",
    @SerialName("uav_current")
    val uavCurrent: Int = 0,
    @SerialName("uav_total")
    val uavTotal: Int = 0
)