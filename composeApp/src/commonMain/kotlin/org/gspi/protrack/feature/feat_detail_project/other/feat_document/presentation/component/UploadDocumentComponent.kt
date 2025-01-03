package org.gspi.protrack.feature.feat_detail_project.other.feat_document.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import org.gspi.protrack.common.media.LaunchFilePicker
import org.gspi.protrack.common.picker.AllowedFileType
import org.gspi.protrack.common.picker.FilePickResult
import org.gspi.protrack.gspidesign.button.GspiButtonOutline
import org.gspi.protrack.gspidesign.button.GspiButtonPickFile
import org.gspi.protrack.gspidesign.button.GspiButtonPrimary
import org.gspi.protrack.gspidesign.font.PoppinsFontFamily
import org.gspi.protrack.gspidesign.text.GspiTextLabel
import org.gspi.protrack.gspidesign.textfield.GspiTextFieldText

@Composable
fun UploadDocumentComponent(
    isDialogVisible: Boolean,
    onDialogVisibleChange: (Boolean) -> Unit,
    documentName: String,
    onDocumentNameChange: (String) -> Unit,
    projectName: String= "Select File",
    onProjectNameChange: (FilePickResult) -> Unit,
    onSaveClick: () -> Unit,
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
                    text = "Upload",
                    modifier = Modifier.padding(16.dp),
                    style = TextStyle(
                        fontFamily = PoppinsFontFamily(),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF113F3F)
                    )
                )
                GspiTextLabel("Document Name", modifier= Modifier.align(Alignment.Start).padding(start = 16.dp))
                GspiTextFieldText(
                    value = documentName,
                    onValueChange = {
                        onDocumentNameChange(it)
                    },
                    placeholder = "Document Name",
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp)
                )
                GspiTextLabel("Document", modifier= Modifier.align(Alignment.Start).padding(start = 16.dp))
                LaunchFilePicker(
                    allowedType = AllowedFileType.PDF,
                    onResult = { file ->
                        onProjectNameChange(file)
                    },
                    content = {
                        GspiButtonPickFile(
                            text = projectName,
                            onClick = { it.invoke() },
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
                        )
                    },
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
                            onSaveClick()
                        },
                    )
                }
            }
        }
    }
}

