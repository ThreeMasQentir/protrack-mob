package org.gspi.protrack.feature.feat_login.data.model.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DecoderTokenResponse(
    @SerialName("name")
    val name: String = "",
    @SerialName("phone")
    val phone: String = "",
    @SerialName("role")
    val role: List<String> = listOf()
)