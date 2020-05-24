package com.example.redesocial.util.converters

import com.example.redesocial.models.Mensagem
import com.example.redesocial.models.dtos.MensagemDto

class MensagemConverter {

    companion object {
        private lateinit var conversor: MensagemConverter

        fun getInstance(): MensagemConverter {
            if (conversor == null)
                conversor = MensagemConverter()

            return conversor
        }
    }

    fun converterDtoParaMensagem(mensagemDto: MensagemDto): Mensagem {
        return Mensagem(
            mensagemDto.id,
            mensagemDto.conteudo,
            PerfilConverter.getInstance().converterDtoParaPerfil(mensagemDto.autor)
        )
    }

    fun converterMensagemParaDto(mensagem: Mensagem): MensagemDto {
        return MensagemDto(
            mensagem.id,
            mensagem.conteudo,
            PerfilConverter.getInstance().converterPerfilParaDto(mensagem.autor)
        )
    }
}