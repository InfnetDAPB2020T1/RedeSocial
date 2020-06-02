package com.bemteviinfnet.redesocial.services

import android.app.Activity
import com.bemteviinfnet.redesocial.models.Convite
import com.bemteviinfnet.redesocial.models.Perfil
import com.bemteviinfnet.redesocial.repository.ConectorApi
import com.bemteviinfnet.redesocial.repository.EndpointsApi
import com.bemteviinfnet.redesocial.util.converters.ConviteConverter
import com.bemteviinfnet.redesocial.util.converters.PerfilConverter

class OperacoesConviteService {

    companion object {
        private var operacoes: OperacoesConviteService? = null

        fun getInstance() : OperacoesConviteService{
            if(operacoes == null)
                operacoes = OperacoesConviteService()

            return operacoes as OperacoesConviteService
        }
    }

    fun buscarMeusConvites(activity: Activity, perfilId : Int) : List<Convite>?{
        val retrofitClient = ConectorApi
            .getRetrofitInstance("https://bemtevisocialnetworkapi.azurewebsites.net")

        val endpoint = retrofitClient.create(EndpointsApi::class.java)
        val callback = endpoint.buscarMeusConvites(perfilId)
        var response = callback.execute()
        var convites = mutableListOf<Convite>()

        if(response.isSuccessful && response.body() != null)
        {
            response.body()!!.forEach {
                convites.add(ConviteConverter.getInstance().converterDtoParaConvite(it))
            }
        }

        if(convites.size == 0)
            return null

        return convites
    }

    fun buscarConvitesRecebidos(activity: Activity, perfilId : Int) : List<Convite>?{
        val retrofitClient = ConectorApi
            .getRetrofitInstance("https://bemtevisocialnetworkapi.azurewebsites.net")

        val endpoint = retrofitClient.create(EndpointsApi::class.java)
        val callback = endpoint.buscarConvitesRecebidos(perfilId)
        var response = callback.execute()
        var convites = mutableListOf<Convite>()

        if(response.isSuccessful && response.body() != null)
        {
            response.body()!!.forEach {
                convites.add(ConviteConverter.getInstance().converterDtoParaConvite(it))
            }
        }

        if(convites.size == 0)
            return null

        return convites
    }

    fun aceitarConvite(activity: Activity, perfilId : Int, idConvidante : Int) : Boolean{
        val retrofitClient = ConectorApi
            .getRetrofitInstance("https://bemtevisocialnetworkapi.azurewebsites.net")

        val endpoint = retrofitClient.create(EndpointsApi::class.java)
        val callback = endpoint.aceitarConvite(perfilId, idConvidante)
        var response = callback.execute()

        if(response.isSuccessful)
            return true

        return false
    }

    fun bloquearUsuario(activity: Activity, perfilId : Int, idUsuario : Int) : Boolean{
        val retrofitClient = ConectorApi
            .getRetrofitInstance("https://bemtevisocialnetworkapi.azurewebsites.net")

        val endpoint = retrofitClient.create(EndpointsApi::class.java)
        val callback = endpoint.bloquearUsuario(perfilId, idUsuario)
        var response = callback.execute()

        if(response.isSuccessful)
            return true

        return false
    }

    fun enviarConvite(activity: Activity, perfilId : Int, idConvidado : Int) : Boolean{
        val retrofitClient = ConectorApi
            .getRetrofitInstance("https://bemtevisocialnetworkapi.azurewebsites.net")

        val endpoint = retrofitClient.create(EndpointsApi::class.java)
        val callback = endpoint.enviarConvite(perfilId,idConvidado)
        var response = callback.execute()

        if(response.isSuccessful)
            return true

        return false
    }

    fun buscarSugestoesAmizade(activity: Activity, perfilId : Int) : List<Perfil>?{
        val retrofitClient = ConectorApi
            .getRetrofitInstance("https://bemtevisocialnetworkapi.azurewebsites.net")

        val endpoint = retrofitClient.create(EndpointsApi::class.java)
        val callback = endpoint.buscarSugestoesAmizade(perfilId)
        var response = callback.execute()
        var sugestoes = mutableListOf<Perfil>()

        if(response.isSuccessful && response.body() != null)
        {
            response.body()!!.forEach {
                sugestoes.add(PerfilConverter.getInstance().converterDtoParaPerfil(it))
            }
        }

        if(sugestoes.size == 0)
            return null

        return sugestoes
    }

    fun buscarAmigos(activity: Activity, perfilId : Int) : List<Perfil>?{
        val retrofitClient = ConectorApi
            .getRetrofitInstance("https://bemtevisocialnetworkapi.azurewebsites.net")

        val endpoint = retrofitClient.create(EndpointsApi::class.java)
        val callback = endpoint.buscarAmigos(perfilId)
        var response = callback.execute()
        var amigos = mutableListOf<Perfil>()

        if(response.isSuccessful && response.body() != null)
        {
            response.body()!!.forEach {
                amigos.add(PerfilConverter.getInstance().converterDtoParaPerfil(it))
            }
        }

        if(amigos.size == 0)
            return null

        return amigos
    }

}