package com.example.redesocial.ui.editarPerfil

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EditarPerfilViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is editarPerfil Fragment"
    }
    val text: LiveData<String> = _text
}