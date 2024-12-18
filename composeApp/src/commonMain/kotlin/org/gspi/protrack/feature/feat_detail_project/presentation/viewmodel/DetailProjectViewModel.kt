package org.gspi.protrack.feature.feat_detail_project.presentation.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.gspi.protrack.feature.feat_detail_project.presentation.eventstate.DetailProjectEvent
import org.gspi.protrack.feature.feat_detail_project.presentation.eventstate.DetailProjectState
import org.gspi.protrack.feature.feat_detail_project.presentation.eventstate.DetailProjectState.*

class DetailProjectViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(DetailProjectState())
    val uiState: StateFlow<DetailProjectState> = _uiState

    private fun updateUiState(state: DetailProjectState) {
        _uiState.value = state
    }


    fun onEvent(event: DetailProjectEvent) {
        when (event) {
            is DetailProjectEvent.OnUpdateButtonClick -> {
                updateUiState(_uiState.value.copy(updateProgressState = _uiState.value.updateProgressState.copy(
                    isDialogUpdateVisible = event.isDialogVisible,
                    totalTitikKontrol = event.totalTitikControl,
                    totalFotoUdara = event.totalFotoUdara,
                    totalPengolahanData = event.totalPengolahanData
                    )))
            }

            DetailProjectEvent.OnCancelUpdateProgress -> {
                updateUiState(_uiState.value.copy(updateProgressState = _uiState.value.updateProgressState.copy(isDialogUpdateVisible = false)))
            }
            DetailProjectEvent.OnSaveUpdateProgress -> {
                //todo: call api
                updateUiState(_uiState.value.copy(updateProgressState = _uiState.value.updateProgressState.copy(isDialogUpdateVisible = false)))
            }
            DetailProjectEvent.OnClearProgress -> {
                updateUiState(_uiState.value.copy(updateProgressState = UpdateProgressState()))
            }
            is DetailProjectEvent.OnCurrentFotoUdaraChange -> {
                updateUiState(_uiState.value.copy(updateProgressState = _uiState.value.updateProgressState.copy(currentFotoUdara = event.fotoUdara)))
            }
            is DetailProjectEvent.OnCurrentPengolahanDataChange -> {
                updateUiState(_uiState.value.copy(updateProgressState = _uiState.value.updateProgressState.copy(currentPengolahanData = event.pengolahanData)))
            }
            is DetailProjectEvent.OnCurrentTitikControlChange -> {
                updateUiState(_uiState.value.copy(updateProgressState = _uiState.value.updateProgressState.copy(currentTitikKonrol = event.currentTitikControl)))
            }
            is DetailProjectEvent.OnTotalFotoUdaraChange -> {
                updateUiState(_uiState.value.copy(updateProgressState = _uiState.value.updateProgressState.copy(totalFotoUdara = event.totalFotoUdara)))
            }
            is DetailProjectEvent.OnTotalPengolahanDataChange -> {
                updateUiState(_uiState.value.copy(updateProgressState = _uiState.value.updateProgressState.copy(totalPengolahanData = event.totalPengolahanData)))
            }
            is DetailProjectEvent.OnTotalTitikControlChange -> {
                updateUiState(_uiState.value.copy(updateProgressState = _uiState.value.updateProgressState.copy(totalTitikKontrol = event.totalTitikControl)))
            }

            is DetailProjectEvent.OnAoiFileChange -> {
                updateUiState(_uiState.value.copy(settingProjectState = _uiState.value.settingProjectState.copy(
                    aoiFileName = event.aoiFileName,
                    aoiByteArray = event.aoiByteArray
                )))
            }
            DetailProjectEvent.OnCancelSettingProject -> {
                updateUiState(_uiState.value.copy(settingProjectState = _uiState.value.settingProjectState.copy(isDialogSettingVisible = false)))
            }
            DetailProjectEvent.OnClearSettingProject -> {
                updateUiState(_uiState.value.copy(settingProjectState = SettingProjectState()))
            }
            is DetailProjectEvent.OnDeleteProject -> {
                //todo: call api
            }
            is DetailProjectEvent.OnEndDateChange -> {
                updateUiState(_uiState.value.copy(settingProjectState = _uiState.value.settingProjectState.copy(endDate = event.endDate)))
            }
            is DetailProjectEvent.OnProjectNameChange -> {
                updateUiState(_uiState.value.copy(settingProjectState = _uiState.value.settingProjectState.copy(projectName = event.projectName)))
            }
            is DetailProjectEvent.OnRencanaTitikControlFileChange -> {
                updateUiState(_uiState.value.copy(settingProjectState = _uiState.value.settingProjectState.copy(
                    rencanaTitikControlFileName = event.rencanaTitikControlFileName,
                    rencanaTitikControlByteArray = event.rencanaTitikControlByteArray,
                )))
            }
            DetailProjectEvent.OnSaveSettingProject -> {
                //todo: call api
                updateUiState(_uiState.value.copy(settingProjectState = _uiState.value.settingProjectState.copy(isDialogSettingVisible = false)))
            }
            is DetailProjectEvent.OnSettingProjectClick -> {
                updateUiState(_uiState.value.copy(settingProjectState = _uiState.value.settingProjectState.copy(
                    isDialogSettingVisible = event.isDialogVisible,
                    projectName = event.projectName,
                    startDate = event.startDate,
                    endDate = event.endDate,
                    aoiFileName = event.aoiFileName,
                    rencanaTitikControlFileName = event.rencanaTitikControlFileName
                )))
            }
            is DetailProjectEvent.OnStartDateChange -> {
                updateUiState(_uiState.value.copy(settingProjectState = _uiState.value.settingProjectState.copy(startDate = event.startDate)))
            }
        }
    }
}