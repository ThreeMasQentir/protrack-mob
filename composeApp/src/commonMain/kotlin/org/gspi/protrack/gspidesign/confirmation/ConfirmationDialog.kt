package org.gspi.protrack.gspidesign.confirmation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
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
import org.gspi.protrack.gspidesign.loading.Loading

@Composable
fun ConfirmationDialog() {
    if (Confirmation.isVisible.value) {
        Dialog(
            onDismissRequest = { Confirmation.hide() }, // Prevents dismissal
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
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Title
                    Text(
                        text = Confirmation.title,
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        color = Color.Black
                    )

                    // Message
                    Text(
                        text = Confirmation.message,
                        style = TextStyle(fontSize = 14.sp),
                        color = Color.Gray,
                        textAlign = TextAlign.Center
                    )

                    // Buttons
                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        TextButton(onClick = { Confirmation.hide() }) {
                            Text(
                                color = Color(0xFF113F3F),
                                text = Confirmation.noLabel.uppercase()
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        TextButton(onClick = {
                            Confirmation.onYesClick?.invoke()
                            Confirmation.hide()
                        }) {
                            Text(
                                color = Color(0xFF113F3F),
                                text = Confirmation.yesLabel.uppercase()
                            )
                        }
                    }
                }
            }
        }
    }
}