package org.gspi.protrack.gspidesign.textfield

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun GspiTextFieldPhoneNumber(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Enter phone number",
    isValid: (Boolean) -> Unit
) {
    val isPhoneValid = value.length in 10..13 && value.all { it.isDigit() }
    isValid(isPhoneValid)

    OutlinedTextField(
        value = value,
        onValueChange = { newValue ->
            if (newValue.all { it.isDigit() } && newValue.length <= 13) {
                onValueChange(newValue)
            }
        },
        placeholder = {
            Text(
                text = placeholder,
                color = Color(0xFFA59C9C)
            )
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Phone
        ),
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
        shape = RoundedCornerShape(8.dp), // 8dp rounded corners
        singleLine = true,
        modifier = modifier.fillMaxWidth().padding(16.dp)
    )
}