package org.gspi.protrack.feature.feat_dashboard.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResponseItem(
    @SerialName("id")
    val id: Int,

    @SerialName("phone")
    val phone: String,

    @SerialName("password")
    val password: String,

    @SerialName("name")
    val name: String,

    @SerialName("email")
    val email: String,

    @SerialName("isActivated")
    val isActivated: Int,

    @SerialName("date_created")
    val dateCreated: String
)