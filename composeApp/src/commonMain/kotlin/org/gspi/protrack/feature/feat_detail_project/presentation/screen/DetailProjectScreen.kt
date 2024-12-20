package org.gspi.protrack.feature.feat_detail_project.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.techieroid.webviewapplication.getWebViewHandler
import org.gspi.protrack.common.navigation.Routes
import org.gspi.protrack.common.utils.getBackStackEntryData
import org.gspi.protrack.feature.feat_detail_project.presentation.eventstate.DetailProjectEvent
import org.gspi.protrack.feature.feat_detail_project.presentation.eventstate.DetailProjectState
import org.gspi.protrack.feature.feat_detail_project.presentation.screen.content.HeaderContent
import org.gspi.protrack.feature.feat_detail_project.presentation.screen.content.MainDetailContent
import org.gspi.protrack.feature.feat_detail_project.presentation.screen.content.SettingsDialogContent
import org.gspi.protrack.feature.feat_detail_project.presentation.screen.content.UpdateProgressDialogContent
import org.gspi.protrack.feature.feat_detail_project.presentation.viewmodel.DetailProjectViewModel
import org.gspi.protrack.feature.feat_detail_project.other.feat_document.presentation.screen.DocumentDetailScreen
import org.gspi.protrack.feature.feat_detail_project.other.feat_log.presentation.screen.LogDetailScreen
import org.gspi.protrack.gspidesign.error.Error
import org.gspi.protrack.gspidesign.loading.Loading
import org.gspi.protrack.gspidesign.success.SuccessToast
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
                SuccessToast.show("Sucess")
                id?.let{
                    viewModel.onEvent(DetailProjectEvent.OnGetDetailProject(it.toString()))
                }
            } else {
                Error.show("Failed: ${it.message}")
            }
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        HeaderContent(uiState, viewModel, navController)
        UpdateProgressDialogContent(uiState, viewModel)
        SettingsDialogContent(uiState, viewModel, navController)

        when (uiState.contentType) {
            DetailProjectState.ContentType.MAINDETAIL -> MainDetailContent(modifier, webViewHandler, viewModel, uiState, navController, id)
            DetailProjectState.ContentType.LOG -> LogDetailScreen(uiState,viewModel)
            DetailProjectState.ContentType.DOCUMENT -> DocumentDetailScreen(uiState,viewModel)
        }
    }
}




