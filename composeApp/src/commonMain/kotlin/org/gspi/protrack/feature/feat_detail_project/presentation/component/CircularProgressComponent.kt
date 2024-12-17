package org.gspi.protrack.feature.feat_detail_project.presentation.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CircularProgressComponentWithLabel(
    progress: Float = 0.25f,
    size: Dp = 100.dp, // Size of the chart
    primaryColor: Color = Color(0xFFFBC04D), // Progress color (Yellow-Orange)
    backgroundColor: Color = Color(0xFFFFF4D6), // Background color (Light Yellow)
    textColor: Color = Color(0xFF113F3F), // Text color (Dark Green)
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.padding(16.dp)
    ) {
        // Top Label
        Text(
            text = "Total",
            color = textColor,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.size(8.dp))

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.size(size).padding(top = 8.dp)
        ) {
            // Circular background
            Canvas(modifier = Modifier.matchParentSize()) {
                drawArc(
                    color = backgroundColor,
                    startAngle = -90f,
                    sweepAngle = 360f,
                    useCenter = false,
                    style = Stroke(width = size.toPx() / 8, cap = StrokeCap.Round)
                )
            }

            // Progress arc
            Canvas(modifier = Modifier.matchParentSize()) {
                drawArc(
                    color = primaryColor,
                    startAngle = -90f,
                    sweepAngle = 360 * progress,
                    useCenter = false,
                    style = Stroke(width = size.toPx() / 8, cap = StrokeCap.Round)
                )
            }

            // Center text
            Text(
                text = "${(progress * 100).toInt()}%",
                color = textColor,
                fontWeight = FontWeight.Bold,
                fontSize = (size.value / 5).sp
            )
        }
    }
}