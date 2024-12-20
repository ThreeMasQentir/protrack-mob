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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.gspi.protrack.feature.feat_login.presentation.component.LoginBackgroundComponent
import org.gspi.protrack.feature.feat_login.presentation.eventstate.LoginEvent
import org.gspi.protrack.feature.feat_login.presentation.viewmodel.LoginViewModel
import org.gspi.protrack.gspidesign.button.GspiButtonPrimary
import org.gspi.protrack.gspidesign.error.Error
import org.gspi.protrack.gspidesign.loading.Loading
import org.gspi.protrack.gspidesign.success.SuccessToast
import org.gspi.protrack.gspidesign.text.GspiTextLabel
import org.gspi.protrack.gspidesign.text.GspiTextTitle
import org.gspi.protrack.gspidesign.textfield.GspiTextFieldPassword
import org.gspi.protrack.gspidesign.textfield.GspiTextFieldPhoneNumber
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = koinViewModel(),
    onLoginSuccess: () -> Unit
) {

    val uiState by viewModel.uiState.collectAsState()
    LaunchedEffect(uiState) {
        if (uiState.isLoading) Loading.show() else Loading.hide()
        uiState.errorMessage?.let { errorMessage ->
            Error.show(errorMessage)
            viewModel.onEvent(LoginEvent.ClearError)
        }
        uiState.loginResponse?.let {
            viewModel.onEvent(LoginEvent.OnDecoderToken)
        }
        uiState.decoderTokenResponse?.let {
            SuccessToast.show("Login successful")
            onLoginSuccess()
            Loading.hide()
        }
        viewModel.getToken()?.let {
            if (it != "") {
                onLoginSuccess()
            }
        }
    }


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
                GspiTextLabel("No Hp", modifier= Modifier.align(Alignment.Start).padding(start = 16.dp))
                GspiTextFieldPhoneNumber(
                    value = uiState.loginRequest.phone,
                    onValueChange = {
                        viewModel.onEvent(LoginEvent.PhoneChanged(it))
                    },
                    placeholder = "Enter phone number",
                    isValid = {

                    }
                )
                GspiTextLabel("Password", modifier= Modifier.align(Alignment.Start).padding(start = 16.dp))
                GspiTextFieldPassword(
                    value = uiState.loginRequest.password,
                    onValueChange = {
                        viewModel.onEvent(LoginEvent.PasswordChanged(it))
                    },
                    placeholder = "Enter password"
                )

                GspiButtonPrimary(
                    text = "Login",
                    onClick = {
                        viewModel.onEvent(LoginEvent.LoginClicked)
                    },
                    modifier = Modifier.padding(16.dp).fillMaxWidth()
                )
            }
        }
    }
}