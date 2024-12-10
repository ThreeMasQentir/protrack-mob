package org.gspi.protrack.feature.feat_dashboard.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gspiprotrack.composeapp.generated.resources.Res
import gspiprotrack.composeapp.generated.resources.ic_logout
import gspiprotrack.composeapp.generated.resources.ic_users
import kotlinx.coroutines.launch
import org.gspi.protrack.feature.feat_dashboard.presentation.component.DrawerDashboard
import org.gspi.protrack.feature.feat_dashboard.presentation.component.ItemProjectComponent
import org.gspi.protrack.feature.feat_dashboard.presentation.component.NewProjectSearchComponent
import org.gspi.protrack.feature.feat_dashboard.presentation.component.ProTrackHeaderComponent
import org.gspi.protrack.gspidesign.button.GspiButtonLeftIcon
import org.gspi.protrack.gspidesign.button.GspiButtonLeftIconFull
import org.gspi.protrack.gspidesign.font.PoppinsFontFamily

@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier
) {
    var searchValue by remember { mutableStateOf("") }
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()


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