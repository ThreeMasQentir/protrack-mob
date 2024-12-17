package org.gspi.protrack.feature.feat_detail_project.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.ArrowLeft
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource

@Composable
fun HeaderProjectComponent(
    modifier: Modifier = Modifier,
    title: String = "Proyek Foto Tegak BPN Kabupaten Banjar",
    onBackClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(Color(0xFF113F3F))
            .padding(horizontal = 16.dp, vertical = 8.dp), // Kurangi padding vertikal
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Back Button
            Icon(
                imageVector = Icons.Default.ArrowBackIos,
                contentDescription = "Back",
                tint = Color.White,
                modifier = Modifier
                    .size(24.dp)
                    .clickable { onBackClick() }
            )

            Spacer(modifier = Modifier.width(8.dp)) // Kurangi jarak di sini

            // Title
            Text(
                text = title,
                fontSize = 12.sp,
                color = Color(0xFFFFEFC4),
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start
            )
        }

        // Settings Icon
        Icon(
            imageVector = Icons.Default.Settings,
            contentDescription = "Settings",
            tint = Color.White,
            modifier = Modifier
                .size(24.dp)
                .clickable { onSettingsClick() }
        )
    }
}