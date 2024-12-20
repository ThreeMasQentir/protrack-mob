package org.gspi.protrack.feature.feat_dashboard.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.gspi.protrack.common.local.UserPreferences
import org.gspi.protrack.common.utils.handleApiResponse
import org.gspi.protrack.common.utils.handleApiResponseMeta
import org.gspi.protrack.feature.feat_dashboard.domain.projectsusecase.CreateProjectUseCase
import org.gspi.protrack.feature.feat_dashboard.domain.usersusecase.DeleteUserUseCase
import org.gspi.protrack.feature.feat_dashboard.domain.projectsusecase.GetListProjectUseCase
import org.gspi.protrack.feature.feat_dashboard.domain.usersusecase.GetListUserUseCase
import org.gspi.protrack.feature.feat_dashboard.domain.usersusecase.PostActiveUserUseCase
import org.gspi.protrack.feature.feat_dashboard.domain.usersusecase.PostCreateUserUseCase
import org.gspi.protrack.feature.feat_dashboard.domain.usersusecase.PostDeactiveUserUseCase
import org.gspi.protrack.feature.feat_dashboard.domain.usersusecase.PostUpdateUserUseCase
import org.gspi.protrack.feature.feat_dashboard.presentation.eventstate.DashboardEvent
import org.gspi.protrack.feature.feat_dashboard.presentation.eventstate.DashboardState

