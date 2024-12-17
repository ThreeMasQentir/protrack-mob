package org.gspi.protrack.feature.feat_detail_project.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gspiprotrack.composeapp.generated.resources.Res
import gspiprotrack.composeapp.generated.resources.ic_edit
import gspiprotrack.composeapp.generated.resources.ic_project_map
import org.gspi.protrack.feature.feat_dashboard.presentation.component.ProjectTitle
import org.gspi.protrack.gspidesign.button.GspiButtonLeftIcon
import org.gspi.protrack.gspidesign.font.PoppinsFontFamily

@Composable
fun ProgressTitleComponent(
    onButtonClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp), // Padding for Row
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Title Text
        Text(
            text = "Progress",
            style = TextStyle(
                fontFamily = PoppinsFontFamily(),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color(0xFF113F3F)
            ),
        )

        // Button at the End
        GspiButtonLeftIcon(
            modifier = Modifier.wrapContentWidth(), // Remove weight
            text = "Update",
            icon = Res.drawable.ic_edit,
            onClick = {
                onButtonClick()
            }
        )
    }
}