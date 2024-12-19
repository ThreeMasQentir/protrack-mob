package org.gspi.protrack.common.navigation

sealed class Routes(val route: String) {
    data object Login : Routes("login")
    data object Dashboard : Routes("dashboard")
    data class DetailProject(val projectIdArg: String = "projectId") : Routes("detailProject")
    data object LogDetail : Routes("logDetail")
    data object DocumentDetail : Routes("documentDetail")
}