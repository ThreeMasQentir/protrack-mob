package org.gspi.protrack.feature.feat_detail_project.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.gspi.protrack.common.utils.handleApiResponse
import org.gspi.protrack.common.utils.handleApiResponseMeta
import org.gspi.protrack.feature.feat_detail_project.domain.usecase.document.DeleteDocumentUseCase
import org.gspi.protrack.feature.feat_detail_project.domain.usecase.document.GetListDocumentUseCase
import org.gspi.protrack.feature.feat_detail_project.domain.usecase.document.UploadDocumentUseCase
import org.gspi.protrack.feature.feat_detail_project.domain.usecase.log.GetListLogUseCase
import org.gspi.protrack.feature.feat_detail_project.domain.usecase.project.DeleteProjectUseCase
import org.gspi.protrack.feature.feat_detail_project.domain.usecase.project.GetDetailProjectUseCase
import org.gspi.protrack.feature.feat_detail_project.domain.usecase.project.PostSettingProjectUseCase
import org.gspi.protrack.feature.feat_detail_project.domain.usecase.progress.PostUpdateProgressUseCase
import org.gspi.protrack.feature.feat_detail_project.presentation.eventstate.DetailProjectEvent
import org.gspi.protrack.feature.feat_detail_project.presentation.eventstate.DetailProjectState
import org.gspi.protrack.feature.feat_detail_project.presentation.eventstate.DetailProjectState.*

class DetailProjectViewModel(
    private val getDetailProjectUseCase: GetDetailProjectUseCase,
    private val deleteProjectUseCase: DeleteProjectUseCase,
    private val postUpdateProgressUseCase: PostUpdateProgressUseCase,
    private val postSettingProjectUseCase: PostSettingProjectUseCase,
    private val getListLogUseCase: GetListLogUseCase,
    private val getListDocumentUseCase: GetListDocumentUseCase,
    private val deleteDocumentUseCase: DeleteDocumentUseCase,
    private val uploadDocumentUseCase: UploadDocumentUseCase
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
                    currentPengolahanData = event.currentPengolahanData)))
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
            DetailProjectEvent.OnDeleteProject -> {
                deleteProject()
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
                updateUiState(_uiState.value.copy(settingProjectState = _uiState.value.settingProjectState.copy(isDialogSettingVisible = false)))
                postUpdateSettingProject()
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

            DetailProjectEvent.LoadDocumentList -> {
                resetDocumentState()
                getListDocument()
            }
            DetailProjectEvent.LoadLogList -> {
                getListLog()
            }
            is DetailProjectEvent.OnAddDocumentClick -> {
                updateUiState(_uiState.value.copy(documentState = _uiState.value.documentState.copy(isDialogDocumentVisible = event.isDialogVisible)))
            }
            DetailProjectEvent.OnCancelDocument -> {
                resetDocumentState()
            }
            is DetailProjectEvent.OnDeleteDocument -> {
                deleteDocument(event.documentId)
            }
            is DetailProjectEvent.OnDocumentFileChange -> {
                updateUiState(_uiState.value.copy(documentState = _uiState.value.documentState.copy(
                    documentByteArray = event.documentByteArray, documentButtonName = event.documentName
                )))
            }
            is DetailProjectEvent.OnDocumentNameChange -> {
                updateUiState(_uiState.value.copy(documentState = _uiState.value.documentState.copy(
                    documentName = event.documentName
                )))
            }
            DetailProjectEvent.OnDownloadDocument -> {

            }
            DetailProjectEvent.OnSaveDocument -> {
                updateUiState(_uiState.value.copy(documentState = _uiState.value.documentState.copy(isDialogDocumentVisible = false)))
                uploadDocument()
            }

            is DetailProjectEvent.OnChangeContentType -> {
                updateUiState(_uiState.value.copy(contentType = event.contentType))
            }
        }
    }

    private fun resetDocumentState() {
        updateUiState(_uiState.value.copy(documentState = DocumentState()))
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

    private fun deleteProject() {
        updateUiState(_uiState.value.copy(isLoading = true))
        viewModelScope.launch {
            handleApiResponseMeta(
                apiCall = { deleteProjectUseCase.execute(_uiState.value.idProject) },
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

    private fun postUpdateSettingProject() {
        updateUiState(_uiState.value.copy(isLoading = true))
        viewModelScope.launch {
            handleApiResponseMeta(
                apiCall = { postSettingProjectUseCase.execute(
                    id = _uiState.value.idProject,
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

    fun getListLog() {
        updateUiState(_uiState.value.copy(isLoading = true))
        viewModelScope.launch {
            handleApiResponse(
                apiCall = { getListLogUseCase.execute(_uiState.value.idProject) },
                onSuccess = { response ->
                    updateUiState(_uiState.value.copy(isLoading = false, listLog = response))
                },
                onError = { errorMessage ->
                    updateUiState(_uiState.value.copy(isLoading = false, errorMessage = errorMessage))
                }
            )
        }
    }

    fun getListDocument() {
        updateUiState(_uiState.value.copy(isLoading = true))
        viewModelScope.launch {
            handleApiResponse(
                apiCall = { getListDocumentUseCase.execute(_uiState.value.idProject) },
                onSuccess = { response ->
                    updateUiState(_uiState.value.copy(isLoading = false, listDocument = response))
                },
                onError = { errorMessage ->
                    updateUiState(_uiState.value.copy(isLoading = false, errorMessage = errorMessage))
                }
            )
        }
    }

    fun deleteDocument(id: Int) {
        updateUiState(_uiState.value.copy(isLoading = true))
        viewModelScope.launch {
            handleApiResponseMeta(
                apiCall = { deleteDocumentUseCase.execute(id) },
                onSuccess = { response ->
                    updateUiState(_uiState.value.copy(isLoading = false, metaResponse = response))
                },
                onError = { errorMessage ->
                    updateUiState(_uiState.value.copy(isLoading = false, errorMessage = errorMessage.message))
                }
            )
        }
    }

    fun uploadDocument() {
        updateUiState(_uiState.value.copy(isLoading = true))
        viewModelScope.launch {
            handleApiResponseMeta(
                apiCall = { uploadDocumentUseCase.execute(
                    id = _uiState.value.idProject,
                    documentName = _uiState.value.documentState.documentName,
                    documentFile = _uiState.value.documentState.documentByteArray!!
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