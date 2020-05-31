package com.example.redesocial.viewmodel

import androidx.lifecycle.ViewModel

class PesquisaViewModel : ViewModel(){
    var username : String? = null

    fun setNome(nome : String) {
        username = nome
    }
}