package com.rec.aamvvmcomposedemo.ui

sealed class RgbUiState {

    object Loading : RgbUiState()

    data class Ready(
        val r: Int,
        val g: Int,
        val b: Int
    ) : RgbUiState()

    data class Error(
        val message: String
    ) : RgbUiState()
}
