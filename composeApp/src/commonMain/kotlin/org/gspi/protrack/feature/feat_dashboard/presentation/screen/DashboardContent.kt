package org.gspi.protrack.feature.feat_dashboard.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.gspi.protrack.common.navigation.Routes
import org.gspi.protrack.feature.feat_dashboard.presentation.component.ItemProjectComponent
import org.gspi.protrack.feature.feat_dashboard.presentation.component.NewProjectSearchComponent
import org.gspi.protrack.feature.feat_dashboard.presentation.eventstate.DashboardEvent
import org.gspi.protrack.feature.feat_dashboard.presentation.viewmodel.DashboardViewModel

@Composable
fun DashboardContent(modifier: Modifier = Modifier,
                     navController: NavController,
                     viewModel: DashboardViewModel
                     ) {
    val uiState by viewModel.uiState.collectAsState()

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
        uiState.listProject.forEach { project ->
//                    val progressPercent = project.gpsCurrent / project.gpsTotal * 100
            ItemProjectComponent(
                projectName = project.projectName,
                progress = 45,
                timeline = "${project.startDate} - ${project.deadlineDate}",
                timeLeft = "10 days left",
                onClick = {
                    navController.navigate(Routes.DetailProject.route)
                }
            )
        }
    }
}