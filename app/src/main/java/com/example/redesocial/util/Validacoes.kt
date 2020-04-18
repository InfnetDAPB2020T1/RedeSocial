package com.example.redesocial.util

class Validacoes {

    companion object {
        fun verificaUsuario(username : String, password : String) : Boolean
        {
            var usuarioRegistrado = "user"
            var senhaRegistrada = "senha"

            if(username.trim() == usuarioRegistrado && password.trim() == senhaRegistrada)
                return true;

            return false
        }

        fun verificaCampoPreenchido(param : String?) : Boolean
        {
            if(param != null && param.trim().isNotEmpty())
                return true

            return false
        }
    }

}