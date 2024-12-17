package org.gspi.protrack.feature.feat_dashboard.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.launch
import kotlinx.serialization.json.JsonNull.content
import org.gspi.protrack.common.media.LaunchFilePicker
import org.gspi.protrack.common.navigation.Routes
import org.gspi.protrack.common.picker.AllowedFileType
import org.gspi.protrack.common.picker.FilePickResult
import org.gspi.protrack.feature.feat_dashboard.presentation.component.DrawerDashboard
import org.gspi.protrack.feature.feat_dashboard.presentation.component.ItemProjectComponent
import org.gspi.protrack.feature.feat_dashboard.presentation.component.NewProjectSearchComponent
import org.gspi.protrack.feature.feat_dashboard.presentation.component.ProTrackHeaderComponent
import org.gspi.protrack.feature.feat_dashboard.presentation.dialog.AddEditProjectComponent
import org.gspi.protrack.feature.feat_dashboard.presentation.eventstate.DashboardEvent
import org.gspi.protrack.feature.feat_dashboard.presentation.model.ContentType
import org.gspi.protrack.feature.feat_dashboard.presentation.viewmodel.DashboardViewModel
import org.gspi.protrack.feature.feat_login.presentation.eventstate.LoginEvent
import org.gspi.protrack.gspidesign.confirmation.Confirmation
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
    LaunchedEffect(Unit){
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
        onDialogVisibleChange = {
            viewModel.onEvent(DashboardEvent.OnAddProjectClick(it))
        },
        projectName = "",
        onProjectNameChange = {},
        startDate = "",
        onStartDateChange = {},
        endDate = "",
        onEndDateChange = {},
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
                        Confirmation.show(
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
                                navController = navController,
                                viewModel = viewModel
                            )
                        }
                    }
                }
            }
        }
    )
}
/*
this is example for using
LaunchFilePicker(
allowedType = AllowedFileType.ANY,
onResult = {
    when(it){
        FilePickResult.Cancelled -> {
            println("FilePickResult.Cancelled")
        }
        is FilePickResult.Success -> {
            println("FilePickResult.Success ${it.data?.size}")
        }
        is FilePickResult.Error -> {
            println("FilePickResult.Error ${it.message}")
        }
    }
},
content = {
    Button(onClick = it) {
        Text("Pick File")
    }
}
)*/
