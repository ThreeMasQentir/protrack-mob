package org.gspi.protrack

import kotlinx.cinterop.ExperimentalForeignApi
import org.gspi.protrack.common.network.ConnectivityChecker

class IOSConnectivityChecker : ConnectivityChecker {

    override fun isConnected(): Boolean {
       return true
    }
}