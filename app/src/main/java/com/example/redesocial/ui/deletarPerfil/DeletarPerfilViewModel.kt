package com.example.redesocial.ui.deletarPerfil

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DeletarPerfilViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is deletarPerfil Fragment"
    }
    val text: LiveData<String> = _text
}