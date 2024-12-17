package org.gspi.protrack.feature.feat_dashboard.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import gspiprotrack.composeapp.generated.resources.Res
import gspiprotrack.composeapp.generated.resources.ic_logout
import gspiprotrack.composeapp.generated.resources.ic_project_map
import gspiprotrack.composeapp.generated.resources.ic_users
import org.gspi.protrack.gspidesign.button.GspiButtonLeftIconFull

@Composable
fun DrawerDashboard(
    onUserClick: () -> Unit,
    onProjectClick: () -> Unit,
    onLogOutClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(450.dp * 0.75f)
            .background(Color(0xFF113F3F))
            .padding(16.dp)
    ) {
        // Header
        ProTrackHeaderComponent(isDashboard = false)
        Spacer(modifier = Modifier.padding(8.dp))

        // Other Buttons
        GspiButtonLeftIconFull(
            text = "Projects",
            icon = Res.drawable.ic_project_map,
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                onProjectClick()
            },
            buttonColor = Color(0xFFFFEFC4),
            textColor = Color(0xFF113F3F)
        )
        Spacer(modifier = Modifier.padding(8.dp))
        GspiButtonLeftIconFull(
            text = "Users",
            icon = Res.drawable.ic_users,
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                onUserClick()
            },
            buttonColor = Color(0xFFFFEFC4),
            textColor = Color(0xFF113F3F)
        )


        // Spacer to push content above and Log Out button to the bottom
        Spacer(modifier = Modifier.weight(1f))

        // Log Out Button
        GspiButtonLeftIconFull(
            text = "Log Out",
            icon = Res.drawable.ic_logout,
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                onLogOutClick()
            },
            buttonColor = Color(0xFFDA292E),
            textColor = Color.White
        )
    }
}