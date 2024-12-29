package org.gspi.protrack.feature.feat_detail_project.other.feat_log.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.ktor.http.HttpHeaders.Date
import org.gspi.protrack.feature.feat_detail_project.presentation.eventstate.DetailProjectEvent
import org.gspi.protrack.feature.feat_detail_project.presentation.eventstate.DetailProjectState
import org.gspi.protrack.feature.feat_detail_project.presentation.viewmodel.DetailProjectViewModel
import org.gspi.protrack.feature.feat_detail_project.other.feat_log.presentation.component.ItemLogComponent
import org.gspi.protrack.feature.feat_detail_project.other.feat_log.presentation.component.NewProjectLogSearchComponent
import org.gspi.protrack.gspidesign.empty.EmptyView
import org.gspi.protrack.gspidesign.infodialog.InfoDialog

@Composable
fun LogDetailScreen(
    uiState: DetailProjectState,
    viewModel: DetailProjectViewModel,
    modifier: Modifier = Modifier,
) {
    LaunchedEffect(Unit) {
        viewModel.onEvent(DetailProjectEvent.LoadLogList)
    }
    Column(
        modifier = modifier
            .padding(top = 56.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        NewProjectLogSearchComponent(
            searchValue = uiState.searchLogValue,
            onValueChange = {
                viewModel.onEvent(DetailProjectEvent.OnSearchLog(it))
            },
            onButtonClick = {}
        )
        if (uiState.listLogFiltered?.isNotEmpty() == true) {
            EmptyView.hide()

            uiState.listLogFiltered.forEach {
                val description = buildString {
                    it.link?.projectName?.let { names ->
                        append("Name: ${names.getOrNull(0).orEmpty()} to ${names.getOrNull(1).orEmpty()}\n")
                    }
                    it.link?.startDate?.let { dates ->
                        append("Start Date: ${dates.getOrNull(0).orEmpty()} to ${dates.getOrNull(1).orEmpty()}\n")
                    }
                    it.link?.deadlineDate?.let { dates ->
                        append("End Date: ${dates.getOrNull(0).orEmpty()} to ${dates.getOrNull(1).orEmpty()}\n")
                    }
                    it.link?.gpsCurrent?.let { gps ->
                        append("GPS Current: ${gps.getOrNull(0)} to ${gps.getOrNull(1)}\n")
                    }
                    it.link?.gpsTotal?.let { gps ->
                        append("GPS Target: ${gps.getOrNull(0)} to ${gps.getOrNull(1)}\n")
                    }
                    it.link?.uavCurrent?.let { uav ->
                        append("UAV Current: ${uav.getOrNull(0)} to ${uav.getOrNull(1)}\n")
                    }
                    it.link?.uavTotal?.let { uav ->
                        append("UAV Target: ${uav.getOrNull(0)} to ${uav.getOrNull(1)}\n")
                    }
                    it.link?.processCurrent?.let { process ->
                        append("Pengolahan Current: ${process.getOrNull(0)} to ${process.getOrNull(1)}\n")
                    }
                    it.link?.processTotal?.let { process ->
                        append("Pengolahan Target: ${process.getOrNull(0)} to ${process.getOrNull(1)}\n")
                    }
                }.trim()
                ItemLogComponent(
                    projectName = it.activity,
                    timeLeft = it.time,
                    name = it.user,
                    onClick = {
                        InfoDialog.show(
                            title = it.activity,
                            description = description
                        )
                    }
                )
            }
        } else if (uiState.searchDocumentValue.isNotEmpty()) {
            EmptyView.show("No logs found")
        } else {
            EmptyView.show("No logs available")
        }
    }

}