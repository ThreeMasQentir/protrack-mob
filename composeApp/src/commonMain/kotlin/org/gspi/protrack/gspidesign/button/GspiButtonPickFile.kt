package org.gspi.protrack.gspidesign.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.UploadFile
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GspiButtonPickFile(
    text: String = "Select File",
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    buttonColor: Color = Color(0xFFFFEFC4), // Updated to a light beige color for the button background
    textColor: Color = Color(0xFF113F3F), // Text and icon color as a dark forest green
    icon: ImageVector = Icons.Filled.UploadFile // Using material icons for the file upload icon
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .height(48.dp)
            .wrapContentWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor, // Use for the button's main background
            contentColor = textColor // Use for text and icon color
        ),
        shape = RoundedCornerShape(4.dp), // Maintains less rounded corners
        border = BorderStroke(1.dp, Color(0xFFFFC130)) // Outline color as a golden yellow
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = "Upload File",
                modifier = Modifier.size(20.dp),
                tint = textColor // Ensure the icon color matches the text color
            )
            Spacer(modifier = Modifier.width(8.dp)) // Space between icon and text
            Text(
                text = text,
                style = TextStyle(
                    fontFamily = FontFamily.SansSerif, // Using a simple sans-serif font
                    fontWeight = FontWeight.Medium, // Medium weight for the text
                    fontSize = 16.sp, // Appropriate font size for clarity
                    color = textColor // Color for the text
                )
            )
        }
    }
}