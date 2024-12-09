package org.gspi.protrack.feature.feat_login.data.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class LoginRequest(
    @SerialName("password")
    val password: String = "",
    @SerialName("phone")
    val phone: String = ""
)