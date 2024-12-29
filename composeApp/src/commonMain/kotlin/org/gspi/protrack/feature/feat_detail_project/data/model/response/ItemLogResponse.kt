package org.gspi.protrack.feature.feat_detail_project.data.model.response

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class ItemLogResponse(
    @SerialName("id") val id: Int,
    @SerialName("tgl") val date: String,
    @SerialName("jam") val time: String,
    @SerialName("user") val user: String,
    @SerialName("aktivitas") val activity: String,
    @SerialName("link") val link: LinkLog?
) {
    @Serializable
    data class LinkLog(
        @SerialName("project_name") val projectName: List<String>? = null,
        @SerialName("start_date") val startDate: List<String>? = null,
        @SerialName("deadline_date") val deadlineDate: List<String>? = null,
        @SerialName("gps_current") val gpsCurrent: List<Int>? = null,
        @SerialName("gps_total") val gpsTotal: List<Int>? = null,
        @SerialName("uav_current") val uavCurrent: List<Int>? = null,
        @SerialName("uav_total") val uavTotal: List<Int>? = null,
        @SerialName("process_current") val processCurrent: List<Int>? = null,
        @SerialName("process_total") val processTotal: List<Int>? = null,
    )
}