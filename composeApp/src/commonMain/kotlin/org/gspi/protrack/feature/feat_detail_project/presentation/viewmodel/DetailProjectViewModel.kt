package org.gspi.protrack.feature.feat_detail_project.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.gspi.protrack.common.utils.handleApiResponse
import org.gspi.protrack.common.utils.handleApiResponseMeta
import org.gspi.protrack.feature.feat_detail_project.domain.usecase.DeleteProjectUseCase
import org.gspi.protrack.feature.feat_detail_project.domain.usecase.GetDetailProjectUseCase
import org.gspi.protrack.feature.feat_detail_project.domain.usecase.PostSettingProjectUseCase
import org.gspi.protrack.feature.feat_detail_project.domain.usecase.PostUpdateProgressUseCase
import org.gspi.protrack.feature.feat_detail_project.presentation.eventstate.DetailProjectEvent
import org.gspi.protrack.feature.feat_detail_project.presentation.eventstate.DetailProjectState
import org.gspi.protrack.feature.feat_detail_project.presentation.eventstate.DetailProjectState.*

class DetailProjectViewModel(
    private val getDetailProjectUseCase: GetDetailProjectUseCase,
    private val deleteProjectUseCase: DeleteProjectUseCase,
    private val postUpdateProgressUseCase: PostUpdateProgressUseCase,
    private val postSettingProjectUseCase: PostSettingProjectUseCase
): ViewModel() {
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
                    totalPengolahanData = event.totalPengolahanData,
                    currentTitikKonrol = event.currentTitikKonrol,
                    currentFotoUdara = event.currentFotoUdara,
                    currentPengolahanData = event.currentPengolahanData,

                    )))
            }

            DetailProjectEvent.OnCancelUpdateProgress -> {
                updateUiState(_uiState.value.copy(updateProgressState = _uiState.value.updateProgressState.copy(isDialogUpdateVisible = false)))
            }
            DetailProjectEvent.OnSaveUpdateProgress -> {
                updateUiState(_uiState.value.copy(updateProgressState = _uiState.value.updateProgressState.copy(isDialogUpdateVisible = false)))
                postUpdateProgress()
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

            is DetailProjectEvent.OnGetDetailProject -> {
                resetState()
                updateUiState(_uiState.value.copy(idProject = event.projectId.toInt()))
                getDetailProject()
            }
            DetailProjectEvent.ClearError -> {
                updateUiState(_uiState.value.copy(errorMessage = null))
            }
        }
    }

    private fun resetState() {
        updateUiState(DetailProjectState())
    }

    private fun getDetailProject() {
        updateUiState(_uiState.value.copy(isLoading = true))
        viewModelScope.launch {
            handleApiResponse(
                apiCall = { getDetailProjectUseCase.execute(_uiState.value.idProject) },
                onSuccess = { response ->
                    updateUiState(_uiState.value.copy(isLoading = false, detailProject = response))
                },
                onError = { errorMessage ->
                    updateUiState(_uiState.value.copy(isLoading = false, errorMessage = errorMessage))
                }
            )
        }
    }

    private fun deleteProject(id: Int) {
        updateUiState(_uiState.value.copy(isLoading = true))
        viewModelScope.launch {
            handleApiResponseMeta(
                apiCall = { deleteProjectUseCase.execute(id) },
                onSuccess = { response ->
                    updateUiState(_uiState.value.copy(isLoading = false, metaResponse = response))
                },
                onError = { errorMessage ->
                    updateUiState(_uiState.value.copy(isLoading = false, errorMessage = errorMessage.message))
                }
            )
        }
    }

    private fun postUpdateProgress() {
        updateUiState(_uiState.value.copy(isLoading = true))
        viewModelScope.launch {
            handleApiResponseMeta(
                apiCall = { postUpdateProgressUseCase.execute(
                    id = _uiState.value.idProject,
                    currentTitikControl = _uiState.value.updateProgressState.currentTitikKonrol,
                    totalTitikControl = _uiState.value.updateProgressState.totalTitikKontrol,
                    currentFotoUdara = _uiState.value.updateProgressState.currentFotoUdara,
                    totalFotoUdara = _uiState.value.updateProgressState.totalFotoUdara,
                    currentPengolahanLahan = _uiState.value.updateProgressState.currentPengolahanData,
                    totalPengolahanLahan = _uiState.value.updateProgressState.totalPengolahanData
                    ) },
                onSuccess = { response ->
                    updateUiState(_uiState.value.copy(isLoading = false, metaResponse = response))
                },
                onError = { errorMessage ->
                    updateUiState(_uiState.value.copy(isLoading = false, errorMessage = errorMessage.message))
                }
            )
        }
    }

    private fun postUpdateSettingProject(id: Int) {
        updateUiState(_uiState.value.copy(isLoading = true))
        viewModelScope.launch {
            handleApiResponseMeta(
                apiCall = { postSettingProjectUseCase.execute(
                    id = id,
                    projectName = _uiState.value.settingProjectState.projectName,
                    startDate = _uiState.value.settingProjectState.startDate,
                    endDate = _uiState.value.settingProjectState.endDate,
                    aoiFile = _uiState.value.settingProjectState.aoiByteArray,
                    aoiFileName = _uiState.value.settingProjectState.aoiFileName,
                    rencanaTitikControlFileName = _uiState.value.settingProjectState.rencanaTitikControlFileName,
                    rencanaTitikControlFile = _uiState.value.settingProjectState.rencanaTitikControlByteArray
                ) },
                onSuccess = { response ->
                    updateUiState(_uiState.value.copy(isLoading = false, metaResponse = response))
                },
                onError = { errorMessage ->
                    updateUiState(_uiState.value.copy(isLoading = false, errorMessage = errorMessage.message))
                }
            )
        }
    }
}