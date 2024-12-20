package org.gspi.protrack.feature.feat_dashboard.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.gspi.protrack.common.navigation.Routes
import org.gspi.protrack.common.utils.calculateTimeLeft
import org.gspi.protrack.common.utils.isOverdue
import org.gspi.protrack.common.utils.setBackStackEntryData
import org.gspi.protrack.common.utils.toIndonesianDateFormat
import org.gspi.protrack.feature.feat_dashboard.presentation.component.ItemProjectComponent
import org.gspi.protrack.feature.feat_dashboard.presentation.component.NewProjectSearchComponent
import org.gspi.protrack.feature.feat_dashboard.presentation.eventstate.DashboardEvent
import org.gspi.protrack.feature.feat_dashboard.presentation.viewmodel.DashboardViewModel
import org.gspi.protrack.gspidesign.empty.EmptyView
import org.gspi.protrack.gspidesign.error.Error
import org.gspi.protrack.gspidesign.success.SuccessToast

@Composable
fun DashboardContent(modifier: Modifier = Modifier,
                     navController: NavController,
                     viewModel: DashboardViewModel
                     ) {
    val uiState by viewModel.uiState.collectAsState()
    LaunchedEffect(uiState.metaResponse) {
        uiState.metaResponse?.let {
            if (it.code == 200) {
                SuccessToast.show("Berhasil")
                viewModel.onEvent(DashboardEvent.LoadListProject)
            } else {
                Error.show("Failed: ${it.message}")
            }
        }
    }

    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        Spacer(modifier = Modifier.padding(8.dp))
        NewProjectSearchComponent(
            searchValue = uiState.searchValue,
            onValueChange = {
                viewModel.onEvent(DashboardEvent.OnSearchValueChange(it))
            },
            onButtonClick = {
                viewModel.onEvent(DashboardEvent.OnAddProjectClick(true))
            }
        )
        if (uiState.listProjectFiltered.isNotEmpty()) {
            EmptyView.hide()
            uiState.listProjectFiltered.forEach { project ->
                ItemProjectComponent(
                    projectName = project.projectName,
                    progress = project.progress,
                    timeline = "${project.startDate.toIndonesianDateFormat()} - ${project.deadlineDate.toIndonesianDateFormat()}",
                    timeLeft = calculateTimeLeft(project.deadlineDate),
                    isOverdue = isOverdue(project.deadlineDate),
                    onClick = {
                        navController.apply {
                            setBackStackEntryData(Routes.DetailProject().projectIdArg, project.id)
                            navigate(Routes.DetailProject().route)
                        }
                    }
                )
            }
        } else if (uiState.searchValue.isNotEmpty()) {
            EmptyView.show("No projects found")
        } else {
            EmptyView.show("No projects available")
        }
    }
}