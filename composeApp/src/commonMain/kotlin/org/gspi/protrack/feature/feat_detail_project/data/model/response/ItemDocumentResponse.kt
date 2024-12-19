package org.gspi.protrack.feature.feat_detail_project.data.model.response

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class ItemDocumentResponse(
    @SerialName("id") val id: Int,
    @SerialName("id_project") val projectId: Int,
    @SerialName("file_name") val fileName: String,
    @SerialName("document_name") val documentName: String,
    @SerialName("path") val path: String,
    @SerialName("date_uploaded") val dateUploaded: String
)