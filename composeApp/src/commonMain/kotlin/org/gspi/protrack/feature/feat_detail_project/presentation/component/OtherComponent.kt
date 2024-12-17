package org.gspi.protrack.feature.feat_detail_project.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gspiprotrack.composeapp.generated.resources.Res
import gspiprotrack.composeapp.generated.resources.ic_document
import gspiprotrack.composeapp.generated.resources.ic_log
import org.gspi.protrack.gspidesign.button.GspiButtonLeftIconFull
import org.gspi.protrack.gspidesign.font.PoppinsFontFamily

@Composable
fun OtherComponent(modifier: Modifier = Modifier,
                   onDocumentClick: () -> Unit,
                   onLogClick: () -> Unit
                   ) {
    Column(
        modifier = modifier.padding(horizontal = 16.dp)
    ){
        Text(
            text = "Form",
            fontSize = 18.sp, // Increased by 2px
            color = Color(0xFF113F3F),
            fontWeight = FontWeight.Bold,
            fontFamily = PoppinsFontFamily()
        )
        Spacer(modifier = Modifier.padding(8.dp))
        GspiButtonLeftIconFull(
            text = "Document",
            icon = Res.drawable.ic_document,
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                onDocumentClick()
            },
            buttonColor = Color(0xFFFFEFC4),
            textColor = Color(0xFF113F3F)
        )
        Spacer(modifier = Modifier.padding(8.dp))
        GspiButtonLeftIconFull(
            text = "Log",
            icon = Res.drawable.ic_log,
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                onLogClick()
            },
            buttonColor = Color(0xFFFFEFC4),
            textColor = Color(0xFF113F3F)
        )
    }
}