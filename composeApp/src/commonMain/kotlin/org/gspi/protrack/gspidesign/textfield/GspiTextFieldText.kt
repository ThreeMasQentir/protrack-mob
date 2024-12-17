package org.gspi.protrack.gspidesign.textfield

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GspiTextFieldText(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Enter text"
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = placeholder,
                color = Color(0xFFA59C9C), // Placeholder text color
                style = TextStyle(fontSize = 14.sp) // Placeholder text size
            )
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text
        ),
        textStyle = TextStyle(
            fontSize = 14.sp, // Adjusted text size
            color = Color(0xFFA59C9C) // Text color
        ),
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color(0xFFA59C9C), // Text color when focused
            unfocusedTextColor = Color(0xFFA59C9C), // Text color when not focused
            focusedIndicatorColor = Color(0xFFFFC130), // Focused border color
            unfocusedIndicatorColor = Color(0xFFFFC130), // Unfocused border color
            cursorColor = Color(0xFFA59C9C), // Cursor color
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(8.dp), // Rounded border
        singleLine = true, // Keeps it single line
        modifier = modifier
            .height(48.dp) // Fixed height
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    )
}