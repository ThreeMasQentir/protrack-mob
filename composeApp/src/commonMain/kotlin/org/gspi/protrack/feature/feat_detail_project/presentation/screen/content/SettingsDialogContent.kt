package org.gspi.protrack.feature.feat_detail_project.presentation.screen.content

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import org.gspi.protrack.common.picker.FilePickResult
import org.gspi.protrack.common.utils.toIndonesianDateFormat
import org.gspi.protrack.feature.feat_detail_project.presentation.dialog.SettingProjectComponent
import org.gspi.protrack.feature.feat_detail_project.presentation.eventstate.DetailProjectEvent
import org.gspi.protrack.feature.feat_detail_project.presentation.eventstate.DetailProjectState
import org.gspi.protrack.feature.feat_detail_project.presentation.viewmodel.DetailProjectViewModel
import org.gspi.protrack.gspidesign.confirmation.ConfirmationDialog

@Composable
fun SettingsDialogContent(
    uiState: DetailProjectState,
    viewModel: DetailProjectViewModel,
    navController: NavController
) {
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
        },
        onDeleteAoiButtonClicked = {
            ConfirmationDialog.show(
                title = "Delete AOI",
                message = "Are you sure want to delete this AOI?",
                onYesClick = {
                    viewModel.onEvent(DetailProjectEvent.OnDeleteAoiFileName)
                }
            )
        },
        onDeleteRencanaTitikControlButtonClicked = {
            ConfirmationDialog.show(
                title = "Delete Rencana Titik Control",
                message = "Are you sure want to delete this Rencana Titik Control?",
                onYesClick = {
                    viewModel.onEvent(DetailProjectEvent.OnDeleteRencanaTitikControlFileName)
                })
        },


    )
}