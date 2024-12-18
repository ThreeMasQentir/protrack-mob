package org.gspi.protrack.feature.feat_dashboard.presentation.dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
    isDialogVisible: Boolean,
    onDialogVisibleChange: (Boolean) -> Unit,
    userName: String,
    onUserNameChange: (String) -> Unit,
    userUserName: String,
    onUserUserNameChange: (String) -> Unit,
    userPassword: String,
    onUserPasswordChange: (String) -> Unit,
    userEmail: String,
    onUserEmailChange: (String) -> Unit,
    userPhoneNumber: String,
    onUserPhoneNumberChange: (String) -> Unit,
    onSaveClick: () -> Unit,
    onEditClick: () -> Unit,
    isEdit : Boolean
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
                    .padding(horizontal = 16.dp)
                    .background(Color.White, shape = RoundedCornerShape(8.dp))
            ) {
                Text(
                    text = "Add User",
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    style = TextStyle(
                        fontFamily = PoppinsFontFamily(),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF113F3F)
                    )
                )
                LabeledTextField(
                    label = "Name",
                    value = userName,
                    onValueChange = {
                        onUserNameChange(it)
                    },
                    placeholder = "Name"
                )
                LabeledTextField(
                    label = "Username",
                    value = userUserName,
                    onValueChange = {
                        onUserUserNameChange(it)
                    },
                    placeholder = "Username"
                )
                LabeledTextField(
                    label = "Password",
                    value = userPassword,
                    onValueChange = {
                        onUserPasswordChange(it)
                    },
                    placeholder = "Password"
                )
                LabeledTextField(
                    label = "Email",
                    value = userEmail,
                    onValueChange = {
                        onUserEmailChange(it)
                    },
                    placeholder = "Email"
                )
                LabeledTextField(
                    label = "Phone Number",
                    value = userPhoneNumber,
                    onValueChange = {
                        onUserPhoneNumberChange(it)
                    },
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
                            if (isEdit) {
                                onEditClick()
                            } else {
                                onSaveClick()
                            }
                        },
                    )
                }
            }
        }
    }
}

@Composable
fun LabeledTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        GspiTextLabel(label, modifier = Modifier.align(Alignment.Start).padding(start = 16.dp))
        Spacer(modifier = Modifier.height(8.dp))
        GspiTextFieldText(
            value = value,
            onValueChange = onValueChange,
            placeholder = placeholder,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}

