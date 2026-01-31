package com.rec.aamvvmcomposedemo.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rec.aamvvmcomposedemo.data.model.Post
import com.rec.aamvvmcomposedemo.data.model.RetrofitInstance
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _post = MutableStateFlow<Post?>(null)
    val post = _post.asStateFlow()

    fun loadPost() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getPost()
                _post.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}




