package org.gspi.protrack

import org.gspi.protrack.common.network.ConnectivityChecker
import platform.Network.nw_path_get_status
import platform.Network.nw_path_monitor_create
import platform.Network.nw_path_monitor_set_queue
import platform.Network.nw_path_monitor_set_update_handler
import platform.Network.nw_path_monitor_start
import platform.Network.nw_path_status_satisfied
import platform.darwin.dispatch_queue_create

class IOSConnectivityChecker : ConnectivityChecker {

    private val monitor = nw_path_monitor_create()
    private val queue = dispatch_queue_create("NetworkMonitorQueue", null)
    private val isConnectedRef = kotlin.concurrent.AtomicReference(false)

    init {
        nw_path_monitor_set_queue(monitor, queue)
        nw_path_monitor_set_update_handler(monitor) { path ->
            isConnectedRef.value = nw_path_get_status(path) == nw_path_status_satisfied
        }
        nw_path_monitor_start(monitor)
    }

    override fun isConnected(): Boolean {
        return isConnectedRef.value
    }
}