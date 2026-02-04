package com.rec.aamvvmcomposedemo.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rec.aamvvmcomposedemo.data.model.LedRequest
import com.rec.aamvvmcomposedemo.data.remote.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<LedUiState>(LedUiState.Idle)
    val uiState = _uiState.asStateFlow()


    fun setLed(ledId: Int, percent: Int) {

        _uiState.value = LedUiState.Loading(
            ledId = ledId,
            targetPercent = percent
        )

        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.setLed(
                    id = ledId,
                    request = LedRequest(percent)
                )

                _uiState.value = LedUiState.Success(
                    ledId = ledId,
                    percent = response.percent,
                    duty = response.duty
                )

            } catch (e: Exception) {
                _uiState.value = LedUiState.Error(
                    message = "Erro ao controlar LED $ledId"
                )
            }
        }

    }

}


