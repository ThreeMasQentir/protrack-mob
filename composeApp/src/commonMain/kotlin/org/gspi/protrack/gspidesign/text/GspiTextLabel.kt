package org.gspi.protrack.gspidesign.text

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.gspi.protrack.gspidesign.font.PoppinsFontFamily

@Composable
fun GspiTextLabel(
    text: String,
    modifier: Modifier = Modifier
) {
    BasicText(
        text = text,
        style = TextStyle(
            fontFamily = PoppinsFontFamily(),
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp,
            color = Color(0xFF113F3F)
        ),
        modifier = modifier.padding(horizontal = 16.dp),
    )
}