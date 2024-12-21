package org.gspi.protrack.feature.feat_detail_project.presentation.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.gspi.protrack.common.utils.isOverdue
import org.gspi.protrack.gspidesign.font.PoppinsFontFamily

@Composable
fun TimelineComponent(
    startDate: String,
    endDate: String,
    daysRemaining: String,
    isOverdue: Boolean = false
) {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Title: Timeline
        Text(
            text = "Timeline",
            fontSize = 18.sp, // Increased by 2px
            color = Color(0xFF113F3F),
            fontWeight = FontWeight.Bold,
            fontFamily = PoppinsFontFamily()
        )

        // Start Date Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Start Date",
                fontSize = 14.sp, // Increased by 2px
                color = Color(0xFF113F3F),
                fontFamily = PoppinsFontFamily()

            )
            Text(
                text = startDate,
                fontSize = 14.sp, // Increased by 2px
                color = Color(0xFFA59C9C),
                fontFamily = PoppinsFontFamily()

            )
        }

        // End Date Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "End Date",
                fontSize = 14.sp, // Increased by 2px
                color = Color(0xFF113F3F),
                fontFamily = PoppinsFontFamily()

            )
            Text(
                text = endDate,
                fontSize = 14.sp, // Increased by 2px
                color = Color(0xFFA59C9C),
                fontFamily = PoppinsFontFamily()

            )
        }

        // Days Remaining Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Days Remaining",
                fontSize = 14.sp, // Increased by 2px
                color = Color(0xFF113F3F),
                fontWeight = FontWeight.Medium,
                fontFamily = PoppinsFontFamily()
            )
            Text(
                text = daysRemaining,
                fontSize = 14.sp, // Increased by 2px
                color = if (!isOverdue) Color(0xFFA59C9C) else Color(0xFFDA292E),
                fontFamily = PoppinsFontFamily()
            )
        }
    }
}