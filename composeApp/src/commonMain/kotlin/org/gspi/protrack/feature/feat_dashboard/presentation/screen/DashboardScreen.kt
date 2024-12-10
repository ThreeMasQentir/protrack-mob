package org.gspi.protrack.feature.feat_dashboard.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.launch
import org.gspi.protrack.feature.feat_dashboard.presentation.component.DrawerDashboard
import org.gspi.protrack.feature.feat_dashboard.presentation.component.ItemProjectComponent
import org.gspi.protrack.feature.feat_dashboard.presentation.component.NewProjectSearchComponent
import org.gspi.protrack.feature.feat_dashboard.presentation.component.ProTrackHeaderComponent
import org.gspi.protrack.feature.feat_dashboard.presentation.viewmodel.DashboardViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier,
    viewModel: DashboardViewModel = koinViewModel()
) {
    var searchValue by remember { mutableStateOf("") }
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

   viewModel.getAllProjects()


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
                            drawerState.open() // Open the navigation drawer
                        }
                    }
                )
                Spacer(modifier = Modifier.padding(8.dp))
                NewProjectSearchComponent(
                    searchValue = searchValue,
                    onValueChange = {
                        searchValue = it
                    }
                )

                for (i in 0..5) {
                    ItemProjectComponent(
                        projectName = "Proyek Foto Tegak BPN  Kabupaten Demak",
                        progress = 0.7f,
                        timeline = "1 Dec 2024 - 31 Jan 2025",
                        timeLeft = "63 days left"
                    )
                }
            }
        }
    )
}