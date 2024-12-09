package org.gspi.protrack.gspidesign.error

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

object Error {
    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage: State<String?> get() = _errorMessage
    private var hideJob: Job? = null

    fun show(message: String, durationMillis: Long = 3000L) {
        _errorMessage.value = message
        hideJob?.cancel()
        hideJob = CoroutineScope(Dispatchers.Main).launch {
            delay(durationMillis)
            hide()
        }
    }

    private fun hide() {
        _errorMessage.value = null
    }
}