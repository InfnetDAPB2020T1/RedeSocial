package com.bemteviinfnet.redesocial.models

class Convite(id : Int, convidante : Perfil, convidado: Perfil, status: String){
    var id : Int = id
    var convidante : Perfil = convidante
    var convidado: Perfil = convidado
    var status: String = status
}