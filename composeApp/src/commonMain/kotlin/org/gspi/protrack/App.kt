package org.gspi.protrack

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.gspi.protrack.feature.feat_login.presentation.component.LoginBackgroundComponent
import org.gspi.protrack.feature.feat_login.presentation.screen.LoginScreen
import org.gspi.protrack.gspidesign.button.GspiButtonPrimary
import org.gspi.protrack.gspidesign.text.GspiTextLabel
import org.gspi.protrack.gspidesign.textfield.GspiTextFieldPassword
import org.gspi.protrack.gspidesign.textfield.GspiTextFieldPhoneNumber
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {

    LoginScreen()

}


