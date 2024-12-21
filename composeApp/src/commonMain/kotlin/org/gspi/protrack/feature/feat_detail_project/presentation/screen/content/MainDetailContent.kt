package org.gspi.protrack.feature.feat_detail_project.presentation.screen.content

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.techieroid.webviewapplication.WebViewHandler
import org.gspi.protrack.common.navigation.Routes
import org.gspi.protrack.common.utils.calculateTimeLeft
import org.gspi.protrack.common.utils.isOverdue
import org.gspi.protrack.common.utils.setBackStackEntryData
import org.gspi.protrack.common.utils.toIndonesianDateFormat
import org.gspi.protrack.feature.feat_detail_project.presentation.component.OtherComponent
import org.gspi.protrack.feature.feat_detail_project.presentation.component.ProgressComponent
import org.gspi.protrack.feature.feat_detail_project.presentation.component.ProgressTitleComponent
import org.gspi.protrack.feature.feat_detail_project.presentation.component.TimelineComponent
import org.gspi.protrack.feature.feat_detail_project.presentation.eventstate.DetailProjectEvent
import org.gspi.protrack.feature.feat_detail_project.presentation.eventstate.DetailProjectState
import org.gspi.protrack.feature.feat_detail_project.presentation.viewmodel.DetailProjectViewModel
import kotlin.text.ifEmpty

@Composable
fun MainDetailContent(
    modifier: Modifier,
    webViewHandler: WebViewHandler,
    viewModel: DetailProjectViewModel,
    uiState: DetailProjectState,
) {
    Column(
        modifier = modifier
            .padding(top = 56.dp) // Adjust padding to match header height
            .verticalScroll(rememberScrollState())
    ) {
        Box(modifier = Modifier.height(300.dp)) {
            webViewHandler.LoadUrl("https://gspi-protrack.my.id/api-dev/maps?id=${uiState.idProject}")
        }
        Spacer(modifier = Modifier.height(16.dp))
        ProgressTitleComponent(onButtonClick = {
            viewModel.onEvent(
                DetailProjectEvent.OnUpdateButtonClick(
                    isDialogVisible = true,
                    currentTitikKonrol = uiState.detailProject?.gpsCurrent ?: 0,
                    totalTitikControl = uiState.detailProject?.gpsTotal ?: 0,
                    totalFotoUdara = uiState.detailProject?.uavTotal ?: 0,
                    currentFotoUdara = uiState.detailProject?.uavCurrent ?: 0,
                    totalPengolahanData = uiState.detailProject?.processTotal ?: 0,
                    currentPengolahanData = uiState.detailProject?.processCurrent ?: 0
                )
            )
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
            startDate = uiState.detailProject?.startDate?.toIndonesianDateFormat()?.ifEmpty {
                "-"
            }.toString(),
            endDate = uiState.detailProject?.deadlineDate?.toIndonesianDateFormat()?.ifEmpty {
                "-"
            }.toString(),
            daysRemaining = uiState.detailProject?.deadlineDate?.takeIf { it.isNotEmpty() }?.let {
                calculateTimeLeft(it)
            }.orEmpty(),
            isOverdue = uiState.detailProject?.deadlineDate?.let { isOverdue(it) } ?: false
        )
        Spacer(modifier = Modifier.height(16.dp))
        OtherComponent(
            onDocumentClick = {
                viewModel.onEvent(DetailProjectEvent.OnChangeContentType(DetailProjectState.ContentType.DOCUMENT))
            },
            onLogClick = {
                viewModel.onEvent(DetailProjectEvent.OnChangeContentType(DetailProjectState.ContentType.LOG))
            }
        )
        Spacer(modifier = Modifier.height(32.dp))
    }
}