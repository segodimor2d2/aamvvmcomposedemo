package com.rec.aamvvmcomposedemo.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _uiState = MutableStateFlow<LoginUiState>(LoginUiState.Idle)
    val uiState = _uiState.asStateFlow()

    val isFormValid: Boolean
        get() = email.value.isNotBlank() && password.value.length >= 4

    fun onEmailChange(value: String) {
        _email.value = value
    }

    fun onPasswordChange(value: String) {
        _password.value = value
    }

    fun login() {
        if (!isFormValid) {
            _uiState.value = LoginUiState.Error("Email ou senha inválidos")
            return
        }

        viewModelScope.launch {
            _uiState.value = LoginUiState.Loading

            // simula chamada de rede
            delay(2000)

            if (email.value == "tt@ee.com" && password.value == "qwer") {
                _uiState.value = LoginUiState.Success("Login realizado com sucesso ✅")
            } else {
                _uiState.value = LoginUiState.Error("Credenciais incorretas ❌")
            }
        }
    }

    fun reset() {
        _email.value = ""
        _password.value = ""
        _uiState.value = LoginUiState.Idle
    }


}




