package org.gspi.protrack.feature.feat_detail_project.presentation.eventstate

sealed class DetailProjectEvent {

    //get detail project
    data class OnGetDetailProject(val projectId: String) : DetailProjectEvent()
    data object ClearError: DetailProjectEvent()

    //update progress
    data class OnUpdateButtonClick(
        val isDialogVisible: Boolean,
        val totalTitikControl: Int,
        val totalFotoUdara: Int,
        val totalPengolahanData: Int,
        val currentTitikKonrol: Int,
        val currentFotoUdara: Int,
        val currentPengolahanData: Int,
    ) : DetailProjectEvent()
    data object OnSaveUpdateProgress: DetailProjectEvent()
    data object OnCancelUpdateProgress: DetailProjectEvent()
    data class OnCurrentTitikControlChange(val currentTitikControl: Int) : DetailProjectEvent()
    data class OnTotalTitikControlChange(val totalTitikControl: Int) : DetailProjectEvent()
    data class OnCurrentFotoUdaraChange(val fotoUdara: Int) : DetailProjectEvent()
    data class OnTotalFotoUdaraChange(val totalFotoUdara: Int) : DetailProjectEvent()
    data class OnCurrentPengolahanDataChange(val pengolahanData: Int) : DetailProjectEvent()
    data class OnTotalPengolahanDataChange(val totalPengolahanData: Int) : DetailProjectEvent()
    data object OnClearProgress : DetailProjectEvent()
    data class OnChangeContentType(val contentType: DetailProjectState.ContentType) : DetailProjectEvent()

    //setting project
    data class OnSettingProjectClick(
        val isDialogVisible: Boolean,
        val projectName: String,
        val startDate: String,
        val endDate: String,
        val aoiFileName: String,
        val rencanaTitikControlFileName: String,
    ) : DetailProjectEvent()
    data object OnSaveSettingProject: DetailProjectEvent()
    data object OnCancelSettingProject: DetailProjectEvent()
    data object OnDeleteProject : DetailProjectEvent()
    data class OnProjectNameChange(val projectName: String) : DetailProjectEvent()
    data class OnStartDateChange(val startDate: String) : DetailProjectEvent()
    data class OnEndDateChange(val endDate: String) : DetailProjectEvent()
    data class OnAoiFileChange(val aoiFileName: String, val aoiByteArray: ByteArray?) : DetailProjectEvent()
    data class OnRencanaTitikControlFileChange(val rencanaTitikControlFileName: String, val rencanaTitikControlByteArray: ByteArray?) : DetailProjectEvent()
    data object OnClearSettingProject : DetailProjectEvent()
    data object OnDeleteAoiFileName : DetailProjectEvent()
    data object OnDeleteRencanaTitikControlFileName : DetailProjectEvent()

    //document
    data object LoadDocumentList : DetailProjectEvent()
    data class OnAddDocumentClick(val isDialogVisible: Boolean) : DetailProjectEvent()
    data object OnSaveDocument : DetailProjectEvent()
    data object OnCancelDocument : DetailProjectEvent()
    data class OnDocumentNameChange(val documentName: String) : DetailProjectEvent()
    data class OnDocumentFileChange(val documentByteArray: ByteArray?, val documentName: String) : DetailProjectEvent()
    data class OnDeleteDocument(val documentId: Int) : DetailProjectEvent()
    data object OnDownloadDocument : DetailProjectEvent()

    //log
    data object LoadLogList : DetailProjectEvent()

}