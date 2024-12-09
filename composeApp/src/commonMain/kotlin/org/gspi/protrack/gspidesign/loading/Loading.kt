package org.gspi.protrack.gspidesign.loading

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

object Loading {
    private val _isVisible = mutableStateOf(false)
    val isVisible: State<Boolean> get() = _isVisible

    fun show() {
        _isVisible.value = true
    }

    fun hide() {
        _isVisible.value = false
    }
}