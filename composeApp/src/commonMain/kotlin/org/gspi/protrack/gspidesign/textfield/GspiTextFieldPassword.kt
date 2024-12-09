package org.gspi.protrack.gspidesign.textfield

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun GspiTextFieldPassword(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Enter password"
) {
    var passwordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = placeholder,
                color = Color(0xFFA59C9C) // Placeholder text color
            )
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = if (passwordVisible) KeyboardType.Text else KeyboardType.Password
        ),
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(
                    imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                    contentDescription = if (passwordVisible) "Hide password" else "Show password",
                    tint = Color(0xFFA59C9C) // Eye icon color
                )
            }
        },
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