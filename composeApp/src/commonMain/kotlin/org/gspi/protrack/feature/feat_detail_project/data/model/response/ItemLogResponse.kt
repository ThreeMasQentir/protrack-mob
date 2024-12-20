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

)

