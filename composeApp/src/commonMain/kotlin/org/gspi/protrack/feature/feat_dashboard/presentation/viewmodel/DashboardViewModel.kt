package org.gspi.protrack.feature.feat_dashboard.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.gspi.protrack.common.local.UserPreferences
import org.gspi.protrack.common.utils.handleApiResponse
import org.gspi.protrack.feature.feat_dashboard.domain.GetListProjectUseCase
import org.gspi.protrack.feature.feat_dashboard.presentation.eventstate.DashboardEvent
import org.gspi.protrack.feature.feat_dashboard.presentation.eventstate.DashboardState

class DashboardViewModel(
    private val userPreferences: UserPreferences,
    private val getListProjectUseCase: GetListProjectUseCase
    ) : ViewModel() {

    private val _uiState = MutableStateFlow(DashboardState())
    val uiState: StateFlow<DashboardState> = _uiState

    private fun updateUiState(state: DashboardState) {
        _uiState.value = state
    }

    fun onEvent(event: DashboardEvent) {
        when (event) {
            is DashboardEvent.LoadListProject -> {
                getAllProjects()
            }
            is DashboardEvent.ClearError -> {
                updateUiState(_uiState.value.copy(errorMessage = null))
            }
            is DashboardEvent.OnLogout -> {
                logout()
            }

            is DashboardEvent.OnSearchValueChange -> {
                updateUiState(_uiState.value.copy(searchValue = event.searchValue))
            }

            is DashboardEvent.OnAddProjectClick -> {
                updateUiState(_uiState.value.copy(isDialogVisible = event.isDialogVisible))
            }

            is DashboardEvent.OnContentTypeChange -> {
                updateUiState(_uiState.value.copy(contentType = event.contentType))
            }

            is DashboardEvent.OnAoiChange -> {
                updateUiState(_uiState.value.copy(projectAoiFileName = event.aoiFileName, projectAoiByteArray = event.aoiByteArray))
            }
            is DashboardEvent.OnEndDateChange -> {
                updateUiState(_uiState.value.copy(projectEndDate = event.endDate))
            }
            is DashboardEvent.OnProjectNameChange -> {
                updateUiState(_uiState.value.copy(projectName = event.projectName))
            }
            is DashboardEvent.OnRencanaTitikControlChange -> {
                updateUiState(_uiState.value.copy(projectRencanaTitikControlFileName = event.rencanaTitikControlFileName, projectRencanaTitikControlByteArray = event.rencanaTitikControlByteArray))
            }
            is DashboardEvent.OnSaveProjectClick -> {
                //todo: call api function
                updateUiState(_uiState.value.copy(isDialogVisible = false))
            }
            is DashboardEvent.OnStartDateChange -> {
                updateUiState(_uiState.value.copy(projectstartDate = event.startDate))
            }
            DashboardEvent.ClearSaveProjectState -> {
                updateUiState(_uiState.value.copy(projectName = "", projectstartDate = "", projectEndDate = "", projectAoiFileName = "Select File", projectAoiByteArray = null, projectRencanaTitikControlFileName = "Select File", projectRencanaTitikControlByteArray = null))
            }

            DashboardEvent.ClearSaveUserState -> {
                updateUiState(_uiState.value.copy(
                    isDialogVisible = false,
                    userName = "", userUsername = "", userPassword = "", userEmail = "", userPhoneNumber = ""))
            }
            is DashboardEvent.OnDeleteUserClick -> {
                //todo: call api function
            }
            DashboardEvent.OnEditUserClick -> {
                //todo: call api function

            }
            DashboardEvent.OnSaveUserClick -> {
                //todo: call api function
            }
            is DashboardEvent.OnUserEmailChange -> {
                updateUiState(_uiState.value.copy(userEmail = event.userEmail))
            }
            is DashboardEvent.OnUserNameChange -> {
                updateUiState(_uiState.value.copy(userName = event.userName))
            }
            is DashboardEvent.OnUserPasswordChange -> {
                updateUiState(_uiState.value.copy(userPassword = event.userPassword))
            }
            is DashboardEvent.OnUserPhoneNumberChange -> {
                updateUiState(_uiState.value.copy(userPhoneNumber = event.userPhoneNumber))
            }
            is DashboardEvent.OnUserStateChange -> {
                updateUiState(_uiState.value.copy(userIsActive = event.isActive))
            }
            is DashboardEvent.OnUserUsernameChange -> {
                updateUiState(_uiState.value.copy(userUsername = event.userUsername))
            }
            is DashboardEvent.ShowEditUserDialog -> {
                updateUiState(_uiState.value.copy(
                    isDialogVisible = true,
                    userName = event.userName, userUsername = event.userUsername, userPassword = event.userPassword, userEmail = event.userEmail, userPhoneNumber = event.userPhoneNumber))
            }
        }
    }

    private fun logout() {
        viewModelScope.launch {
            userPreferences.clearAll()
        }
    }

    private fun getAllProjects() {
        updateUiState(_uiState.value.copy(isLoading = true))
        viewModelScope.launch {
            handleApiResponse(
                apiCall = { getListProjectUseCase.execute() },
                onSuccess = { response ->
                    println("response: $response")
                    updateUiState(_uiState.value.copy(isLoading = false, listProject = response))
                },
                onError = { error ->
                    println("errorload: $error")
                    updateUiState(_uiState.value.copy(isLoading = false, errorMessage = error))
                }
            )
        }
    }
}