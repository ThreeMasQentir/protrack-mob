package org.gspi.protrack.feature.feat_detail_project.presentation.dialog

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
import org.gspi.protrack.common.media.LaunchFilePicker
import org.gspi.protrack.common.picker.AllowedFileType
import org.gspi.protrack.common.picker.FilePickResult
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
    isDialogVisible: Boolean,
    projectName: String,
    onProjectNameChange: (String) -> Unit,
    startDate: String,
    onStartDateChange: (String) -> Unit,
    endDate: String,
    aoiFileName: String,
    onAoiFileChange: (FilePickResult) -> Unit,
    rencanaTitikControlFileName: String,
    onRencanaTitikControlFileChange: (FilePickResult) -> Unit,
    onEndDateChange: (String) -> Unit,
    onDeleteProject: (String) -> Unit,
    onCancelButtonClicked: () -> Unit,
    onSaveButtonClicked: () -> Unit,
) {
    if (isDialogVisible) {
        Dialog(
            onDismissRequest = { onCancelButtonClicked() },
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
                    value = projectName,
                    onValueChange = {
                        onProjectNameChange(it)
                    },
                    placeholder = "Project Name",
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
                GspiTextLabel("Start Date", modifier= Modifier.align(Alignment.Start).padding(start = 16.dp))
                Spacer(modifier = Modifier.height(8.dp))
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth().padding(end = 16.dp),

                    ){
                    Image(
                        painter = painterResource(Res.drawable.ic_calendar),
                        contentDescription = "Calendar Icon",
                        modifier = Modifier.padding(start = 16.dp).size(48.dp)
                    )
                    GspiTextFieldText(
                        value = startDate,
                        onValueChange = {
                            onStartDateChange(it)
                        },
                        placeholder = "Select Date"
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))

                GspiTextLabel("End Date", modifier= Modifier.align(Alignment.Start).padding(start = 16.dp))
                Spacer(modifier = Modifier.height(8.dp))
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth().padding(end = 16.dp),
                ){
                    Image(
                        painter = painterResource(Res.drawable.ic_calendar),
                        contentDescription = "Calendar Icon",
                        modifier = Modifier.padding(start = 16.dp).size(48.dp)
                    )
                    GspiTextFieldText(
                        value = endDate,
                        onValueChange = {
                            onEndDateChange(it)
                        },
                        placeholder = "Select Date"
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                GspiTextLabel("Area of Interest (AOI)", modifier= Modifier.align(Alignment.Start).padding(start = 16.dp))
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
                ) {
                    LaunchFilePicker(
                        allowedType = AllowedFileType.ZIP,
                        onResult = { file ->
                            onAoiFileChange(file)
                        },
                        content = {
                            GspiButtonPickFile(
                                text = aoiFileName,
                                onClick = { it.invoke() }
                            )
                        },
                    )
                }
                GspiTextLabel("Rencana Titik Kontrol", modifier= Modifier.align(Alignment.Start).padding(start = 16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
                ) {
                LaunchFilePicker(
                    allowedType = AllowedFileType.ZIP,
                    onResult = { file ->
                        onRencanaTitikControlFileChange(file)
                    },
                    content = {
                        GspiButtonPickFile(
                            text = rencanaTitikControlFileName,
                            onClick = { it.invoke() }
                        )
                    }
                )
                    }

                TextButton(
                    onClick = {
                        onDeleteProject(projectName)
                    },
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
                            onCancelButtonClicked()
                        },
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    GspiButtonPrimary(
                        text = "Save",
                        onClick = {
                            onSaveButtonClicked()
                        },
                    )
                }
            }
        }
    }
}

