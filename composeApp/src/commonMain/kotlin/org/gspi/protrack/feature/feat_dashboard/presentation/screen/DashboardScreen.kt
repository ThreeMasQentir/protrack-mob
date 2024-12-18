package org.gspi.protrack.feature.feat_dashboard.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import org.gspi.protrack.common.picker.FilePickResult
import org.gspi.protrack.feature.feat_dashboard.presentation.component.DrawerDashboard
import org.gspi.protrack.feature.feat_dashboard.presentation.component.ProTrackHeaderComponent
import org.gspi.protrack.feature.feat_dashboard.presentation.dialog.AddEditProjectComponent
import org.gspi.protrack.feature.feat_dashboard.presentation.eventstate.DashboardEvent
import org.gspi.protrack.feature.feat_dashboard.presentation.model.ContentType
import org.gspi.protrack.feature.feat_dashboard.presentation.viewmodel.DashboardViewModel
import org.gspi.protrack.gspidesign.confirmation.ConfirmationDialog
import org.gspi.protrack.gspidesign.error.Error
import org.gspi.protrack.gspidesign.loading.Loading
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier,
    viewModel: DashboardViewModel = koinViewModel(),
    navController: NavController
) {
    val uiState by viewModel.uiState.collectAsState()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    LaunchedEffect(Unit) {
        viewModel.onEvent(DashboardEvent.LoadListProject)
    }
    LaunchedEffect(uiState) {
        if (uiState.isLoading) Loading.show() else Loading.hide()
        uiState.errorMessage?.let { errorMessage ->
            Error.show(errorMessage)
            viewModel.onEvent(DashboardEvent.ClearError)
        }
    }
    AddEditProjectComponent(
        isDialogVisible = uiState.isDialogVisible,
        projectName = uiState.projectName,
        onProjectNameChange = {
            viewModel.onEvent(DashboardEvent.OnProjectNameChange(it))
        },
        startDate = uiState.projectstartDate,
        onStartDateChange = {
            viewModel.onEvent(DashboardEvent.OnStartDateChange(it))
        },
        endDate = uiState.projectEndDate,
        onEndDateChange = {
            viewModel.onEvent(DashboardEvent.OnEndDateChange(it))
        },
        onDialogDismiss = {
            viewModel.onEvent(DashboardEvent.OnAddProjectClick(false))
        },
        onAoiChange = { file ->
            when (file) {
                is FilePickResult.Success -> {
                    viewModel.onEvent(
                        DashboardEvent.OnAoiChange(
                            file.fileName,
                            file.data
                        )
                    )
                }
                else -> {}
            }
        },
        onKontrolChange = {
            when (it) {
                is FilePickResult.Success -> {
                    viewModel.onEvent(
                        DashboardEvent.OnRencanaTitikControlChange(
                            it.fileName,
                            it.data
                        )
                    )
                }

                else -> {}
            }
        },
        aoiFileName = uiState.projectAoiFileName,
        kontrolFileName = uiState.projectRencanaTitikControlFileName,
        onSaveProjectClick = {
            viewModel.onEvent(
                DashboardEvent.OnSaveProjectClick(
                    uiState.projectName,
                    uiState.projectstartDate,
                    uiState.projectEndDate,
                    uiState.projectAoiByteArray!!,
                    uiState.projectRencanaTitikControlByteArray!!
                )
            )
            viewModel.onEvent(DashboardEvent.ClearSaveProjectState)
        }
    )
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerDashboard(
                onUserClick = {
                    scope.launch {
                        drawerState.close()
                        viewModel.onEvent(DashboardEvent.OnContentTypeChange(ContentType.USERS))
                    }
                },
                onProjectClick = {
                    scope.launch {
                        drawerState.close()
                        viewModel.onEvent(DashboardEvent.OnContentTypeChange(ContentType.PROJECTS))
                    }
                },
                onLogOutClick = {
                    scope.launch {
                        ConfirmationDialog.show(
                            title = "Logout",
                            message = "Are you sure you want to logout?",
                            onYesClick = {
                                viewModel.onEvent(DashboardEvent.OnLogout)
                            },
                        )
                        drawerState.close()
                    }
                }
            )
        },
        content = {
            // Root Column with Modifier to fill screen
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                // Header Component - Fixed at the Top
                ProTrackHeaderComponent(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    isDashboard = true,
                    onHamburgerClick = {
                        scope.launch {
                            drawerState.open()
                        }
                    }
                )

                // Scrollable Content Below Header
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f) // Allows the Box to take up remaining space
                        .padding(top = 8.dp) // Adds spacing below the header
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        when (uiState.contentType) {
                            ContentType.PROJECTS -> DashboardContent(
                                navController = navController,
                                viewModel = viewModel
                            )

                            ContentType.USERS -> UsersContent(
                                viewModel = viewModel
                            )
                        }
                    }
                }
            }
        }
    )
}