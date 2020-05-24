package com.example.redesocial.models.dtos

import com.google.gson.annotations.SerializedName
import java.util.*

data class PerfilDto(
    @SerializedName("id")
    var id : Int?,
    @SerializedName("seriegerado")
    var serieGerado: String,
    @SerializedName("nome")
    var nome : String,
    @SerializedName("datanascimento")
    var dataNascimento : Date,
    @SerializedName("email")
    var email : String,
    @SerializedName("sobre")
    var sobre : String,
    @SerializedName("foto")
    var foto : String
)