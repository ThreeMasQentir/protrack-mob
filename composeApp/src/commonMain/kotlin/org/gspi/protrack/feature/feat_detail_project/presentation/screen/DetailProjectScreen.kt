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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.techieroid.webviewapplication.getWebViewHandler
import org.gspi.protrack.common.navigation.Routes
import org.gspi.protrack.common.picker.FilePickResult
import org.gspi.protrack.common.utils.getBackStackEntryData
import org.gspi.protrack.common.utils.toIndonesianDateFormat
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
import org.gspi.protrack.gspidesign.confirmation.ConfirmationDialog
import org.gspi.protrack.gspidesign.error.Error
import org.gspi.protrack.gspidesign.loading.Loading
import org.gspi.protrack.gspidesign.success.Success
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun DetailProjectScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: DetailProjectViewModel = koinViewModel()
) {
    val webViewHandler = getWebViewHandler()
    val uiState by viewModel.uiState.collectAsState()
    val id by mutableStateOf(navController.getBackStackEntryData<Int>(Routes.DetailProject().projectIdArg, true))
    LaunchedEffect(Unit) {
        id?.let{
            viewModel.onEvent(DetailProjectEvent.OnGetDetailProject(it.toString()))
        }
    }
    LaunchedEffect(uiState) {
        if (uiState.isLoading) Loading.show() else Loading.hide()
        uiState.errorMessage?.let { errorMessage ->
            Error.show(errorMessage)
            println("Error Message Detail: $errorMessage")
            viewModel.onEvent(DetailProjectEvent.ClearError)
        }
    }
    LaunchedEffect(uiState.metaResponse) {
        uiState.metaResponse?.let {
            if (it.code == 200) {
                Success.show("Failed")
                id?.let{
                    viewModel.onEvent(DetailProjectEvent.OnGetDetailProject(it.toString()))
                }
            } else {
                Error.show("Failed: ${it.message}")
            }
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        UpdateProgressComponent(
            isDialogVisible = uiState.updateProgressState.isDialogUpdateVisible,
            currentTitikControl = uiState.updateProgressState.currentTitikKonrol.toString(),
            onCurrentTitikControlChange = {
                if (it.isNotEmpty()){
                    viewModel.onEvent(DetailProjectEvent.OnCurrentTitikControlChange(it.toInt()))
                }
            },
            totalTitikControl = uiState.updateProgressState.totalTitikKontrol.toString(),
            onTotalTitikControlChange = {
                if (it.isNotEmpty()){
                    viewModel.onEvent(DetailProjectEvent.OnTotalTitikControlChange(it.toInt()))
                }
            },
            currentFotoUdara = uiState.updateProgressState.currentFotoUdara.toString(),
            onCurrentFotoUdaraChange = {
                if (it.isNotEmpty()){
                    viewModel.onEvent(DetailProjectEvent.OnCurrentFotoUdaraChange(it.toInt()))
                }
            },
            totalFotoUdara = uiState.updateProgressState.totalFotoUdara.toString(),
            onTotalFotoUdaraChange = {
                if (it.isNotEmpty()){
                    viewModel.onEvent(DetailProjectEvent.OnTotalFotoUdaraChange(it.toInt()))
                }
            },
            currentPengolahanData = uiState.updateProgressState.currentPengolahanData.toString(),
            onCurrentPengolahanDataChange = {
                if (it.isNotEmpty()){
                    viewModel.onEvent(DetailProjectEvent.OnCurrentPengolahanDataChange(it.toInt()))
                }
            },
            totalPengolahanData = uiState.updateProgressState.totalPengolahanData.toString(),
            onTotalPengolahanDataChange = {
                if (it.isNotEmpty()){
                    viewModel.onEvent(DetailProjectEvent.OnTotalPengolahanDataChange(it.toInt()))
                }
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
            startDate = uiState.settingProjectState.startDate.toIndonesianDateFormat(),
            onStartDateChange = {
                viewModel.onEvent(DetailProjectEvent.OnStartDateChange(it))
            },
            endDate = uiState.settingProjectState.endDate.toIndonesianDateFormat(),
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
                ConfirmationDialog.show(
                    title = "Delete Project",
                    message = "Are you sure want to delete this project?",
                    onYesClick = {
                        viewModel.onEvent(DetailProjectEvent.OnDeleteProject)
                        navController.popBackStack()
                    }
                )
            }


        )
        Column {
            HeaderProjectComponent(
                title = uiState.detailProject?.projectName.orEmpty(),
                onSettingsClick = {
                    viewModel.onEvent(
                        DetailProjectEvent.OnSettingProjectClick(
                            true,
                            projectName =  uiState.detailProject?.projectName.orEmpty(),
                            startDate = uiState.detailProject?.startDate?.toIndonesianDateFormat().orEmpty(),
                            endDate = uiState.detailProject?.deadlineDate?.toIndonesianDateFormat().orEmpty(),
                            aoiFileName = uiState.detailProject?.aoiFileName.orEmpty(),
                            rencanaTitikControlFileName = uiState.detailProject?.titikKontrolFileName.orEmpty()
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
                viewModel.onEvent(DetailProjectEvent.OnUpdateButtonClick(
                    isDialogVisible = true,
                    currentTitikKonrol = uiState.detailProject?.gpsCurrent ?: 0,
                    totalTitikControl = uiState.detailProject?.gpsTotal ?: 0,
                    totalFotoUdara = uiState.detailProject?.uavTotal ?: 0,
                    currentFotoUdara = uiState.detailProject?.uavCurrent ?: 0,
                    totalPengolahanData = uiState.detailProject?.processTotal ?: 0,
                    currentPengolahanData = uiState.detailProject?.processCurrent ?: 0
                    ))
            })
            ProgressComponent(
                currentTitikControl = uiState.detailProject?.gpsCurrent ?: 0,
                totalTitikControl = uiState.detailProject?.gpsTotal ?: 0,
                currentFotoUdara = uiState.detailProject?.uavCurrent ?: 0,
                totalFotoUdara = uiState.detailProject?.uavTotal ?: 0,
                currentPengolahanData = uiState.detailProject?.processCurrent ?: 0,
                totalPengolahanData = uiState.detailProject?.processTotal ?: 0
            )
            TimelineComponent(
                startDate = uiState.detailProject?.startDate?.toIndonesianDateFormat()?.ifEmpty{
                    "-"
                }.toString(),
                endDate = uiState.detailProject?.deadlineDate?.toIndonesianDateFormat()?.ifEmpty{
                    "-"
                }.toString(),
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