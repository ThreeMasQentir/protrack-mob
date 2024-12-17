package org.gspi.protrack.gspidesign.confirmation

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State

object Confirmation {
    private val _isVisible = mutableStateOf(false)
    val isVisible: State<Boolean> get() = _isVisible

    internal var title: String = ""
    internal var message: String = ""
    internal var yesLabel: String = "Yes"
    internal var noLabel: String = "No"
    internal var onYesClick: (() -> Unit)? = null

    fun show(
        title: String,
        message: String,
        yesLabel: String = "Yes",
        noLabel: String = "No",
        onYesClick: () -> Unit
    ) {
        this.title = title
        this.message = message
        this.yesLabel = yesLabel
        this.noLabel = noLabel
        this.onYesClick = onYesClick
        _isVisible.value = true
    }

    fun hide() {
        _isVisible.value = false
    }
}
