package org.gspi.protrack.feature.feat_detail_project.data.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddDocumentRequest(
    @SerialName("document_name") val documentName: String,
    @SerialName("document") val document: ByteArray,
    val documentRealName: String? = null
)