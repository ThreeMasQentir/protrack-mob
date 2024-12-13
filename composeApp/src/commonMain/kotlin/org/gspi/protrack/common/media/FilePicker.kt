package org.gspi.protrack.common.media

import androidx.compose.runtime.Composable
import org.gspi.protrack.common.picker.AllowedFileType
import org.gspi.protrack.common.picker.FilePickResult

@Composable
expect fun LaunchFilePicker(
    allowedType: AllowedFileType = AllowedFileType.ANY,
    onResult: (FilePickResult) -> Unit,
    //composable
    content: @Composable (onClick: () -> Unit) -> Unit
)