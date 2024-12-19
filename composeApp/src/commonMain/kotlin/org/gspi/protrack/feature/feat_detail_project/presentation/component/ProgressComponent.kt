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
fun ProgressComponent(
    modifier: Modifier = Modifier,
    currentTitikControl: Int,
    totalTitikControl: Int,
    currentFotoUdara: Int,
    totalFotoUdara: Int,
    currentPengolahanData: Int,
    totalPengolahanData: Int,
) {
    val progressTitikControl = (currentTitikControl.toFloat() / totalTitikControl.toFloat()) * 100
    val progressFotoUdara = (currentFotoUdara.toFloat() / totalFotoUdara.toFloat()) * 100
    val progressPengolahanData = (currentPengolahanData.toFloat() / totalPengolahanData.toFloat()) * 100
    val totalProgress = (progressTitikControl + progressFotoUdara + progressPengolahanData) / 3

    Row(modifier = modifier.fillMaxWidth()) {
        CircularProgressComponentWithLabel(progress = totalProgress / 100f, modifier = Modifier.weight(1f))
        Column(modifier = Modifier.weight(2f)) {
            GspiProgressWithIndicator(
                label = "Akuisisi Titik Kontrol",
                progress = progressTitikControl.toInt(),  // Current progress
                total = totalTitikControl,    // Total value
                currentProgress = currentTitikControl,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
            GspiProgressWithIndicator(
                label = "Progress Akuisisi Foto Udara",
                progress = progressFotoUdara.toInt(),  // Current progress
                total = totalFotoUdara,    // Total value
                currentProgress = currentFotoUdara,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
            GspiProgressWithIndicator(
                label = "Progress Pengolahan Data",
                progress = progressPengolahanData.toInt(),  // Current progress
                total = totalPengolahanData,    // Total value
                currentProgress = currentPengolahanData,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }
}