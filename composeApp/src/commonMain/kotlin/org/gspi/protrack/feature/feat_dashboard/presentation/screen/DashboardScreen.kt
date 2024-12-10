package org.gspi.protrack.feature.feat_dashboard.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.gspi.protrack.feature.feat_dashboard.presentation.component.ItemProjectComponent
import org.gspi.protrack.feature.feat_dashboard.presentation.component.NewProjectSearchComponent
import org.gspi.protrack.feature.feat_dashboard.presentation.component.ProTrackHeaderComponent

@Composable
fun DashboardScreen(modifier: Modifier = Modifier) {
    var searchValue by remember { mutableStateOf("") }
    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        ProTrackHeaderComponent(isDashboard = true,
            onHamburgerClick = {
        })
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