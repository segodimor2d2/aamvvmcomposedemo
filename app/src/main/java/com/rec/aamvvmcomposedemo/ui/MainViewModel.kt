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

    fun setLed(ledId: Int, percent: Int) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.setLed(
                    id = ledId,
                    request = LedRequest(percent)
                )
                _ledState.value = response
            } catch (e: Exception) {
                android.util.Log.e("VM", "Erro na chamada", e)
                e.printStackTrace()
            }
        }
    }

}


