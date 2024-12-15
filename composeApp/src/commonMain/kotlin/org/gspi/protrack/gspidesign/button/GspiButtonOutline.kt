package org.gspi.protrack.gspidesign.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gspiprotrack.composeapp.generated.resources.Res
import gspiprotrack.composeapp.generated.resources.poppins_semibold
import org.jetbrains.compose.resources.Font

@Composable
fun GspiButtonOutline(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier.height(48.dp),
        border = BorderStroke(1.dp, Color(0xFF113F3F)), // Outline color dark green
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Color.White, // Container color set to white
            contentColor = Color(0xFF113F3F) // Text color set to dark green
        ),
        shape = RoundedCornerShape(8.dp) // Rounded corners with 8dp
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontFamily = FontFamily(Font(Res.font.poppins_semibold, FontWeight.SemiBold)), // Poppins Semibold font
                fontSize = 12.sp, // Text size
                color = Color(0xFF113F3F) // Text color, set to match outline
            )
        )
    }
}