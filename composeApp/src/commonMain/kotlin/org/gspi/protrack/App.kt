package org.gspi.protrack

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.gspi.protrack.common.navigation.Routes
import org.gspi.protrack.feature.feat_dashboard.presentation.screen.DashboardScreen
import org.gspi.protrack.feature.feat_login.presentation.screen.LoginScreen
import org.gspi.protrack.gspidesign.error.ErrorToast
import org.gspi.protrack.gspidesign.loading.LoadingDialog
import org.gspi.protrack.gspidesign.success.SuccessToast
import com.multiplatform.webview.util.KLogSeverity
import com.multiplatform.webview.web.WebView
import com.multiplatform.webview.web.rememberWebViewNavigator
import com.multiplatform.webview.web.rememberWebViewState
import com.techieroid.webviewapplication.getWebViewHandler
import org.gspi.protrack.feature.feat_detail_project.presentation.screen.DetailProjectScreen
import org.gspi.protrack.feature.feat_detail_project.sub.feat_document.presentation.screen.DocumentDetailScreen
import org.gspi.protrack.feature.feat_detail_project.sub.feat_log.presentation.screen.LogDetailScreen
import org.gspi.protrack.gspidesign.confirmation.ConfirmationDialog
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    val navController = rememberNavController()
    Box {
        NavHost(navController = navController, startDestination = Routes.Dashboard.route) {
            composable(route = Routes.Login.route) {
                LoginScreen(onLoginSuccess = {
                    navController.navigate(Routes.Dashboard.route){
                        popUpTo(Routes.Dashboard.route){
                            inclusive = true
                        }
                    }
                })
            }
            composable(route = Routes.Dashboard.route) {
                DashboardScreen(navController = navController)
            }
            composable(route = Routes.DetailProject.route) {
                DetailProjectScreen(navController = navController)
            }
            composable(route = Routes.DocumentDetail.route) {
                DocumentDetailScreen(navController = navController)
            }
            composable(route = Routes.LogDetail.route) {
                LogDetailScreen(navController = navController)
            }

        }
        LoadingDialog()
        ErrorToast()
        SuccessToast()
        ConfirmationDialog()
    }

}


