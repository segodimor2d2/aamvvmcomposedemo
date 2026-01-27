package com.rec.aamvvmcomposedemo.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {
    private val _message = MutableStateFlow("Mensagem inicial")
    val message = _message.asStateFlow()

    fun changeMessage() {
        _message.value = "Mensagem alterada pelo ViewModel"
    }
}
