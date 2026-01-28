package com.rec.aamvvmcomposedemo.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private var email = ""
    private var password = ""

    private val _uiState =
        MutableStateFlow<LoginUiState>(
            LoginUiState.Editing(
                email = "",
                password = "",
                isFormValid = false
            )
        )

    val uiState = _uiState.asStateFlow()

    fun onEmailChange(value: String) {
        email = value
        emitEditing()
    }

    fun onPasswordChange(value: String) {
        password = value
        emitEditing()
    }

    private fun emitEditing() {
        _uiState.value = LoginUiState.Editing(
            email = email,
            password = password,
            isFormValid = email.isNotBlank() && password.length >= 4
        )
    }

    fun login() {
        val state = _uiState.value

        if (state !is LoginUiState.Editing || !state.isFormValid) {
            _uiState.value =
                LoginUiState.Error("Email ou senha inválidos")
            return
        }

        viewModelScope.launch {
            _uiState.value = LoginUiState.Loading

            // simula chamada de rede
            delay(2000)

            if (email == "tt@ee.com" && password == "qwer") {
                _uiState.value =
                    LoginUiState.Success("Login realizado com sucesso ✅")
            } else {
                _uiState.value =
                    LoginUiState.Error("Credenciais incorretas ❌")
            }
        }
    }

    fun reset() {
        email = ""
        password = ""
        emitEditing()

}




