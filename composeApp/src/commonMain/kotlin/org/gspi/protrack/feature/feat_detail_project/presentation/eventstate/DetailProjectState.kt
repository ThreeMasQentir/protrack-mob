package org.gspi.protrack.feature.feat_detail_project.presentation.eventstate

import org.gspi.protrack.common.model.Meta
import org.gspi.protrack.feature.feat_detail_project.data.model.response.DetailProjectResponse
import org.gspi.protrack.feature.feat_detail_project.data.model.response.ItemDocumentResponse
import org.gspi.protrack.feature.feat_detail_project.data.model.response.ItemLogResponse

data class DetailProjectState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val detailProject: DetailProjectResponse? = null,
    val listLog: List<ItemLogResponse>? = null,
    val listDocument: List<ItemDocumentResponse>? = null,
    val metaResponse: Meta? = null,
    val idProject: Int = 0,
    val updateProgressState: UpdateProgressState = UpdateProgressState(),
    val settingProjectState: SettingProjectState = SettingProjectState(),
    val documentState: DocumentState = DocumentState(),
){
    data class DocumentState(
        val isDialogDocumentVisible: Boolean = false,
        val documentName: String = "Select File",
        val documentByteArray: ByteArray? = null,
    )

    data class UpdateProgressState(
        val isDialogUpdateVisible: Boolean = false,
        val currentTitikKonrol: Int = 0,
        val totalTitikKontrol: Int = 0,
        val currentFotoUdara: Int = 0,
        val totalFotoUdara: Int = 0,
        val currentPengolahanData: Int = 0,
        val totalPengolahanData: Int = 0,
    )

    data class SettingProjectState(
        val isDialogSettingVisible: Boolean = false,
        val projectName: String = "",
        val startDate: String = "",
        val endDate: String = "",
        val aoiFileName: String = "Select File",
        val aoiByteArray: ByteArray? = null,
        val rencanaTitikControlFileName: String = "Select File",
        val rencanaTitikControlByteArray: ByteArray? = null,

    )
}