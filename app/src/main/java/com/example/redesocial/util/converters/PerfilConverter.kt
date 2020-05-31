package com.example.redesocial.util.converters

import com.example.redesocial.models.Perfil
import com.example.redesocial.models.dtos.PerfilDto

class PerfilConverter{

    companion object{
        private var conversor : PerfilConverter? = null

        fun getInstance() : PerfilConverter{
            if(conversor == null)
                conversor = PerfilConverter()

            return conversor as PerfilConverter
        }

    }

    fun converterDtoParaPerfil(perfilDto: PerfilDto) : Perfil{
        return Perfil(perfilDto.id,perfilDto.serieGerado,perfilDto.nome,perfilDto.dataNascimento,
            perfilDto.email,perfilDto.sobre,perfilDto.foto)
    }

    fun converterPerfilParaDto(perfil: Perfil) : PerfilDto{
        return PerfilDto(perfil.id,perfil.serieGerado,perfil.nome,perfil.dataNascimento,
            perfil.email,perfil.sobre,perfil.foto)
    }
}