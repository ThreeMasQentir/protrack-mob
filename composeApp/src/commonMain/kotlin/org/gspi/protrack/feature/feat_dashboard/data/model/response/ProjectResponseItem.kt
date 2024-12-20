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
    val uavTotal: Int = 0,
    @SerialName("process_current")
    val processCurrent: Int = 0,
    @SerialName("process_total")
    val processTotal: Int = 0
){
    val progress: Int
    get() {
        val total = gpsTotal + uavTotal + processTotal
        return if (total == 0) 0 else (gpsCurrent + uavCurrent + processCurrent) * 100 / total
    }

    val progressUav: Int
    get() {
        return if (uavTotal == 0) 0 else uavCurrent * 100 / uavTotal
    }

    val progressGps: Int
    get() {
        return if (gpsTotal == 0) 0 else gpsCurrent * 100 / gpsTotal
    }

    val progressProcess: Int
    get() {
        return if (processTotal == 0) 0 else processCurrent * 100 / processTotal
    }

}