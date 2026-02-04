package com.rec.aamvvmcomposedemo.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rec.aamvvmcomposedemo.data.model.LedRequest
import com.rec.aamvvmcomposedemo.data.model.LedResponse
import com.rec.aamvvmcomposedemo.data.remote.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _ledState = MutableStateFlow<LedResponse?>(null)
    val ledState = _ledState.asStateFlow()

    fun setLed(percent: Int) {
        android.util.Log.d("VM", "loadPost() chamado")
        viewModelScope.launch {
            try {
                android.util.Log.d("VM", "Antes da chamada HTTP")
                val response = RetrofitInstance.api.setLed(
                    id = 1,
                    request = LedRequest(percent)
                )
                android.util.Log.d("VM", "Resposta recebida: $response")
                _ledState.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}


