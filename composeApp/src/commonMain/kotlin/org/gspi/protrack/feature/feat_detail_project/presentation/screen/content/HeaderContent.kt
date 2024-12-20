package org.gspi.protrack.feature.feat_detail_project.presentation.screen.content

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.gspi.protrack.common.utils.toIndonesianDateFormat
import org.gspi.protrack.feature.feat_detail_project.presentation.component.HeaderProjectComponent
import org.gspi.protrack.feature.feat_detail_project.presentation.eventstate.DetailProjectEvent
import org.gspi.protrack.feature.feat_detail_project.presentation.eventstate.DetailProjectState
import org.gspi.protrack.feature.feat_detail_project.presentation.viewmodel.DetailProjectViewModel

@Composable
fun HeaderContent(
    uiState: DetailProjectState,
    viewModel: DetailProjectViewModel,
    navController: NavController
) {
    Column {
        HeaderProjectComponent(
            title = uiState.detailProject?.projectName.orEmpty(),
            onSettingsClick = {
                viewModel.onEvent(
                    DetailProjectEvent.OnSettingProjectClick(
                        true,
                        projectName = uiState.detailProject?.projectName.orEmpty(),
                        startDate = uiState.detailProject?.startDate?.toIndonesianDateFormat()
                            .orEmpty(),
                        endDate = uiState.detailProject?.deadlineDate?.toIndonesianDateFormat()
                            .orEmpty(),
                        aoiFileName = uiState.detailProject?.aoiFileName.orEmpty(),
                        rencanaTitikControlFileName = uiState.detailProject?.titikKontrolFileName.orEmpty()
                    )
                )
            },
            onBackClick = {
                when (uiState.contentType) {
                    DetailProjectState.ContentType.MAINDETAIL -> navController.popBackStack()
                    DetailProjectState.ContentType.LOG -> viewModel.onEvent(DetailProjectEvent.OnChangeContentType(DetailProjectState.ContentType.MAINDETAIL))
                    DetailProjectState.ContentType.DOCUMENT -> viewModel.onEvent(DetailProjectEvent.OnChangeContentType(DetailProjectState.ContentType.MAINDETAIL))
                }
            },
            isSettingsVisible = uiState.contentType == DetailProjectState.ContentType.MAINDETAIL
        )
        Divider(color = Color.LightGray, thickness = 1.dp)
    }
}