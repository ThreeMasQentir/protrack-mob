package org.gspi.protrack.feature.feat_dashboard.presentation.dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import gspiprotrack.composeapp.generated.resources.Res
import gspiprotrack.composeapp.generated.resources.ic_calendar
import org.gspi.protrack.common.media.LaunchFilePicker
import org.gspi.protrack.common.picker.AllowedFileType
import org.gspi.protrack.common.picker.FilePickResult
import org.gspi.protrack.feature.feat_dashboard.presentation.eventstate.DashboardEvent
import org.gspi.protrack.feature.feat_dashboard.presentation.viewmodel.DashboardViewModel
import org.gspi.protrack.gspidesign.button.GspiButtonOutline
import org.gspi.protrack.gspidesign.button.GspiButtonPickFile
import org.gspi.protrack.gspidesign.button.GspiButtonPrimary
import org.gspi.protrack.gspidesign.datepicker.DatePickerDialog
import org.gspi.protrack.gspidesign.font.PoppinsFontFamily
import org.gspi.protrack.gspidesign.text.GspiTextLabel
import org.gspi.protrack.gspidesign.textfield.GspiTextFieldText
import org.jetbrains.compose.resources.painterResource
import kotlin.contracts.contract

@Composable
fun AddEditProjectComponent(
    modifier: Modifier = Modifier,
    isDialogVisible: Boolean,
    onDialogDismiss: () -> Unit,
    projectName: String,
    onProjectNameChange: (String) -> Unit,
    startDate: String,
    onStartDateChange: (String) -> Unit,
    endDate: String,
    onEndDateChange: (String) -> Unit,
    onAoiChange: (FilePickResult) -> Unit,
    onKontrolChange: (FilePickResult) -> Unit,
    aoiFileName: String = "Select File",
    kontrolFileName: String = "Select File",
    onSaveProjectClick: () -> Unit
) {
    if (isDialogVisible) {
        Dialog(
            onDismissRequest = { onDialogDismiss() },
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
                    .padding(16.dp)
            ) {
                // Title
                Text(
                    text = "New Project",
                    style = TextStyle(
                        fontFamily = PoppinsFontFamily(),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF113F3F)
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                GspiTextLabel(
                    text = "Project Name",
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                GspiTextFieldText(
                    value = projectName,
                    onValueChange = onProjectNameChange,
                    placeholder = "Enter Project Name"
                )
                Spacer(modifier = Modifier.height(8.dp))

                GspiTextLabel(
                    text = "Start Date",
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        painter = painterResource(Res.drawable.ic_calendar),
                        contentDescription = "Calendar Icon",
                        modifier = Modifier
                            .size(48.dp).clickable {
                                DatePickerDialog.show { selectedDate ->
                                    onStartDateChange(selectedDate)
                                }
                            }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    GspiTextFieldDatePicker(
                        value = startDate,
                        onValueChange = {
                            onStartDateChange(it)
                        },
                        placeholder = "Select Start Date",
                        modifier = Modifier.weight(1f),
                        onClick = {
                            println("Start Date Clicked")
                        }
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // End Date
                GspiTextLabel(
                    text = "End Date",
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        painter = painterResource(Res.drawable.ic_calendar),
                        contentDescription = "Calendar Icon",
                        modifier = Modifier
                            .size(48.dp)
                            .clickable {
                                DatePickerDialog.show { selectedDate ->
                                    onEndDateChange(selectedDate)
                                }
                            }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    GspiTextFieldDatePicker(
                        value = endDate,
                        onValueChange = {
                            onEndDateChange(it)
                        },
                        placeholder = "Select End Date",
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Area of Interest
                GspiTextLabel(
                    text = "Area of Interest (AOI)",
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                LaunchFilePicker(
                    allowedType = AllowedFileType.ZIP,
                    onResult = { file ->
                        onAoiChange(file)
                    },
                    content = {
                        GspiButtonPickFile(
                            text = aoiFileName,
                            onClick = { it.invoke() }
                        )
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Rencana Titik Kontrol
                GspiTextLabel(
                    text = "Rencana Titik Kontrol",
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                LaunchFilePicker(
                    allowedType = AllowedFileType.ZIP,
                    onResult = { file ->
                        onKontrolChange(file)
                    },
                    content = {
                        GspiButtonPickFile(
                            text = kontrolFileName,
                            onClick = { it.invoke() }
                        )
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Action Buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    GspiButtonOutline(
                        text = "Cancel",
                        onClick = {
                            onDialogDismiss()
                        }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    GspiButtonPrimary(
                        text = "Save",
                        onClick = {
                            onSaveProjectClick()
                        }
                    )
                }
            }
        }
    }
}


@Composable
fun GspiTextFieldDatePicker(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Input Data",
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .height(48.dp) // Fixed height
            .fillMaxWidth()
            .clickable(onClick = onClick) // Make the Box clickable
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                Text(
                    text = placeholder,
                    color = Color(0xFFA59C9C), // Placeholder text color
                    style = TextStyle(fontSize = 14.sp) // Placeholder text size
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text
            ),
            textStyle = TextStyle(
                fontSize = 14.sp, // Adjusted text size
                color = Color(0xFFA59C9C) // Text color
            ),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color(0xFFA59C9C), // Text color when focused
                unfocusedTextColor = Color(0xFFA59C9C), // Text color when not focused
                focusedIndicatorColor = Color(0xFFFFC130), // Focused border color
                unfocusedIndicatorColor = Color(0xFFFFC130), // Unfocused border color
                cursorColor = Color(0xFFA59C9C), // Cursor color
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            ),
            shape = RoundedCornerShape(8.dp), // Rounded border
            singleLine = true, // Keeps it single line
            readOnly = true, // Makes the text field non-editable
            modifier = Modifier.fillMaxSize() // Fill the Box size
        )
    }
}
