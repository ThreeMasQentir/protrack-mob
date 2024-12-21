package org.gspi.protrack.feature.feat_detail_project.presentation.screen.content

import androidx.compose.runtime.Composable
import org.gspi.protrack.feature.feat_detail_project.presentation.dialog.UpdateProgressComponent
import org.gspi.protrack.feature.feat_detail_project.presentation.eventstate.DetailProjectEvent
import org.gspi.protrack.feature.feat_detail_project.presentation.eventstate.DetailProjectState
import org.gspi.protrack.feature.feat_detail_project.presentation.viewmodel.DetailProjectViewModel

@Composable
fun UpdateProgressDialogContent(
    uiState: DetailProjectState,
    viewModel: DetailProjectViewModel
) {
    UpdateProgressComponent(
        isDialogVisible = uiState.updateProgressState.isDialogUpdateVisible,
        currentTitikControl = uiState.updateProgressState.currentTitikKonrol.toString(),
        onCurrentTitikControlChange = {
            if (it.isNotEmpty()) {
                viewModel.onEvent(DetailProjectEvent.OnCurrentTitikControlChange(it.toInt()))
            }
        },
        totalTitikControl = uiState.updateProgressState.totalTitikKontrol.toString(),
        onTotalTitikControlChange = {
            if (it.isNotEmpty()) {
                viewModel.onEvent(DetailProjectEvent.OnTotalTitikControlChange(it.toInt()))
            }
        },
        currentFotoUdara = uiState.updateProgressState.currentFotoUdara.toString(),
        onCurrentFotoUdaraChange = {
            if (it.isNotEmpty()) {
                viewModel.onEvent(DetailProjectEvent.OnCurrentFotoUdaraChange(it.toInt()))
            }
        },
        totalFotoUdara = uiState.updateProgressState.totalFotoUdara.toString(),
        onTotalFotoUdaraChange = {
            if (it.isNotEmpty()) {
                viewModel.onEvent(DetailProjectEvent.OnTotalFotoUdaraChange(it.toInt()))
            }
        },
        currentPengolahanData = uiState.updateProgressState.currentPengolahanData.toString(),
        onCurrentPengolahanDataChange = {
            if (it.isNotEmpty()) {
                viewModel.onEvent(DetailProjectEvent.OnCurrentPengolahanDataChange(it.toInt()))
            }
        },
        totalPengolahanData = uiState.updateProgressState.totalPengolahanData.toString(),
        onTotalPengolahanDataChange = {
            if (it.isNotEmpty()) {
                viewModel.onEvent(DetailProjectEvent.OnTotalPengolahanDataChange(it.toInt()))
            }
        },
        onCancelButtonClicked = {
            viewModel.onEvent(DetailProjectEvent.OnCancelUpdateProgress)
        },
        onSaveButtonClicked = {
            viewModel.onEvent(DetailProjectEvent.OnSaveUpdateProgress)
        },
        isAdmin = viewModel.isAdministrator(),
        enabled = uiState.updateProgressState.currentFotoUdara <= uiState.updateProgressState.totalFotoUdara &&
                uiState.updateProgressState.currentPengolahanData <= uiState.updateProgressState.totalPengolahanData &&
                uiState.updateProgressState.currentTitikKonrol <= uiState.updateProgressState.totalTitikKontrol

    )
}