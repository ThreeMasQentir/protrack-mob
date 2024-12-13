package org.gspi.protrack.common.picker

sealed class FilePickResult {
    data class Success(val data: ByteArray?) : FilePickResult()
    data class Error(val message: String) : FilePickResult()
    object Cancelled : FilePickResult()
}