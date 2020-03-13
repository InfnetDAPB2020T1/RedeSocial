package com.example.redesocial.ui.pesquisa

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PesquisaViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Pesquisa"
    }
    val text: LiveData<String> = _text
}