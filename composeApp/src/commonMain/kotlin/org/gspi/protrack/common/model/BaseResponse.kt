package org.gspi.protrack.common.model

import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    val data: T? = null,
    val meta: Meta
)
