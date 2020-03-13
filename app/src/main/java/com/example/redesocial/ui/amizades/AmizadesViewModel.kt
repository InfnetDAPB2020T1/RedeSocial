package com.example.redesocial.ui.amizades

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AmizadesPerfilViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is amizades Fragment"
    }
    val text: LiveData<String> = _text
}