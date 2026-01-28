package com.rec.aamvvmcomposedemo.ui

sealed class LoginUiState {

    data class Editing(
        val email: String,
        val password: String,
        val isFormValid: Boolean
    ) : LoginUiState()

    object Loading : LoginUiState()
    data class Success( val message: String) : LoginUiState()
    data class Error( val message: String) : LoginUiState()
}
