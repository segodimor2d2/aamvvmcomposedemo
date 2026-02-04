package com.rec.aamvvmcomposedemo.ui

sealed class LedUiState {

    object Idle : LedUiState()

    data class Loading(
        val ledId: Int,
        val targetPercent: Int
    ) : LedUiState()

    data class Success(
        val ledId: Int,
        val percent: Int,
        val duty: Int
    ) : LedUiState()

    data class Error(
        val message: String
    ) : LedUiState()
}
