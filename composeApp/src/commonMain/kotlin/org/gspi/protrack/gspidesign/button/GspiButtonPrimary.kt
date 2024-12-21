package org.gspi.protrack.gspidesign.button

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gspiprotrack.composeapp.generated.resources.Res
import gspiprotrack.composeapp.generated.resources.poppins_semibold
import org.jetbrains.compose.resources.Font

@Composable
fun GspiButtonPrimary(
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF113F3F), // Button background color
            contentColor = Color(0xFFFFEFC4) // Text color
        ),
        enabled = enabled,
        shape = RoundedCornerShape(8.dp), // Rounded corners with 8dp
        modifier = modifier.height(48.dp)
    ) {
        Text(
            text = text,
            fontFamily = FontFamily(
               Font(Res.font.poppins_semibold, FontWeight.SemiBold) // Poppins Semibold font
            ),
            fontSize = 12.sp, // Text size
            color = Color(0xFFFFEFC4) // Text color
        )
    }
}