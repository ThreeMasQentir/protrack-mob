package org.gspi.protrack.feature.feat_detail_project.presentation.dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import gspiprotrack.composeapp.generated.resources.Res
import gspiprotrack.composeapp.generated.resources.ic_calendar
import gspiprotrack.composeapp.generated.resources.ic_calendar_outline
import org.gspi.protrack.gspidesign.button.GspiButtonOutline
import org.gspi.protrack.gspidesign.button.GspiButtonPickFile
import org.gspi.protrack.gspidesign.button.GspiButtonPrimary
import org.gspi.protrack.gspidesign.font.PoppinsFontFamily
import org.gspi.protrack.gspidesign.text.GspiTextLabel
import org.gspi.protrack.gspidesign.textfield.GspiTextFieldText
import org.jetbrains.compose.resources.painterResource
import kotlin.contracts.contract

@Composable
fun UpdateProgressComponent(
    modifier: Modifier = Modifier,
    isDialogVisible: Boolean,
    onDialogVisibleChange: (Boolean) -> Unit,
) {
    if (isDialogVisible) {
        Dialog(
            onDismissRequest = { onDialogVisibleChange(false) },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, shape = RoundedCornerShape(8.dp))
            ) {
                UpdateProgressTitle()
                ItemUpdateComponent()
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    GspiButtonOutline(
                        text = "Cancel",
                        onClick = {
                            onDialogVisibleChange(false)
                        },
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    GspiButtonPrimary(
                        text = "Save",
                        onClick = {
                            onDialogVisibleChange(false)
                        },
                    )
                }
            }
        }
    }
}

@Composable
fun UpdateProgressTitle(
    title: String = "Update Progress",
    date: String = "27/11/2024",
    modifier: Modifier = Modifier,
    fontFamily: FontFamily = PoppinsFontFamily()
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Title Text
        Text(
            text = title,
            color = Color(0xFF113F3F), // Dark green color
            fontSize = 20.sp, // Title text size
            fontWeight = FontWeight.Bold,
            fontFamily = fontFamily
        )

        // Date with Icon
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(Res.drawable.ic_calendar_outline), // Replace with your calendar icon resource
                contentDescription = "Calendar",
                tint = Color(0xFF113F3F),
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = date,
                color = Color(0xFF113F3F), // Dark green color
                fontSize = 12.sp, // Date text size
                fontWeight = FontWeight.SemiBold,
                fontFamily = fontFamily
            )
        }
    }
}

