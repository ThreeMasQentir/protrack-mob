package org.gspi.protrack.feature.feat_dashboard.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import gspiprotrack.composeapp.generated.resources.Res
import gspiprotrack.composeapp.generated.resources.ic_users
import org.gspi.protrack.feature.feat_dashboard.presentation.component.ItemUserComponent
import org.gspi.protrack.feature.feat_dashboard.presentation.component.NewProjectSearchComponent
import org.gspi.protrack.feature.feat_dashboard.presentation.dialog.AddEditUserComponent
import org.gspi.protrack.feature.feat_dashboard.presentation.eventstate.DashboardEvent
import org.gspi.protrack.feature.feat_dashboard.presentation.viewmodel.DashboardViewModel
import org.gspi.protrack.gspidesign.confirmation.ConfirmationDialog
import org.gspi.protrack.gspidesign.font.PoppinsFontFamily

@Composable
fun UsersContent(modifier: Modifier = Modifier,
                 navController: NavController,
                 viewModel: DashboardViewModel
) {
    val uiState by viewModel.uiState.collectAsState()

    Box(modifier = modifier.fillMaxSize()) {

        AddEditUserComponent(
            isDialogVisible = uiState.isDialogVisible,
            onDialogVisibleChange = {
                viewModel.onEvent(DashboardEvent.OnAddProjectClick(it))
            }
        )
        Column(modifier = modifier.verticalScroll(rememberScrollState())) {

            Spacer(modifier = Modifier.padding(8.dp))
            NewProjectSearchComponent(
                title = "Users",
                buttonText = "New User",
                searchValue = uiState.searchValue,
                icon = Res.drawable.ic_users,
                onValueChange = {
                    viewModel.onEvent(DashboardEvent.OnSearchValueChange(it))
                },
                onButtonClick = {
                    viewModel.onEvent(DashboardEvent.OnAddProjectClick(true))
                }
            )

            ItemUserComponent(
                isActivated = true, // true for active (green), false for inactive (gray)
                userName = "PM Yogyakarta", // Title in the green/gray background
                name = "Akhtar", // Name field
                password = "Akhtar123", // Password field
                email = "Akhtar@gmail.com", // Email field
                phoneNumber = "09876544567", // Phone number field
                joinDate = "23/09/2013", // Join date field
                fontFamily = PoppinsFontFamily(), // Custom font (optional)
                onEditClick = {
                    ConfirmationDialog.show(
                        title = "Edit User",
                        message = "Are you sure you want to edit this user?",
                        onYesClick = {
                            println()
                        },
                    )
                },
                onDeleteClick = {
                    ConfirmationDialog.show(
                        title = "Delete User",
                        message = "Are you sure you want to delete this user?",
                        onYesClick = {
                            println()
                        },
                    )
                },
                onStatusClick = {
                    ConfirmationDialog.show(
                        title = "Change User Status",
                        message = "Are you sure you want to change this user status?",
                        onYesClick = {
                            println()
                        },
                    )
                }
            )

            ItemUserComponent(
                isActivated = false, // true for active (green), false for inactive (gray)
                userName = "PM Yogyakarta", // Title in the green/gray background
                name = "Akhtar", // Name field
                password = "Akhtar123", // Password field
                email = "Akhtar@gmail.com", // Email field
                phoneNumber = "09876544567", // Phone number field
                joinDate = "23/09/2013", // Join date field
                fontFamily = PoppinsFontFamily(), // Custom font (optional)
                onEditClick = {
                    ConfirmationDialog.show(
                        title = "Edit User",
                        message = "Are you sure you want to edit this user?",
                        onYesClick = {
                            println()
                        },
                    )
                },
                onDeleteClick = {
                    ConfirmationDialog.show(
                        title = "Delete User",
                        message = "Are you sure you want to delete this user?",
                        onYesClick = {
                            println()
                        },
                    )
                },
                onStatusClick = {
                    ConfirmationDialog.show(
                        title = "Change User Status",
                        message = "Are you sure you want to change this user status?",
                        onYesClick = {
                            println()
                        },
                    )
                }
            )

        }
    }


}