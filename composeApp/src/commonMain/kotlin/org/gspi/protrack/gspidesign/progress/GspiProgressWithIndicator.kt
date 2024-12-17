package org.gspi.protrack.gspidesign.progress

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.gspi.protrack.gspidesign.font.PoppinsFontFamily

@Composable
fun GspiProgressWithIndicator(
    label: String, // Label for the progress indicator
    progress: Int, // Progress value between 0 and 100
    currentProgress: Int, // Current progress value
    total: Int, // Total value for the indicator
    modifier: Modifier = Modifier
) {
    // Clamp progress to ensure it's within 0 to 100
    val clampedProgress = progress.coerceIn(0, 100)

    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp), // Space between label and progress bar
        horizontalAlignment = Alignment.Start,
        modifier = modifier.fillMaxWidth()
    ) {
        // Label Text
        Text(
            text = label,
            style = TextStyle(
                fontFamily = PoppinsFontFamily(),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color(0xFF113F3F) // Dark green
            )
        )
        Spacer(modifier = Modifier.height(4.dp))

        // Progress Bar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
                .background(Color(0xFFFFF4D4), shape = RoundedCornerShape(12.dp))
                .clip(RoundedCornerShape(12.dp))
        ) {
            // Progress fill
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(clampedProgress / 100f)
                    .background(Color(0xFFFFC130), shape = RoundedCornerShape(12.dp))
            )

            // Progress Text (e.g., 0%)
            Text(
                text = "$clampedProgress%",
                style = TextStyle(
                    fontFamily = PoppinsFontFamily(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 10.sp,
                    color = Color(0xFF113F3F)
                ),
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 8.dp)
            )

            // End Indicator (e.g., 0/68)
            Text(
                text = "${currentProgress}/${total}",
                style = TextStyle(
                    fontFamily = PoppinsFontFamily(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 10.sp,
                    color = Color(0xFF113F3F)
                ),
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 8.dp)
            )
        }
    }
}