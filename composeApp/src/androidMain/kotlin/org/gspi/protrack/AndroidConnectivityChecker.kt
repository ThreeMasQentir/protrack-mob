package org.gspi.protrack

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import org.gspi.protrack.common.network.ConnectivityChecker

class AndroidConnectivityChecker(private val context: Context) : ConnectivityChecker {
    override fun isConnected(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}