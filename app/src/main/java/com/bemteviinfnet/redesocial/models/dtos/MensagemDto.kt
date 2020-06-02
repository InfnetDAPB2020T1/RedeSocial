package com.bemteviinfnet.redesocial.models.dtos

import com.google.gson.annotations.SerializedName

data class MensagemDto(
    @SerializedName("id")
    var id : Int,
    @SerializedName("conteudo")
    var conteudo : String,
    @SerializedName("autor")
    var autor: PerfilDto
)