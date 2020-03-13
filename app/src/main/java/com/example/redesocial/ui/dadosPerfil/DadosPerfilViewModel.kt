package com.example.redesocial.ui.dadosPerfil

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DadosPerfilViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is dadosPerfil Fragment"
    }
    val text: LiveData<String> = _text
}