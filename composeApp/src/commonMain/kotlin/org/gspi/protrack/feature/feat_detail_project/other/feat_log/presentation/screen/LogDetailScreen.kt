package org.gspi.protrack.feature.feat_detail_project.other.feat_log.presentation.screen

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
import org.gspi.protrack.feature.feat_detail_project.presentation.eventstate.DetailProjectEvent
import org.gspi.protrack.feature.feat_detail_project.presentation.eventstate.DetailProjectState
import org.gspi.protrack.feature.feat_detail_project.presentation.viewmodel.DetailProjectViewModel
import org.gspi.protrack.feature.feat_detail_project.other.feat_log.presentation.component.ItemLogComponent
import org.gspi.protrack.feature.feat_detail_project.other.feat_log.presentation.component.NewProjectLogSearchComponent

@Composable
fun LogDetailScreen(
    uiState: DetailProjectState,
    viewModel: DetailProjectViewModel,
    modifier: Modifier = Modifier,
) {
    LaunchedEffect(Unit){
        viewModel.onEvent(DetailProjectEvent.LoadLogList)
    }
    Column(
        modifier = modifier
            .padding(top = 56.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        NewProjectLogSearchComponent(
            searchValue = "",
            onValueChange = {},
            onButtonClick = {}
        )
        uiState.listLog?.forEach {
            ItemLogComponent(
                projectName = it.activity,
                timeLeft = it.time,
                name = it.user,
                onClick = {
                }
            )
        }
    }

}