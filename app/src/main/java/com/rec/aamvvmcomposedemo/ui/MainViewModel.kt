package com.rec.aamvvmcomposedemo.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {
    private val _message = MutableStateFlow("Mensagem inicial")
    val message = _message.asStateFlow()

    private val _text = MutableStateFlow("")
    val text = _text.asStateFlow()

    private val _validaText = MutableStateFlow("")
    val validaText = _validaText.asStateFlow()

    private val _isValid = MutableStateFlow(false)
    val isValid = _isValid.asStateFlow()


    fun changeMessage() {
        _message.value = "Mensagem alterada pelo ViewModel"
    }

    fun onTextChange(newText: String) {
        _text.value = newText
    }

    fun apagaMessage() {
        _message.value = ""
        _text.value = ""
        _validaText.value = ""
    }

    fun onValidaTextChange(newText: String) {
        _validaText.value = newText
        _isValid.value = newText.length >= 3
    }

}




