package org.gspi.protrack.feature.feat_dashboard.data.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddUpdateUserRequest(
    @SerialName("name")
    val name: String,

    @SerialName("password")
    val password: String,

    @SerialName("email")
    val email: String,

    @SerialName("phone")
    val phone: String
)