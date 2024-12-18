package org.gspi.protrack.feature.feat_detail_project.presentation.eventstate

sealed class DetailProjectEvent {
    //update progress
    data class OnUpdateButtonClick(
        val isDialogVisible: Boolean,
        val totalTitikControl: String,
        val totalFotoUdara: String,
        val totalPengolahanData: String
    ) : DetailProjectEvent()
    data object OnSaveUpdateProgress: DetailProjectEvent()
    data object OnCancelUpdateProgress: DetailProjectEvent()
    data class OnCurrentTitikControlChange(val currentTitikControl: String) : DetailProjectEvent()
    data class OnTotalTitikControlChange(val totalTitikControl: String) : DetailProjectEvent()
    data class OnCurrentFotoUdaraChange(val fotoUdara: String) : DetailProjectEvent()
    data class OnTotalFotoUdaraChange(val totalFotoUdara: String) : DetailProjectEvent()
    data class OnCurrentPengolahanDataChange(val pengolahanData: String) : DetailProjectEvent()
    data class OnTotalPengolahanDataChange(val totalPengolahanData: String) : DetailProjectEvent()
    data object OnClearProgress : DetailProjectEvent()

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
    data class OnDeleteProject(val projectId: String) : DetailProjectEvent()
    data class OnProjectNameChange(val projectName: String) : DetailProjectEvent()
    data class OnStartDateChange(val startDate: String) : DetailProjectEvent()
    data class OnEndDateChange(val endDate: String) : DetailProjectEvent()
    data class OnAoiFileChange(val aoiFileName: String, val aoiByteArray: ByteArray?) : DetailProjectEvent()
    data class OnRencanaTitikControlFileChange(val rencanaTitikControlFileName: String, val rencanaTitikControlByteArray: ByteArray?) : DetailProjectEvent()
    data object OnClearSettingProject : DetailProjectEvent()


}