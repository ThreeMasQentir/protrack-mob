package org.gspi.protrack.gspidesign.success

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

object Success {
    private val _successMessage = mutableStateOf<String?>(null)
    val successMessage: State<String?> get() = _successMessage
    private var hideJob: Job? = null

    fun show(message: String, durationMillis: Long = 3000L) {
        _successMessage.value = message
        hideJob?.cancel()
        hideJob = CoroutineScope(Dispatchers.Main).launch {
            delay(durationMillis)
            hide()
        }
    }

    private fun hide() {
        _successMessage.value = null
    }
}