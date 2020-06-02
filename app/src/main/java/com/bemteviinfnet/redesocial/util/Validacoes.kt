package com.bemteviinfnet.redesocial.util

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

        fun verificaEmail(email : String) : Boolean
        {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }

        fun verificaDadosCadastro(email: String,password: String) : String?
        {

            if(email.length < 10 || email.length > 70)
            {
                return "Email deve ter de 12 a 70 caracteres!"
            }
            if(password.length < 5 || password.length > 10)
            {
                return "Password deve ter de 5 a 10 caracteres!"
            }

            return null
        }
    }

}