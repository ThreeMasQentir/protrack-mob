package org.gspi.protrack.gspidesign.textfield

import androidx.compose.foundation.layout.fillMaxWidth
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
                style = MaterialTheme.typography.bodySmall.copy(fontSize = 16.sp) // Adjusting placeholder text size
            )
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text,
        ),
        visualTransformation = VisualTransformation.None,
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color(0xFFA59C9C), // Font color when focused
            unfocusedTextColor = Color(0xFFA59C9C), // Font color when not focused
            disabledTextColor = Color(0xFFA59C9C), // Font color when disabled
            focusedContainerColor = Color.Transparent, // Transparent inside background
            unfocusedContainerColor = Color.Transparent, // Transparent inside background
            disabledContainerColor = Color.Transparent, // Transparent inside background
            focusedIndicatorColor = Color(0xFFFFC130), // Border color when focused
            unfocusedIndicatorColor = Color(0xFFFFC130), // Border color when not focused
            disabledIndicatorColor = Color(0xFFFFC130), // Border color when disabled
            cursorColor = Color(0xFFA59C9C), // Cursor color
            focusedPlaceholderColor = Color(0xFFA59C9C), // Placeholder text color when focused
            unfocusedPlaceholderColor = Color(0xFFA59C9C), // Placeholder text color when not focused
            disabledPlaceholderColor = Color(0xFFA59C9C) // Placeholder text color when disabled
        ),
        textStyle = MaterialTheme.typography.bodySmall.copy(fontSize = 16.sp), // Adjusting text size
        shape = RoundedCornerShape(8.dp), // 8dp rounded corners
        singleLine = true,
        modifier = modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp)
    )
}