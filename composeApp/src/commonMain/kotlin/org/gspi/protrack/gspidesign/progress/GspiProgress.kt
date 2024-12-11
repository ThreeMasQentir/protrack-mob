package org.gspi.protrack.gspidesign.progress

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
fun GspiProgress(
    progress: Int, // Progress value between 0 and 100
    modifier: Modifier = Modifier
) {
    // Clamp progress to ensure it's within 0 to 100
    val clampedProgress = progress.coerceIn(0, 100)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(18.dp) // Height of the progress bar
            .background(Color(0xFFFFF4D4), shape = RoundedCornerShape(12.dp)) // Light yellow background
            .clip(RoundedCornerShape(12.dp)) // Ensures the progress is clipped to the shape
    ) {
        // Progress fill
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width((clampedProgress * 3).dp) // Adjust width based on progress (300.dp max for 100%)
                .background(Color(0xFFFFC130), shape = RoundedCornerShape(12.dp)) // Yellow fill
        )

        // Progress Text
        Text(
            text = "$clampedProgress%", // Display progress as a percentage string
            style = TextStyle(
                fontFamily = PoppinsFontFamily(),
                fontWeight = FontWeight.Bold,
                fontSize = 10.sp,
                color = Color(0xFF113F3F) // Dark green color
            ),
            modifier = Modifier
                .align(Alignment.CenterStart) // Align the text inside the progress
                .padding(start = 8.dp) // Padding for text inside the progress bar
        )
    }
}