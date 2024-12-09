package org.gspi.protrack.feature.feat_login.presentation.eventstate

sealed class LoginEvent {
    data class PhoneChanged(val phone: String) : LoginEvent()
    data class PasswordChanged(val password: String) : LoginEvent()
    data object LoginClicked : LoginEvent()
    data object ClearError : LoginEvent()
}