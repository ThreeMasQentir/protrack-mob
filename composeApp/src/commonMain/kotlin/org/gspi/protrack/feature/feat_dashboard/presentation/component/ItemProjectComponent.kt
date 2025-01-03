package org.gspi.protrack.feature.feat_dashboard.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.gspi.protrack.gspidesign.font.PoppinsFontFamily
import org.gspi.protrack.gspidesign.progress.GspiProgress
import org.gspi.protrack.gspidesign.text.GspiTextLabel

@Composable
fun ItemProjectComponent(
    projectName: String,
    progress: Int,
    timeline: String,
    timeLeft: String,
    isOverdue: Boolean,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().background(Color.White)
        ) {
            // Header Background
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF113F3F)) // Dark green background
                    .padding(16.dp)
            ) {
                Text(
                    text = projectName,
                    style = TextStyle(
                        fontFamily = PoppinsFontFamily(),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp,
                        color = Color.White
                    )
                )
            }

            // Content
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // Total Progress Label
                Row(
                    modifier = Modifier
                        .fillMaxWidth() ,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    GspiTextLabel(
                        text = "Total Progress",
                        modifier = Modifier
                    )
                    Text(
                        text = timeLeft,
                        style = TextStyle(
                            fontFamily = PoppinsFontFamily(),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 10.sp,
                            color = if (!isOverdue) Color(0xFFA59C9C) else Color(0xFFDA292E)
                        )
                    )
                }

                GspiProgress(
                    progress = progress,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                ) {
                    GspiTextLabel(
                        text = "Timeline",
                        modifier = Modifier
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = timeline,
                        style = TextStyle(
                            fontFamily = PoppinsFontFamily(),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 10.sp,
                            color = Color(0xFFA59C9C) // Gray color
                        ),
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }

            }
        }
    }
}