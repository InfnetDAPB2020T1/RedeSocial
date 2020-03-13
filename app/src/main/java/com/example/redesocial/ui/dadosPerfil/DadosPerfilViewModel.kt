package com.example.redesocial.ui.dadosPerfil

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DadosPerfilViewModel : ViewModel() {

    private val _email = MutableLiveData<String>().apply {
        value = "cecilia.santos@al.infnet.edu.br"
    }
    val email: LiveData<String> = _email
    private val _campoEmail = MutableLiveData<String>().apply {
        value = "Email: "
    }
    val campoEmail: LiveData<String> = _campoEmail



}