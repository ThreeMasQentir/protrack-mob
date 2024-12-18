package org.gspi.protrack.feature.feat_detail_project.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.techieroid.webviewapplication.getWebViewHandler
import org.gspi.protrack.common.navigation.Routes
import org.gspi.protrack.common.picker.FilePickResult
import org.gspi.protrack.feature.feat_detail_project.presentation.component.FormComponent
import org.gspi.protrack.feature.feat_detail_project.presentation.component.HeaderProjectComponent
import org.gspi.protrack.feature.feat_detail_project.presentation.component.OtherComponent
import org.gspi.protrack.feature.feat_detail_project.presentation.component.ProgressComponent
import org.gspi.protrack.feature.feat_detail_project.presentation.component.ProgressTitleComponent
import org.gspi.protrack.feature.feat_detail_project.presentation.component.TimelineComponent
import org.gspi.protrack.feature.feat_detail_project.presentation.dialog.SettingProjectComponent
import org.gspi.protrack.feature.feat_detail_project.presentation.dialog.UpdateProgressComponent
import org.gspi.protrack.feature.feat_detail_project.presentation.eventstate.DetailProjectEvent
import org.gspi.protrack.feature.feat_detail_project.presentation.viewmodel.DetailProjectViewModel

@Composable
fun DetailProjectScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: DetailProjectViewModel = remember { DetailProjectViewModel() }
) {
    val webViewHandler = getWebViewHandler()
    val uiState by viewModel.uiState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        UpdateProgressComponent(
            isDialogVisible = uiState.updateProgressState.isDialogUpdateVisible,
            currentTitikControl = uiState.updateProgressState.currentTitikKonrol,
            onCurrentTitikControlChange = {
                viewModel.onEvent(DetailProjectEvent.OnCurrentTitikControlChange(it))
            },
            totalTitikControl = uiState.updateProgressState.totalTitikKontrol,
            onTotalTitikControlChange = {
                viewModel.onEvent(DetailProjectEvent.OnTotalTitikControlChange(it))
            },
            currentFotoUdara = uiState.updateProgressState.currentFotoUdara,
            onCurrentFotoUdaraChange = {
                viewModel.onEvent(DetailProjectEvent.OnCurrentFotoUdaraChange(it))
            },
            totalFotoUdara = uiState.updateProgressState.totalFotoUdara,
            onTotalFotoUdaraChange = {
                viewModel.onEvent(DetailProjectEvent.OnTotalFotoUdaraChange(it))
            },
            currentPengolahanData = uiState.updateProgressState.currentPengolahanData,
            onCurrentPengolahanDataChange = {
                viewModel.onEvent(DetailProjectEvent.OnCurrentPengolahanDataChange(it))
            },
            totalPengolahanData = uiState.updateProgressState.totalPengolahanData,
            onTotalPengolahanDataChange = {
                viewModel.onEvent(DetailProjectEvent.OnTotalPengolahanDataChange(it))
            },
            onCancelButtonClicked = {
                viewModel.onEvent(DetailProjectEvent.OnCancelUpdateProgress)
            },
            onSaveButtonClicked = {
                viewModel.onEvent(DetailProjectEvent.OnSaveUpdateProgress)
            }

        )
        SettingProjectComponent(
            isDialogVisible = uiState.settingProjectState.isDialogSettingVisible,
            projectName = uiState.settingProjectState.projectName,
            onProjectNameChange = {
                viewModel.onEvent(DetailProjectEvent.OnProjectNameChange(it))
            },
            startDate = uiState.settingProjectState.startDate,
            onStartDateChange = {
                viewModel.onEvent(DetailProjectEvent.OnStartDateChange(it))
            },
            endDate = uiState.settingProjectState.endDate,
            onEndDateChange = {
                viewModel.onEvent(DetailProjectEvent.OnEndDateChange(it))
            },
            aoiFileName = uiState.settingProjectState.aoiFileName,
            onAoiFileChange = { file ->
                when (file) {
                    is FilePickResult.Success -> {
                        viewModel.onEvent(
                            DetailProjectEvent.OnAoiFileChange(
                                file.fileName,
                                file.data,
                            )
                        )
                    }

                    else -> {}
                }
            },
            rencanaTitikControlFileName = uiState.settingProjectState.rencanaTitikControlFileName,
            onRencanaTitikControlFileChange = { file ->
                when (file) {
                    is FilePickResult.Success -> {
                        viewModel.onEvent(
                            DetailProjectEvent.OnRencanaTitikControlFileChange(
                                file.fileName,
                                file.data
                            )
                        )
                    }

                    else -> {}
                }
            },
            onCancelButtonClicked = {
                viewModel.onEvent(DetailProjectEvent.OnCancelSettingProject)
            },
            onSaveButtonClicked = {
                viewModel.onEvent(DetailProjectEvent.OnSaveSettingProject)
            },
            onDeleteProject = {
                viewModel.onEvent(DetailProjectEvent.OnDeleteProject(it))
            }


        )
        Column {
            // Fixed Header Component
            HeaderProjectComponent(
                title = "Proyek Foto Tegak BPN Kabupaten Banjar",
                onSettingsClick = {
                    viewModel.onEvent(
                        DetailProjectEvent.OnSettingProjectClick(
                            true,
                            "Proyek Foto Tegak BPN  Kabupaten Demak",
                            "10/12/2023",
                            "10/12/2024",
                            "Dokumen AOI.pdf",
                            "Titik Kontrol.pdf"
                        )
                    )
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
            Divider(color = Color.LightGray, thickness = 1.dp)
        }

        // Scrollable Content
        Column(
            modifier = modifier
                .padding(top = 56.dp) // Adjust padding to match header height
                .verticalScroll(rememberScrollState())
        ) {
            Box(modifier = Modifier.height(300.dp)) {
                webViewHandler.LoadUrl("https://gspi-protrack.my.id/api/maps?id=7")
            }
            Spacer(modifier = Modifier.height(16.dp))
            ProgressTitleComponent(onButtonClick = {
                viewModel.onEvent(DetailProjectEvent.OnUpdateButtonClick(true, "68", "12", "45"))
            })
            ProgressComponent()
            TimelineComponent(
                startDate = "12/12/2021",
                endDate = "12/12/2022",
                daysRemaining = "120"
            )
            FormComponent(
                onGpsClick = {},
                onUavClick = {}
            )
            Spacer(modifier = Modifier.height(16.dp))
            OtherComponent(
                onDocumentClick = {
                    navController.navigate(Routes.DocumentDetail.route)
                },
                onLogClick = {
                    navController.navigate(Routes.LogDetail.route)
                }
            )
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}