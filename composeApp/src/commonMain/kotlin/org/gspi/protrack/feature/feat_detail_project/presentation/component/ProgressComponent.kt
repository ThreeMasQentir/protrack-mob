package org.gspi.protrack.feature.feat_detail_project.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.gspi.protrack.gspidesign.progress.GspiProgressWithIndicator

@Composable
fun ProgressComponent(modifier: Modifier = Modifier) {
    Row(modifier = modifier.fillMaxWidth()){
        CircularProgressComponentWithLabel(progress = 0.5f, modifier = Modifier.weight(1f))
        Column(modifier = Modifier.weight(2f)) {
            GspiProgressWithIndicator(
                label = "Akuisisi Titik Kontrol",
                progress = 14,  // Current progress
                total = 68,    // Total value
                currentProgress = 29,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
            GspiProgressWithIndicator(
                label = "Akuisisi Titik Kontrol",
                progress = 14,  // Current progress
                total = 68,    // Total value
                currentProgress = 29,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
            GspiProgressWithIndicator(
                label = "Akuisisi Titik Kontrol",
                progress = 14,  // Current progress
                total = 68,    // Total value
                currentProgress = 29,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }
}