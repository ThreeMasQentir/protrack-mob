package org.gspi.protrack

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.gspi.protrack.common.navigation.Routes
import org.gspi.protrack.feature.feat_dashboard.presentation.screen.DashboardScreen
import org.gspi.protrack.feature.feat_login.presentation.screen.LoginScreen
import org.gspi.protrack.gspidesign.error.ErrorToast
import org.gspi.protrack.gspidesign.loading.LoadingDialog
import org.gspi.protrack.gspidesign.success.SuccessToast
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
                DashboardScreen()
            }
        }
        LoadingDialog()
        ErrorToast()
        SuccessToast()
    }

}


