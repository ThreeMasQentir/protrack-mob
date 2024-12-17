package org.gspi.protrack.feature.feat_detail_project.sub.feat_document.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import org.gspi.protrack.feature.feat_dashboard.presentation.dialog.AddEditProjectComponent
import org.gspi.protrack.feature.feat_dashboard.presentation.eventstate.DashboardEvent
import org.gspi.protrack.feature.feat_detail_project.presentation.component.HeaderProjectComponent
import org.gspi.protrack.feature.feat_detail_project.sub.feat_document.presentation.component.ItemDocumentComponent
import org.gspi.protrack.feature.feat_detail_project.sub.feat_document.presentation.component.NewProjectDocSearchComponent
import org.gspi.protrack.feature.feat_detail_project.sub.feat_document.presentation.component.UploadDocumentComponent

@Composable
fun DocumentDetailScreen(
    navController: NavController,
    modifier: Modifier = Modifier) {
    Box{
        UploadDocumentComponent(
            isDialogVisible = true,
            onDialogVisibleChange = {
            },
            projectName = "",
            onProjectNameChange = {},
        )
        Column(modifier = modifier.verticalScroll(rememberScrollState())) {
            HeaderProjectComponent(
                title = "Proyek Foto Tegak BPN Kabupaten Banjar"
            )
            Spacer(modifier = Modifier.height(16.dp))
            NewProjectDocSearchComponent(
                searchValue = "",
                onValueChange = {},
                onButtonClick = {}

            )
            ItemDocumentComponent(
                projectName = "Document 1",
                timeLeft = "31 Jan 2025 ",
                onClick = {}
            )
        }
    }

}