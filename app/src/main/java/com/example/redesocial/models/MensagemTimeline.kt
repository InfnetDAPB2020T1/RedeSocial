package com.example.redesocial.models

class MensagemTimeline {
    var username: String
    var foto: Int
    var textoMensagem : String

    constructor(username: String, imagem: Int, textoMensagem : String) {
        this.username = username
        this.foto = imagem
        this.textoMensagem = textoMensagem
    }
}