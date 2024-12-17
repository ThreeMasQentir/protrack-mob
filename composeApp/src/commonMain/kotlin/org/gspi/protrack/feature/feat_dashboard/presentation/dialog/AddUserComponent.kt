package org.gspi.protrack.feature.feat_dashboard.presentation.dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import gspiprotrack.composeapp.generated.resources.Res
import gspiprotrack.composeapp.generated.resources.ic_calendar
import org.gspi.protrack.feature.feat_login.presentation.eventstate.LoginEvent
import org.gspi.protrack.gspidesign.button.GspiButtonOutline
import org.gspi.protrack.gspidesign.button.GspiButtonPickFile
import org.gspi.protrack.gspidesign.button.GspiButtonPrimary
import org.gspi.protrack.gspidesign.font.PoppinsFontFamily
import org.gspi.protrack.gspidesign.text.GspiTextLabel
import org.gspi.protrack.gspidesign.textfield.GspiTextFieldPassword
import org.gspi.protrack.gspidesign.textfield.GspiTextFieldText
import org.jetbrains.compose.resources.painterResource
import kotlin.contracts.contract

@Composable
fun AddEditUserComponent(
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
                Text(
                    text = "New Project",
                    modifier = Modifier.padding(16.dp),
                    style = TextStyle(
                        fontFamily = PoppinsFontFamily(),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF113F3F)
                    )
                )
                GspiTextLabel("Name", modifier= Modifier.align(Alignment.Start).padding(start = 16.dp))
                GspiTextFieldText(
                    value = "",
                    onValueChange = { },
                    placeholder = "Name"
                )
                GspiTextLabel("Username", modifier= Modifier.align(Alignment.Start).padding(start = 16.dp))
                GspiTextFieldText(
                    value = "",
                    onValueChange = { },
                    placeholder = "Username"
                )
                GspiTextLabel("Password", modifier= Modifier.align(Alignment.Start).padding(start = 16.dp))
                GspiTextFieldText(
                    value = "",
                    onValueChange = { },
                    placeholder = "Password"
                )
                GspiTextLabel("Email", modifier= Modifier.align(Alignment.Start).padding(start = 16.dp))
                GspiTextFieldText(
                    value = "",
                    onValueChange = { },
                    placeholder = "Email"
                )
                GspiTextLabel("Phone Number", modifier= Modifier.align(Alignment.Start).padding(start = 16.dp))
                GspiTextFieldText(
                    value = "",
                    onValueChange = { },
                    placeholder = "Phone Number"
                )

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

