package com.bemteviinfnet.redesocial.models.dtos

import com.google.gson.annotations.SerializedName

data class ConviteDto(
    @SerializedName("id")
    var id : Int,
    @SerializedName("convidante")
    var convidante : PerfilDto,
    @SerializedName("convidado")
    var convidado: PerfilDto,
    @SerializedName("status")
    var status: String
)