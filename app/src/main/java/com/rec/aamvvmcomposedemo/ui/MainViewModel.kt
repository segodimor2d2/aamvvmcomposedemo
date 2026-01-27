package com.rec.aamvvmcomposedemo.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {
    private val _message = MutableStateFlow("Mensagem inicial")
    private val _text = MutableStateFlow("")

    val message = _message.asStateFlow()
    val text = _text.asStateFlow()

    fun changeMessage() {
        _message.value = "Mensagem alterada pelo ViewModel"
    }

    fun onTextChange(newText: String) {
        _text.value = newText
    }

    fun apagaMessage() {
        _message.value = ""
        _text.value = ""
    }
}



