package org.gspi.protrack.gspidesign.infodialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import org.gspi.protrack.gspidesign.font.PoppinsFontFamily
import androidx.compose.runtime.State

object InfoDialog {
    private val _isVisible = mutableStateOf(false)
    val isVisible: State<Boolean> get() = _isVisible

    internal var title: String = ""
    internal var description: String = ""
    internal var onOkClick: (() -> Unit)? = null

    fun show(
        title: String,
        description: String,
        onOkClick: (() -> Unit)? = null
    ) {
        this.title = title
        this.description = description
        this.onOkClick = onOkClick
        _isVisible.value = true
    }

    fun hide() {
        _isVisible.value = false
    }

    @Composable
    fun Content() {
        if (isVisible.value) {
            Dialog(
                onDismissRequest = { hide() },
                properties = DialogProperties(
                    dismissOnBackPress = false,
                    dismissOnClickOutside = false
                )
            ) {
                Box(
                    modifier = Modifier
                        .padding(16.dp)
                        .background(Color.White, shape = RoundedCornerShape(8.dp))
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        horizontalAlignment = Alignment.Start
                    ) {
                        // Title
                        Text(
                            text = InfoDialog.title,
                            style = TextStyle(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold
                            ),
                            fontFamily = PoppinsFontFamily(),
                            color = Color.Black
                        )

                        // Description
                        Text(
                            text = InfoDialog.description,
                            style = TextStyle(fontSize = 14.sp),
                            color = Color.Gray,
                            fontFamily = PoppinsFontFamily(),
                            textAlign = TextAlign.Justify
                        )

                        // OK Button
                        Row(
                            horizontalArrangement = Arrangement.End,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            TextButton(onClick = {
                                InfoDialog.onOkClick?.invoke()
                                InfoDialog.hide()
                            }) {
                                Text(
                                    color = Color(0xFF113F3F),
                                    fontFamily = PoppinsFontFamily(),
                                    text = "OK".uppercase()
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}