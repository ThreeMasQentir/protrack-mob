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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.techieroid.webviewapplication.getWebViewHandler
import org.gspi.protrack.common.navigation.Routes
import org.gspi.protrack.feature.feat_dashboard.presentation.dialog.AddEditProjectComponent
import org.gspi.protrack.feature.feat_dashboard.presentation.eventstate.DashboardEvent
import org.gspi.protrack.feature.feat_detail_project.presentation.component.FormComponent
import org.gspi.protrack.feature.feat_detail_project.presentation.component.HeaderProjectComponent
import org.gspi.protrack.feature.feat_detail_project.presentation.component.OtherComponent
import org.gspi.protrack.feature.feat_detail_project.presentation.component.ProgressComponent
import org.gspi.protrack.feature.feat_detail_project.presentation.component.ProgressTitleComponent
import org.gspi.protrack.feature.feat_detail_project.presentation.component.TimelineComponent
import org.gspi.protrack.feature.feat_detail_project.presentation.dialog.SettingProjectComponent
import org.gspi.protrack.feature.feat_detail_project.presentation.dialog.UpdateProgressComponent

@Composable
fun DetailProjectScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val webViewHandler = getWebViewHandler()
    var isDialogVisible by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        UpdateProgressComponent(
            isDialogVisible = isDialogVisible,
            onDialogVisibleChange = {
                isDialogVisible = it
            },
        )
        SettingProjectComponent(
            isDialogVisible = false,
            onDialogVisibleChange = {
                isDialogVisible = it
            },
            projectName = "",
            onProjectNameChange = {},
            startDate = "",
            onStartDateChange = {},
            endDate = "",
            onEndDateChange = {},
        )
        Column {
            // Fixed Header Component
            HeaderProjectComponent(
                title = "Proyek Foto Tegak BPN Kabupaten Banjar",
                onSettingsClick = {
                    isDialogVisible = true
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
                isDialogVisible = true
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