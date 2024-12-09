package org.gspi.protrack.feature.feat_login.data.model.response
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    @SerialName("token")
    val token: String = ""
)