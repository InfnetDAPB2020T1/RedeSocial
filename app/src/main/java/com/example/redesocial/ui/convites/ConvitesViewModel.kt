package com.example.redesocial.ui.convites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ConvitesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is convites Fragment"
    }
    val text: LiveData<String> = _text
}