package com.example.redesocial.ui.novaMensagem

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NovaMensagemViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Nova Mensagem"
    }
    val text: LiveData<String> = _text
}