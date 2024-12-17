package org.gspi.protrack.feature.feat_detail_project.sub.feat_log.presentation.screen

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
import androidx.navigation.NavController
import org.gspi.protrack.feature.feat_detail_project.presentation.component.HeaderProjectComponent
import org.gspi.protrack.feature.feat_detail_project.sub.feat_document.presentation.component.ItemDocumentComponent
import org.gspi.protrack.feature.feat_detail_project.sub.feat_document.presentation.component.NewProjectDocSearchComponent
import org.gspi.protrack.feature.feat_detail_project.sub.feat_document.presentation.component.UploadDocumentComponent
import org.gspi.protrack.feature.feat_detail_project.sub.feat_log.presentation.component.ItemLogComponent
import org.gspi.protrack.feature.feat_detail_project.sub.feat_log.presentation.component.NewProjectLogSearchComponent

@Composable
fun LogDetailScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {

    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        HeaderProjectComponent(
            title = "Proyek Foto Tegak BPN Kabupaten Banjar"
        )
        Spacer(modifier = Modifier.height(16.dp))
        NewProjectLogSearchComponent(
            searchValue = "",
            onValueChange = {},
            onButtonClick = {}
        )
        ItemLogComponent(
            projectName = "Document 1",
            timeLeft = "31 Jan 2025 ",
            onClick = {}
        )
    }

}