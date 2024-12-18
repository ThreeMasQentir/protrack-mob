package org.gspi.protrack.common.picker

sealed class FilePickResult {
    data class Success(val data: ByteArray?, val fileName: String) : FilePickResult()
    data class Error(val message: String) : FilePickResult()
    object Cancelled : FilePickResult()
}