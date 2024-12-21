package org.gspi.protrack.feature.feat_dashboard.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gspiprotrack.composeapp.generated.resources.Res
import gspiprotrack.composeapp.generated.resources.ic_hamburger
import gspiprotrack.composeapp.generated.resources.ic_protrack
import org.gspi.protrack.gspidesign.font.PoppinsFontFamily
import org.jetbrains.compose.resources.painterResource

@Composable
fun ProTrackHeaderComponent(
    isDashboard: Boolean = false,
    onHamburgerClick: () -> Unit = {},
    profilName: String= "",
    modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xFF113F3F))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {

            if (isDashboard){
                Image(
                    painter = painterResource(Res.drawable.ic_hamburger),
                    contentDescription = "ProTrack Logo",
                    modifier = Modifier.size(32.dp) .clickable { onHamburgerClick() }
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Image(
                painter = painterResource(Res.drawable.ic_protrack), // Replace with your icon resource for KMP
                contentDescription = "ProTrack Logo",
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = "ProTrack",
                    style = TextStyle(
                        fontFamily = PoppinsFontFamily(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        color = Color(0xFFFFC130)
                    )
                )
                // by GSPI Text
                Text(
                    text = "by GSPI",
                    style = TextStyle(
                        fontFamily = PoppinsFontFamily(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.sp,
                        color = Color(0xFFFFEFC4)
                    )
                )
            }
        }
        Text(
            text = "Hi, $profilName",
            style = TextStyle(
                fontFamily = PoppinsFontFamily(),
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                color = Color(0xFFFFEFC4)
            )
        )
    }
}