package org.gspi.protrack.feature.feat_dashboard.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gspiprotrack.composeapp.generated.resources.Res
import gspiprotrack.composeapp.generated.resources.ic_edit
import gspiprotrack.composeapp.generated.resources.ic_project_map
import org.gspi.protrack.gspidesign.button.GspiButtonLeftIcon
import org.gspi.protrack.gspidesign.font.PoppinsFontFamily
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun NewProjectSearchComponent(
    searchValue: String,
    onValueChange: (String) -> Unit,
    onButtonClick: () -> Unit,
    title: String = "Projects",
    buttonText: String = "New Project",
    icon: DrawableResource = Res.drawable.ic_project_map
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth().padding(end = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ProjectTitle(
                text = title,
                icon = icon,
                modifier = Modifier.padding(start = 16.dp).weight(1.5f)
            )
            GspiButtonLeftIcon(
                modifier = Modifier.wrapContentWidth().weight(1f),
                text = buttonText,
                icon = Res.drawable.ic_edit,
                onClick = {
                    onButtonClick()
                }
            )
        }
        SearchComponent(
            value = searchValue,
            onValueChange = {
                onValueChange(it)
            },
            modifier = Modifier.padding(16.dp)
        )
    }

}

@Composable
fun SearchComponent(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp), // Set the height of the search bar
        placeholder = {
            Text(
                text = "Search project here",
                style = TextStyle(
                    fontFamily = PoppinsFontFamily(),
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = Color(0xFF9E9E9E) // Placeholder gray color
                )
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search, // Material Icon for Search
                contentDescription = "Search Icon",
                modifier = Modifier.size(20.dp), // Icon size
                tint = Color(0xFF3C3C3C) // Icon color
            )
        },
        singleLine = true,
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color(0xFFA59C9C), // Font color when focused
            unfocusedTextColor = Color(0xFFA59C9C), // Font color when not focused
            disabledTextColor = Color(0xFFA59C9C), // Font color when disabled
            focusedContainerColor = Color.Transparent, // Transparent inside background
            unfocusedContainerColor = Color.Transparent, // Transparent inside background
            disabledContainerColor = Color.Transparent, // Transparent inside background
            focusedIndicatorColor = Color(0xFFA59C9C), // Border color when focused
            unfocusedIndicatorColor = Color(0xFFA59C9C), // Border color when not focused
            disabledIndicatorColor = Color(0xFFA59C9C), // Border color when disabled
            cursorColor = Color(0xFFA59C9C), // Cursor color
            focusedPlaceholderColor = Color(0xFFA59C9C), // Placeholder text color when focused
            unfocusedPlaceholderColor = Color(0xFFA59C9C), // Placeholder text color when not focused
            disabledPlaceholderColor = Color(0xFFA59C9C) // Placeholder text color when disabled
        ),
        shape = RoundedCornerShape(8.dp) // Rounded corners for the search bar
    )
}

@Composable
fun ProjectTitle(
    text: String,
    icon: DrawableResource,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Icon
        Icon(
            painter = painterResource(icon),
            contentDescription = null,
            modifier = Modifier.size(32.dp),
            tint = Color(0xFFFFC130)
        )
        Spacer(modifier = Modifier.width(8.dp)) // Space between icon and text
        // Title Text
        Text(
            text = text,
            style = TextStyle(
                fontFamily = PoppinsFontFamily(),
                fontWeight = FontWeight.Bold, // Bold font weight
                fontSize = 24.sp, // Font size
                color = Color(0xFF113F3F) // Text color
            )
        )
    }
}

