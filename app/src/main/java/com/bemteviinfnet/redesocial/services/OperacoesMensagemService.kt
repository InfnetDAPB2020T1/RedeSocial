package com.bemteviinfnet.redesocial.services

import android.app.Activity
import com.bemteviinfnet.redesocial.models.Mensagem
import com.bemteviinfnet.redesocial.repository.ConectorApi
import com.bemteviinfnet.redesocial.repository.EndpointsApi
import com.bemteviinfnet.redesocial.util.converters.MensagemConverter

class OperacoesMensagemService (){

    companion object{
        private var operacoes : OperacoesMensagemService? = null

        fun getInstance() : OperacoesMensagemService{
            if(operacoes == null)
                operacoes = OperacoesMensagemService()

            return operacoes as OperacoesMensagemService
        }
    }

    fun buscarMensagem(activity: Activity, mensagemId : Int) : Mensagem?{
        val retrofitClient = ConectorApi
            .getRetrofitInstance("https://bemtevisocialnetworkapi.azurewebsites.net")

        val endpoint = retrofitClient.create(EndpointsApi::class.java)
        val callback = endpoint.buscarMensagem(mensagemId)
        var response = callback.execute()
        var mensagem : Mensagem? = null

        if(response.isSuccessful)
            return MensagemConverter.getInstance().converterDtoParaMensagem(response.body()!!)

        return null
    }

    fun buscarTimeline(activity: Activity, perfilId : Int) : List<Mensagem>?{
        val retrofitClient = ConectorApi
            .getRetrofitInstance("https://bemtevisocialnetworkapi.azurewebsites.net")

        val endpoint = retrofitClient.create(EndpointsApi::class.java)
        val callback = endpoint.buscarTimeline(perfilId)
        var response = callback.execute()
        var mensagens = mutableListOf<Mensagem>()

        if(response.isSuccessful && response.body() != null)
        {
            response.body()!!.forEach {
                mensagens.add(MensagemConverter.getInstance()
                        .converterDtoParaMensagem(it))
                }
        }

        if(mensagens.size == 0)
            return null

        return mensagens
    }

    fun criarMensagem(activity: Activity, perfilId : Int, mensagem: Mensagem) : Boolean{
        val retrofitClient = ConectorApi
            .getRetrofitInstance("https://bemtevisocialnetworkapi.azurewebsites.net")

        val endpoint = retrofitClient.create(EndpointsApi::class.java)
        val callback = endpoint.criarMensagem(perfilId, MensagemConverter.getInstance().converterMensagemParaDto(mensagem))
        var response = callback.execute()

        if(response.isSuccessful)
            return true

        return false
    }

    fun deletarMensagem(activity: Activity, mensagemId : Int) : Boolean{
        val retrofitClient = ConectorApi
            .getRetrofitInstance("https://bemtevisocialnetworkapi.azurewebsites.net")

        val endpoint = retrofitClient.create(EndpointsApi::class.java)
        val callback = endpoint.deletarMensagem(mensagemId)
        var response = callback.execute()

        if(response.isSuccessful)
            return true

        return false
    }



}