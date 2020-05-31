package com.example.redesocial.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.redesocial.models.Perfil

class PerfilViewModel : ViewModel(){
    var perfilAtual : Perfil? = null
    var modificado = MutableLiveData<Int>().apply { value = 0 }

    fun setPerfil(perfil: Perfil)
    {
        perfilAtual = perfil
    }

    fun setModificado()
    {
        if(modificado.value == 0)
            modificado.value = 1
        else
            if(modificado.value == 1)
                modificado.value = 0
    }
}