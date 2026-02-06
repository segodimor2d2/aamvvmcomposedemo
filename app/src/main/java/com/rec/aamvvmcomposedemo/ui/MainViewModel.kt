package com.rec.aamvvmcomposedemo.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rec.aamvvmcomposedemo.data.model.LedRequest
import com.rec.aamvvmcomposedemo.data.remote.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<RgbUiState>(RgbUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        loadInitialState()
    }

    private fun loadInitialState() {
        viewModelScope.launch {
            try {
                val r = RetrofitInstance.api.getLed(1).percent
                val g = RetrofitInstance.api.getLed(2).percent
                val b = RetrofitInstance.api.getLed(3).percent

                _uiState.value = RgbUiState.Ready(r, g, b)

            } catch (e: Exception) {
                _uiState.value = RgbUiState.Error("Erro ao carregar estado inicial")
            }
        }
    }

    fun updateRgb(r: Int, g: Int, b: Int) {
        _uiState.value = RgbUiState.Ready(r, g, b)
    }

    fun commitRgb(r: Int, g: Int, b: Int) {
        viewModelScope.launch {
            try {
                RetrofitInstance.api.setLed(1, LedRequest(r))
                RetrofitInstance.api.setLed(2, LedRequest(g))
                RetrofitInstance.api.setLed(3, LedRequest(b))
            } catch (e: Exception) {
                _uiState.value = RgbUiState.Error("Erro ao enviar valores RGB")
            }
        }
    }

}


