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

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _loginResult = MutableStateFlow<String?>(null)
    val loginResult = _loginResult.asStateFlow()

    val isFormValid = MutableStateFlow(false)

    fun onEmailChange(newEmail: String) {
        _email.value = newEmail
        validateForm()
    }

    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
        validateForm()
    }

    private fun validateForm() {
        isFormValid.value = _email.value.contains("@") && _password.value.length >= 6
    }

    fun login() {
        viewModelScope.launch {
            _isLoading.value = true
            _loginResult.value = null

            delay(2000) // simula chamada de rede

            _isLoading.value = false

            if (_email.value == "tt@ee.com" &&
                _password.value == "123456"
            ) {
                _loginResult.value = "Login realizado com sucesso ✅"
            } else {
                _loginResult.value = "Email ou senha inválidos ❌"
            }
        }
    }


}




