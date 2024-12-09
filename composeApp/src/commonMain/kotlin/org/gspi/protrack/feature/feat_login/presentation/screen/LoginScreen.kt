package org.gspi.protrack.feature.feat_login.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.gspi.protrack.feature.feat_login.presentation.component.LoginBackgroundComponent
import org.gspi.protrack.feature.feat_login.presentation.viewmodel.LoginViewModel
import org.gspi.protrack.gspidesign.button.GspiButtonPrimary
import org.gspi.protrack.gspidesign.text.GspiTextLabel
import org.gspi.protrack.gspidesign.text.GspiTextTitle
import org.gspi.protrack.gspidesign.textfield.GspiTextFieldPassword
import org.gspi.protrack.gspidesign.textfield.GspiTextFieldPhoneNumber

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = remember { LoginViewModel() }
) {
    var phoneNumber by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(modifier = modifier.fillMaxSize()) {
        LoginBackgroundComponent(modifier)
        Card(
            modifier = Modifier
                .padding(horizontal = 32.dp, vertical = 16.dp)
                .wrapContentHeight()
                .fillMaxWidth()
                .align(Alignment.Center),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                ,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center

            ) {
                GspiTextTitle("Sign In")
                GspiTextLabel("No Hp", modifier= Modifier.align(Alignment.Start))
                GspiTextFieldPhoneNumber(
                    value = phoneNumber,
                    onValueChange = { phoneNumber = it },
                    placeholder = "Enter phone number",
                    isValid = {

                    }
                )
                GspiTextLabel("Password", modifier= Modifier.align(Alignment.Start))
                GspiTextFieldPassword(
                    value = password,
                    onValueChange = { password = it },
                    placeholder = "Enter password"
                )

                GspiButtonPrimary(
                    text = "Submit",
                    onClick = { /* Handle button click */ },
                    modifier = Modifier.padding(16.dp).fillMaxWidth()
                )
            }
        }
    }
}