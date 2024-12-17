package org.gspi.protrack.feature.feat_detail_project.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.techieroid.webviewapplication.getWebViewHandler
import org.gspi.protrack.common.navigation.Routes
import org.gspi.protrack.feature.feat_detail_project.presentation.component.FormComponent
import org.gspi.protrack.feature.feat_detail_project.presentation.component.HeaderProjectComponent
import org.gspi.protrack.feature.feat_detail_project.presentation.component.OtherComponent
import org.gspi.protrack.feature.feat_detail_project.presentation.component.ProgressComponent
import org.gspi.protrack.feature.feat_detail_project.presentation.component.ProgressTitleComponent
import org.gspi.protrack.feature.feat_detail_project.presentation.component.TimelineComponent

@Composable
fun DetailProjectScreen(
    navController: NavController,
    modifier: Modifier = Modifier) {
    val webViewHandler = getWebViewHandler()
    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        HeaderProjectComponent(
            title = "Proyek Foto Tegak BPN Kabupaten Banjar"
        )
        Box(modifier = Modifier.height(300.dp)) {
            webViewHandler.LoadUrl("https://gspi-protrack.my.id/api/maps?id=7")
        }
        Spacer(modifier = Modifier.height(16.dp))
        ProgressTitleComponent(onButtonClick = {})
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
        Spacer(modifier= Modifier.height(16.dp))
        OtherComponent(
            onDocumentClick = {
                navController.navigate(Routes.DocumentDetail.route)
            },
            onLogClick = {
                navController.navigate(Routes.LogDetail.route)
            }
        )
        Spacer(modifier= Modifier.height(32.dp))
    }
}