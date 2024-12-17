package org.gspi.protrack.feature.feat_dashboard.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gspiprotrack.composeapp.generated.resources.Res
import gspiprotrack.composeapp.generated.resources.ic_account_blue
import gspiprotrack.composeapp.generated.resources.ic_account_red
import gspiprotrack.composeapp.generated.resources.ic_delete_red
import gspiprotrack.composeapp.generated.resources.ic_edit_white
import org.gspi.protrack.gspidesign.font.PoppinsFontFamily
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun ItemUserComponent(
    modifier: Modifier = Modifier,
    isActivated: Boolean,
    userName: String, // Title on green/gray background
    name: String,
    password: String,
    email: String,
    phoneNumber: String,
    joinDate: String,
    fontFamily: FontFamily = PoppinsFontFamily(),
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onStatusClick: () -> Unit
) {
    val backgroundColor = if (isActivated) Color(0xFF113F3F) else Color(0xFFA59C9C)
    val titleColor = Color.White
    val labelColor = Color(0xFF113F3F)
    val valueColor = Color(0xFFA59C9C)
    val iconStatus: DrawableResource = if (isActivated) Res.drawable.ic_account_red else Res.drawable.ic_account_blue

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .border(1.dp, Color.LightGray, shape = RoundedCornerShape(8.dp))
            .background(Color.White)
            .clip(RoundedCornerShape(8.dp))
    ) {
        // Top Header Section
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(backgroundColor)
                .padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Left Section: Edit Icon and Title
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(modifier = Modifier.clickable { onEditClick() },
                        painter = painterResource(Res.drawable.ic_edit_white), contentDescription = "Edit", tint = Color.White)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = userName,
                        color = titleColor,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                }

                // Right Section: Status and Delete Icons
                Row {
                    Icon(
                        modifier = Modifier.clickable { onStatusClick() },
                        painter = painterResource(iconStatus), contentDescription = "Status", tint = if (isActivated) Color.Red else Color(0xFF3982FF))
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        modifier = Modifier.clickable { onDeleteClick() },
                        painter = painterResource(Res.drawable.ic_delete_red), contentDescription = "Delete", tint = Color.Red)
                }
            }
        }

        // Details Section
        Column(modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)) {
            UserDetailRow(label = "Name", value = name, labelColor, valueColor, fontFamily)
            UserDetailRow(label = "Password", value = password, labelColor, valueColor, fontFamily)
            UserDetailRow(label = "Email", value = email, labelColor, valueColor, fontFamily)
            UserDetailRow(
                label = "Phone Number",
                value = phoneNumber,
                labelColor,
                valueColor,
                fontFamily
            )
            UserDetailRow(label = "Join Date", value = joinDate, labelColor, valueColor, fontFamily)
        }
    }
}

@Composable
fun UserDetailRow(
    label: String,
    value: String,
    labelColor: Color,
    valueColor: Color,
    fontFamily: FontFamily
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp)
    ) {
        Text(
            text = label,
            color = labelColor,
            fontFamily = fontFamily,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = value,
            color = valueColor,
            fontFamily = fontFamily,
            fontSize = 12.sp
        )
    }
}