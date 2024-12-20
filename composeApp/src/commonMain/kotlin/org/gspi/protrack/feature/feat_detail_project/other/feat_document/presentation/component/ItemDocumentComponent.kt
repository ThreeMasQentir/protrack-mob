package org.gspi.protrack.feature.feat_detail_project.other.feat_document.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gspiprotrack.composeapp.generated.resources.Res
import gspiprotrack.composeapp.generated.resources.ic_delete_red
import org.gspi.protrack.gspidesign.font.PoppinsFontFamily
import org.gspi.protrack.gspidesign.text.GspiTextLabel
import org.jetbrains.compose.resources.painterResource

@Composable
fun ItemDocumentComponent(
    projectName: String,
    timeLeft: String,
    onClick: () -> Unit = {},
    author: String = "",
    onDownloadClick: () -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().background(Color.White)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF113F3F))
                    .padding(16.dp)
            ) {
                Row(
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                ){
                    Text(
                        text = projectName,
                        style = TextStyle(
                            fontFamily = PoppinsFontFamily(),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 12.sp,
                            color = Color.White
                        )
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        imageVector = Icons.Default.Download,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(24.dp).clickable {
                            onDownloadClick()
                        }
                    )
                    Spacer(modifier = Modifier.width(24.dp))
                    Icon(
                        painter = painterResource(Res.drawable.ic_delete_red),
                        contentDescription = null,
                        tint = Color.Red,
                        modifier = Modifier.size(24.dp).clickable {
                            onDeleteClick()
                        }
                    )
                }

            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth() ,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    GspiTextLabel(
                        text = author,
                        modifier = Modifier
                    )
                    Text(
                        text = timeLeft,
                        style = TextStyle(
                            fontFamily = PoppinsFontFamily(),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 10.sp,
                            color = Color(0xFFA59C9C)
                        )
                    )
                }

            }
        }
    }
}