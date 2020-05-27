package com.example.redesocial.models.dtos

import com.google.gson.annotations.SerializedName
import java.util.*

data class PerfilDto(
    @SerializedName("id")
    var id : Int?,
    @SerializedName("serieGerado")
    var serieGerado: String,
    @SerializedName("nome")
    var nome : String,
    @SerializedName("dataNascimento")
    var dataNascimento : String,
    @SerializedName("email")
    var email : String,
    @SerializedName("sobre")
    var sobre : String,
    @SerializedName("foto")
    var foto : String
)