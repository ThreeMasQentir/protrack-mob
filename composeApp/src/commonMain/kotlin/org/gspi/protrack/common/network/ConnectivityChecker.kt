package org.gspi.protrack.common.network

interface ConnectivityChecker {
    fun isConnected(): Boolean
}