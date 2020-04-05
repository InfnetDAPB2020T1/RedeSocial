package com.example.redesocial.ui.deletarPerfil

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DeletarPerfilViewModel : ViewModel() {

    private val _nome = MutableLiveData<String>().apply {
        value = "Pedro"
    }
    val nome: LiveData<String> = _nome

    private val _camponome = MutableLiveData<String>().apply {
        value = "Nome:"
    }
    val camponome: LiveData<String> = _camponome

    private val _email = MutableLiveData<String>().apply {
        value = "contato.coutpe@gmail.com"
    }
    val email: LiveData<String> = _email

    private val _campoemail = MutableLiveData<String>().apply {
        value = "Email:"
    }
    val campoemail: LiveData<String> = _campoemail

    private val _datanascimento = MutableLiveData<String>().apply {
        value = "24/05/1995"
    }
    val datanascimento: LiveData<String> = _datanascimento

    private val _campodatanascimento = MutableLiveData<String>().apply {
        value = "Data de Nascimento:"
    }
    val campodatanascimento: LiveData<String> = _campodatanascimento

    private val _sobre = MutableLiveData<String>().apply {
        value = "Aluno de Análise e desenvolvimento de sistemas no instituto Infnet"
    }
    val sobre: LiveData<String> = _sobre

    private val _camposobre = MutableLiveData<String>().apply {
        value = "Sobre:"
    }
    val camposobre: LiveData<String> = _camposobre
}