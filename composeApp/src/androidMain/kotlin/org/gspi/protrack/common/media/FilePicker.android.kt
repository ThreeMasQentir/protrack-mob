package org.gspi.protrack.common.media

import android.annotation.SuppressLint
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.launch
import org.gspi.protrack.common.picker.AllowedFileType
import org.gspi.protrack.common.picker.FilePickResult

@SuppressLint("Range")
@Composable
actual fun LaunchFilePicker(
    allowedType: AllowedFileType,
    onResult: (FilePickResult) -> Unit,
    content: @Composable (onClick: () -> Unit) -> Unit
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var shouldLaunchPicker by remember { mutableStateOf(false) }

    val fileLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        scope.launch {
            uri?.let {
                val byteArray = context.contentResolver.openInputStream(it)?.use { inputStream ->
                    inputStream.readBytes()
                }
                val fileName = context.contentResolver.query(it, null, null, null, null)?.use { cursor ->
                    cursor.moveToFirst()
                    cursor.getString(cursor.getColumnIndex("_display_name"))
                }
                onResult(FilePickResult.Success(byteArray, fileName ?: "file.zip"))
            } ?: run {
                onResult(FilePickResult.Error("No file selected"))
            }
        }
    }

    // Trigger the file picker launch when shouldLaunchPicker becomes true
    LaunchedEffect(shouldLaunchPicker) {
        if (shouldLaunchPicker) {
            when(allowedType){
                AllowedFileType.ANY -> fileLauncher.launch("*/*")
                AllowedFileType.ZIP -> fileLauncher.launch("application/zip")
                AllowedFileType.PDF -> fileLauncher.launch("application/pdf")
            }
            shouldLaunchPicker = false
        }
    }

    content { shouldLaunchPicker = true }

}