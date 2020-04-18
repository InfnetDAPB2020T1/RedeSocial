package com.example.redesocial

import com.example.redesocial.util.Validacoes
import org.junit.Assert.*
import org.junit.Test

class ValidacoesTest {

    @Test
    fun verificar_campo_preenchido()
    {
        var campoValido = "teste"
        var campoInvalido = "  "
        var resposta : Boolean

        resposta = Validacoes.verificaCampoPreenchido(campoValido)
        assertTrue(resposta)

        resposta = Validacoes.verificaCampoPreenchido(campoInvalido)
        assertFalse(resposta)

        resposta = Validacoes.verificaCampoPreenchido(null)
        assertFalse(resposta)
    }

    @Test
    fun verificar_usuario()
    {
        var userValido = "user"
        var userInvalido = "userTeste"
        var senhaValida = "senha"
        var senhaInvalida = "senhaTeste"
        var resposta : Boolean

        resposta = Validacoes.verificaUsuario(userValido,senhaValida)
        assertTrue(resposta)

        resposta = Validacoes.verificaUsuario(userValido,senhaInvalida)
        assertFalse(resposta)

        resposta = Validacoes.verificaUsuario(userInvalido,senhaValida)
        assertFalse(resposta)

        resposta = Validacoes.verificaUsuario(userInvalido,senhaInvalida)
        assertFalse(resposta)

    }
}