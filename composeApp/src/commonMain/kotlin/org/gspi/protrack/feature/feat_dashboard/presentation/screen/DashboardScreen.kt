package org.gspi.protrack.feature.feat_dashboard.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.launch
import kotlinx.serialization.json.JsonNull.content
import org.gspi.protrack.common.media.LaunchFilePicker
import org.gspi.protrack.common.picker.AllowedFileType
import org.gspi.protrack.common.picker.FilePickResult
import org.gspi.protrack.feature.feat_dashboard.presentation.component.DrawerDashboard
import org.gspi.protrack.feature.feat_dashboard.presentation.component.ItemProjectComponent
import org.gspi.protrack.feature.feat_dashboard.presentation.component.NewProjectSearchComponent
import org.gspi.protrack.feature.feat_dashboard.presentation.component.ProTrackHeaderComponent
import org.gspi.protrack.feature.feat_dashboard.presentation.dialog.AddEditProjectComponent
import org.gspi.protrack.feature.feat_dashboard.presentation.eventstate.DashboardEvent
import org.gspi.protrack.feature.feat_dashboard.presentation.viewmodel.DashboardViewModel
import org.gspi.protrack.feature.feat_login.presentation.eventstate.LoginEvent
import org.gspi.protrack.gspidesign.error.Error
import org.gspi.protrack.gspidesign.loading.Loading
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier,
    viewModel: DashboardViewModel = koinViewModel()
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
//    AddEditProjectComponent(
//        isDialogVisible = true,
//        onDismissRequest = {
//
//        }
//    )
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerDashboard(
                onUserClick = {
                    scope.launch {
                        drawerState.close()
                    }
                },
                onLogOutClick = {
                    scope.launch {
                        drawerState.close()
                    }
                }
            )
        },
        content = {
            Column(modifier = modifier.verticalScroll(rememberScrollState())) {
                ProTrackHeaderComponent(
                    isDashboard = true,
                    onHamburgerClick = {
                        scope.launch {
                            drawerState.open()
                        }
                    }
                )
                Spacer(modifier = Modifier.padding(8.dp))
                NewProjectSearchComponent(
                    searchValue = uiState.searchValue,
                    onValueChange = {
                        viewModel.onEvent(DashboardEvent.OnSearchValueChange(it))
                    }
                )
                uiState.listProject.forEach { project ->
//                    val progressPercent = project.gpsCurrent / project.gpsTotal * 100
                    ItemProjectComponent(
                        projectName = project.projectName,
                        progress = 45,
                        timeline = "${project.startDate} - ${project.deadlineDate}",
                        timeLeft = "10 days left",
                    )
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
