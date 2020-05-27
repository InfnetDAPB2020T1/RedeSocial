package com.example.redesocial.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UsuarioCriadoViewModel : ViewModel() {
    var uid = ""
    var email = ""
    var password = ""
    var usuarioCriado = MutableLiveData<Boolean>().apply { value = false }
}