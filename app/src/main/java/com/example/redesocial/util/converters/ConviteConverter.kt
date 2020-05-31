package com.example.redesocial.util.converters

import com.example.redesocial.models.Convite
import com.example.redesocial.models.dtos.ConviteDto

class ConviteConverter {

    companion object {
        private var conversor: ConviteConverter? = null

        fun getInstance(): ConviteConverter {
            if (conversor == null)
                conversor = ConviteConverter()

            return conversor as ConviteConverter
        }
    }

    fun converterDtoParaConvite(conviteDto: ConviteDto): Convite {
        return Convite(
            conviteDto.id,
            PerfilConverter.getInstance().converterDtoParaPerfil(conviteDto.convidante),
            PerfilConverter.getInstance().converterDtoParaPerfil(conviteDto.convidado),
            conviteDto.status)
    }

    fun converterConviteParaDto(convite: Convite): ConviteDto {
        return ConviteDto(convite.id,
            PerfilConverter.getInstance().converterPerfilParaDto(convite.convidante),
            PerfilConverter.getInstance().converterPerfilParaDto(convite.convidado),
            convite.status)
    }
}