class DashboardViewModel(
    private val userPreferences: UserPreferences,
    private val getListProjectUseCase: GetListProjectUseCase,
    private val getListUserUseCase: GetListUserUseCase,
    private val postCreateUserUseCase: PostCreateUserUseCase,
    private val postUpdateUserUseCase: PostUpdateUserUseCase,
    private val deleteUserUseCase: DeleteUserUseCase,
    private val postDeactiveUserUseCase: PostDeactiveUserUseCase,
    private val postActiveUserUseCase: PostActiveUserUseCase,
    private val createProjectUseCase: CreateProjectUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(DashboardState())
    val uiState: StateFlow<DashboardState> = _uiState

    private fun updateUiState(state: DashboardState) {
        _uiState.value = state
    }

    fun onEvent(event: DashboardEvent) {
        when (event) {
            DashboardEvent.LoadListProject -> {
                updateUiState(
                    _uiState.value.copy(
                        isDialogVisible = false,
                        projectName = "",
                        projectstartDate = "",
                        projectEndDate = "",
                        projectAoiFileName = "Select File",
                        projectAoiByteArray = null,
                        projectRencanaTitikControlFileName = "Select File",
                        projectRencanaTitikControlByteArray = null,
                        metaResponse = null,
                    )
                )
                getAllProjects()
            }

            DashboardEvent.LoadListUser -> {
                updateUiState(
                    _uiState.value.copy(
                        isDialogVisible = false,
                        userIsEdit = false,
                        projectName = "",
                        projectstartDate = "",
                        projectEndDate = "",
                        projectAoiFileName = "Select File",
                        projectAoiByteArray = null,
                        projectRencanaTitikControlFileName = "Select File",
                        projectRencanaTitikControlByteArray = null,
                        metaResponse = null
                    )
                )
                getAllUsers()
            }

            is DashboardEvent.ClearError -> {
                updateUiState(_uiState.value.copy(errorMessage = null))
            }

            is DashboardEvent.OnLogout -> {
                logout()
            }

            is DashboardEvent.OnSearchValueChange -> {
                val searchValue = event.searchValue
                updateUiState(_uiState.value.copy(searchValue = searchValue))

                if (searchValue.isEmpty()) {
                    updateUiState(_uiState.value.copy(listProjectFiltered = _uiState.value.listProject))
                } else {
                    val filteredList = _uiState.value.listProject.filter {
                        it.projectName.contains(searchValue, ignoreCase = true)
                    }
                    updateUiState(_uiState.value.copy(listProjectFiltered = filteredList))
                }
            }

            is DashboardEvent.OnAddProjectClick -> {
                updateUiState(
                    _uiState.value.copy(
                        isDialogVisible = event.isDialogVisible,
                        userName = "",
                        userUsername = "",
                        userPassword = "",
                        userEmail = "",
                        userPhoneNumber = ""
                    )
                )
            }

            is DashboardEvent.OnContentTypeChange -> {
                updateUiState(_uiState.value.copy(contentType = event.contentType))
            }

            is DashboardEvent.OnAoiChange -> {
                updateUiState(
                    _uiState.value.copy(
                        projectAoiFileName = event.aoiFileName,
                        projectAoiByteArray = event.aoiByteArray
                    )
                )
            }

            is DashboardEvent.OnEndDateChange -> {
                updateUiState(_uiState.value.copy(projectEndDate = event.endDate))
            }

            is DashboardEvent.OnProjectNameChange -> {
                updateUiState(_uiState.value.copy(projectName = event.projectName))
            }

            is DashboardEvent.OnRencanaTitikControlChange -> {
                updateUiState(
                    _uiState.value.copy(
                        projectRencanaTitikControlFileName = event.rencanaTitikControlFileName,
                        projectRencanaTitikControlByteArray = event.rencanaTitikControlByteArray
                    )
                )
            }

            is DashboardEvent.OnSaveProjectClick -> {
                createProject()
            }

            is DashboardEvent.OnStartDateChange -> {
                updateUiState(_uiState.value.copy(projectstartDate = event.startDate))
            }

            is DashboardEvent.OnDeleteUserClick -> {
                deleteUser(event.id)
            }

            DashboardEvent.OnEditUserClick -> {
                updateUser()
            }

            DashboardEvent.OnSaveUserClick -> {
                updateUiState(_uiState.value.copy(isDialogVisible = false))
                createUser()
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
                changeUserStatus(event.id, event.isActive)
            }

            is DashboardEvent.OnUserUsernameChange -> {
                updateUiState(_uiState.value.copy(userUsername = event.userUsername))
            }

            is DashboardEvent.ShowEditUserDialog -> {
                updateUiState(
                    _uiState.value.copy(
                        userIsEdit = true,
                        isDialogVisible = true,
                        userId = event.id,
                        userName = event.userName,
                        userUsername = event.userUsername,
                        userPassword = event.userPassword,
                        userEmail = event.userEmail,
                        userPhoneNumber = event.userPhoneNumber
                    )
                )
            }

            is DashboardEvent.OnAddUserClick -> {
                updateUiState(
                    _uiState.value.copy(
                        isDialogVisible = event.isDialogVisible,
                        userIsEdit = false,
                        userName = "",
                        userUsername = "",
                        userPassword = "",
                        userEmail = "",
                        userPhoneNumber = ""
                    )
                )
            }

            is DashboardEvent.OnSearchUserValueChange -> {
                val searchValue = event.searchValue
                if (searchValue.isEmpty()) {
                    getAllUsers()
                    updateUiState(_uiState.value.copy(searchValue = searchValue))
                    return
                } else {
                    val filteredList = _uiState.value.listUsers.filter {
                        it.name.contains(searchValue, ignoreCase = true) ||
                                it.email.contains(searchValue, ignoreCase = true)
                    }
                    updateUiState(
                        _uiState.value.copy(
                            listUsers = filteredList,
                            searchValue = searchValue
                        )
                    )
                }

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
                    updateUiState(_uiState.value.copy(isLoading = false, listProject = response, listProjectFiltered = response))
                },
                onError = { error ->
                    println("errorload: $error")
                    updateUiState(_uiState.value.copy(isLoading = false, errorMessage = error))
                }
            )
        }
    }

    private fun getAllUsers() {
        updateUiState(_uiState.value.copy(isLoading = true))
        viewModelScope.launch {
            handleApiResponse(
                apiCall = { getListUserUseCase.execute() },
                onSuccess = { response ->
                    println("response: $response")
                    updateUiState(_uiState.value.copy(isLoading = false, listUsers = response))
                },
                onError = { error ->
                    println("errorload: $error")
                    updateUiState(_uiState.value.copy(isLoading = false, errorMessage = error))
                }
            )
        }
    }

    private fun createUser() {
        updateUiState(_uiState.value.copy(isLoading = true))
        viewModelScope.launch {
            handleApiResponseMeta(
                apiCall = {
                    postCreateUserUseCase.execute(
                        name = _uiState.value.userName,
                        password = _uiState.value.userPassword,
                        email = _uiState.value.userEmail,
                        phoneNumber = _uiState.value.userPhoneNumber
                    )
                },
                onSuccess = { response ->
                    updateUiState(_uiState.value.copy(isLoading = false, metaResponse = response))
                },
                onError = { error ->
                    updateUiState(
                        _uiState.value.copy(
                            isLoading = false,
                            errorMessage = error.message
                        )
                    )
                }
            )
        }
    }

    private fun updateUser() {
        updateUiState(_uiState.value.copy(isLoading = true))
        viewModelScope.launch {
            handleApiResponseMeta(
                apiCall = {
                    postUpdateUserUseCase.execute(
                        id = _uiState.value.userId,
                        name = _uiState.value.userName,
                        password = _uiState.value.userPassword,
                        email = _uiState.value.userEmail,
                        phoneNumber = _uiState.value.userPhoneNumber
                    )
                },
                onSuccess = { response ->
                    updateUiState(_uiState.value.copy(isLoading = false, metaResponse = response))
                },
                onError = { error ->
                    updateUiState(
                        _uiState.value.copy(
                            isLoading = false,
                            errorMessage = error.message
                        )
                    )
                }
            )
        }
    }

    private fun deleteUser(id: Int) {
        updateUiState(_uiState.value.copy(isLoading = true))
        viewModelScope.launch {
            handleApiResponseMeta(
                apiCall = {
                    deleteUserUseCase.execute(
                        id = id
                    )
                },
                onSuccess = { response ->
                    updateUiState(_uiState.value.copy(isLoading = false, metaResponse = response))
                },
                onError = { error ->
                    updateUiState(
                        _uiState.value.copy(
                            isLoading = false,
                            errorMessage = error.message
                        )
                    )
                }
            )
        }
    }

    private fun changeUserStatus(id: Int, isActive: Boolean) {
        updateUiState(_uiState.value.copy(isLoading = true))
        viewModelScope.launch {
            handleApiResponseMeta(
                apiCall = {
                    if (!isActive) {
                        postActiveUserUseCase.execute(id)
                    } else {
                        postDeactiveUserUseCase.execute(id)
                    }
                },
                onSuccess = { response ->
                    updateUiState(_uiState.value.copy(isLoading = false, metaResponse = response))
                },
                onError = { error ->
                    updateUiState(
                        _uiState.value.copy(
                            isLoading = false,
                            errorMessage = error.message
                        )
                    )
                }
            )
        }
    }

    private fun createProject() {
        updateUiState(_uiState.value.copy(isLoading = true,isDialogVisible = false))
        viewModelScope.launch {
            handleApiResponseMeta(
                apiCall = {
                    createProjectUseCase.execute(
                        name = _uiState.value.projectName,
                        startDate = _uiState.value.projectstartDate,
                        endDate = _uiState.value.projectEndDate,
                        aoi = _uiState.value.projectAoiByteArray,
                        rencanaTitikControl = _uiState.value.projectRencanaTitikControlByteArray
                    )
                },
                onSuccess = { response ->
                    updateUiState(_uiState.value.copy(isLoading = false, metaResponse = response))
                },
                onError = { error ->
                    updateUiState(
                        _uiState.value.copy(
                            isLoading = false,
                            errorMessage = error.message
                        )
                    )
                }
            )
        }
    }
}