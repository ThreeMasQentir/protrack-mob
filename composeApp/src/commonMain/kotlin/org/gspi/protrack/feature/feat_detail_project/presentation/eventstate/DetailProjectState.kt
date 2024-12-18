package org.gspi.protrack.feature.feat_detail_project.presentation.eventstate

data class DetailProjectState(
    val updateProgressState: UpdateProgressState = UpdateProgressState(),
    val settingProjectState: SettingProjectState = SettingProjectState()
){
    data class UpdateProgressState(
        val isDialogUpdateVisible: Boolean = false,
        val currentTitikKonrol: String = "",
        val totalTitikKontrol: String = "",
        val currentFotoUdara: String = "",
        val totalFotoUdara: String = "",
        val currentPengolahanData: String = "",
        val totalPengolahanData: String = "",
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