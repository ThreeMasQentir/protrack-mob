package org.gspi.protrack.feature.feat_detail_project.presentation.dialog

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
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import gspiprotrack.composeapp.generated.resources.Res
import gspiprotrack.composeapp.generated.resources.ic_calendar
import org.gspi.protrack.gspidesign.button.GspiButtonOutline
import org.gspi.protrack.gspidesign.button.GspiButtonPickFile
import org.gspi.protrack.gspidesign.button.GspiButtonPrimary
import org.gspi.protrack.gspidesign.font.PoppinsFontFamily
import org.gspi.protrack.gspidesign.text.GspiTextLabel
import org.gspi.protrack.gspidesign.textfield.GspiTextFieldText
import org.jetbrains.compose.resources.painterResource
import kotlin.contracts.contract

@Composable
fun SettingProjectComponent(
    modifier: Modifier = Modifier,
    isDialogVisible: Boolean,
    onDialogVisibleChange: (Boolean) -> Unit,
    projectName: String,
    onProjectNameChange: (String) -> Unit,
    startDate: String,
    onStartDateChange: (String) -> Unit,
    endDate: String,
    onEndDateChange: (String) -> Unit,
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
                    text = "Project Settings",
                    modifier = Modifier.padding(16.dp),
                    style = TextStyle(
                        fontFamily = PoppinsFontFamily(),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF113F3F)
                    )
                )
                GspiTextLabel("Project Name", modifier= Modifier.align(Alignment.Start).padding(start = 16.dp))
                GspiTextFieldText(
                    value = "",
                    onValueChange = { },
                    placeholder = "Project Name"
                )
                GspiTextLabel("Start Date", modifier= Modifier.align(Alignment.Start).padding(start = 16.dp))
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                ){
                    Image(
                        painter = painterResource(Res.drawable.ic_calendar),
                        contentDescription = "Calendar Icon",
                        modifier = Modifier.padding(start = 16.dp).size(48.dp)
                    )
                    GspiTextFieldText(
                        value = "",
                        onValueChange = { },
                        placeholder = "Project Name"
                    )
                }

                GspiTextLabel("End Date", modifier= Modifier.align(Alignment.Start).padding(start = 16.dp))
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                ){
                    Image(
                        painter = painterResource(Res.drawable.ic_calendar),
                        contentDescription = "Calendar Icon",
                        modifier = Modifier.padding(start = 16.dp).size(48.dp)
                    )
                    GspiTextFieldText(
                        value = "",
                        onValueChange = { },
                        placeholder = "Project Name"
                    )
                }
                GspiTextLabel("Area of Interest (AOI)", modifier= Modifier.align(Alignment.Start).padding(start = 16.dp))
                GspiButtonPickFile(
                    text = "Select File",
                    onClick = { },
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
                GspiTextLabel("Rencana Titik Kontrol", modifier= Modifier.align(Alignment.Start).padding(start = 16.dp))
                GspiButtonPickFile(
                    text = "Select File",
                    onClick = { },
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
                TextButton(
                    onClick = {},
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "Delete Project",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFDA292E), // #DA292E
                        textDecoration = TextDecoration.Underline
                    )
                }
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

