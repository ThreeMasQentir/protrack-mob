package org.gspi.protrack.feature.feat_dashboard.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import gspiprotrack.composeapp.generated.resources.Res
import gspiprotrack.composeapp.generated.resources.ic_users
import org.gspi.protrack.feature.feat_dashboard.presentation.component.ItemUserComponent
import org.gspi.protrack.feature.feat_dashboard.presentation.component.NewProjectSearchComponent
import org.gspi.protrack.feature.feat_dashboard.presentation.dialog.AddEditUserComponent
import org.gspi.protrack.feature.feat_dashboard.presentation.eventstate.DashboardEvent
import org.gspi.protrack.feature.feat_dashboard.presentation.viewmodel.DashboardViewModel
import org.gspi.protrack.gspidesign.confirmation.ConfirmationDialog
import org.gspi.protrack.gspidesign.error.Error
import org.gspi.protrack.gspidesign.font.PoppinsFontFamily
import org.gspi.protrack.gspidesign.success.Success

@Composable
fun UsersContent(
    modifier: Modifier = Modifier,
    viewModel: DashboardViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.onEvent(DashboardEvent.LoadListUser)
    }

    LaunchedEffect(uiState.metaResponse) {
        uiState.metaResponse?.let {
            if (it.code == 200) {
                Success.show("Berhasil")
                viewModel.onEvent(DashboardEvent.LoadListUser)
            } else {
                Error.show("Failed to create project")
            }
        }
    }
    Box(modifier = modifier.fillMaxSize()) {

        AddEditUserComponent(
            isDialogVisible = uiState.isDialogVisible,
            onDialogVisibleChange = {
                viewModel.onEvent(DashboardEvent.OnAddProjectClick(it))
            },
            userName = uiState.userName,
            onUserNameChange = {
                viewModel.onEvent(DashboardEvent.OnUserNameChange(it))
            },
            userUserName = uiState.userUsername,
            onUserUserNameChange = {
                viewModel.onEvent(DashboardEvent.OnUserUsernameChange(it))
            },
            userPassword = uiState.userPassword,
            onUserPasswordChange = {
                viewModel.onEvent(DashboardEvent.OnUserPasswordChange(it))
            },
            userEmail = uiState.userEmail,
            onUserEmailChange = {
                viewModel.onEvent(DashboardEvent.OnUserEmailChange(it))
            },
            userPhoneNumber = uiState.userPhoneNumber,
            onUserPhoneNumberChange = {
                viewModel.onEvent(DashboardEvent.OnUserPhoneNumberChange(it))
            },
            onEditClick = {
                if (
                    uiState.userName.isNotEmpty() &&
                    uiState.userUsername.isNotEmpty() &&
                    uiState.userPassword.isNotEmpty() &&
                    uiState.userEmail.isNotEmpty() &&
                    uiState.userPhoneNumber.isNotEmpty()
                ) {
                    viewModel.onEvent(DashboardEvent.OnEditUserClick)
                } else {
                    Error.show("Please fill all fields")
                }

            },
            onSaveClick = {
                if (
                    uiState.userName.isNotEmpty() &&
                    uiState.userUsername.isNotEmpty() &&
                    uiState.userPassword.isNotEmpty() &&
                    uiState.userEmail.isNotEmpty() &&
                    uiState.userPhoneNumber.isNotEmpty()
                ) {
                    viewModel.onEvent(DashboardEvent.OnSaveUserClick)
                } else {
                    Error.show("Please fill all fields")
                }
            },
            isEdit = uiState.userIsEdit
        )
        Column(modifier = modifier.verticalScroll(rememberScrollState())) {

            Spacer(modifier = Modifier.padding(8.dp))
            NewProjectSearchComponent(
                title = "Users",
                buttonText = "New User",
                searchValue = uiState.searchValue,
                icon = Res.drawable.ic_users,
                onValueChange = {
                    viewModel.onEvent(DashboardEvent.OnSearchUserValueChange(it))
                },
                onButtonClick = {
                    viewModel.onEvent(DashboardEvent.OnAddUserClick(true))
                }
            )
            uiState.listUsers.forEach { item ->
                ItemUserComponent(
                    isActivated = item.isActivated == 1,
                    userName = item.name,
                    name = item.name,
                    password = item.password,
                    email = item.email,
                    phoneNumber = item.phone,
                    joinDate = item.dateCreated,
                    fontFamily = PoppinsFontFamily(),
                    onEditClick = {
                        viewModel.onEvent(
                            DashboardEvent.ShowEditUserDialog(
                                id = item.id,
                                userName = item.name,
                                userUsername = item.password,
                                userPassword = item.password,
                                userEmail = item.email,
                                userPhoneNumber = item.phone
                            )
                        )
                    },
                    onDeleteClick = {
                        ConfirmationDialog.show(
                            title = "Delete User",
                            message = "Are you sure you want to delete this user?",
                            onYesClick = {
                                viewModel.onEvent(DashboardEvent.OnDeleteUserClick(item.id))
                            },
                        )
                    },
                    onStatusClick = {
                        ConfirmationDialog.show(
                            title = "Change User Status",
                            message = "Are you sure you want to change this user status?",
                            onYesClick = {
                                viewModel.onEvent(DashboardEvent.OnUserStateChange(item.isActivated == 1, item.id))
                            },
                        )
                    })
            }

        }
    }


}