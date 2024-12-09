package org.gspi.protrack.common.model

import kotlinx.serialization.Serializable

@Serializable
data class Meta(
    val code: Int = 200,
    val message: String = "Success",
)
