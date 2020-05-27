package com.example.redesocial.services

import android.app.Activity
import android.widget.Toast
import com.example.redesocial.models.Mensagem
import com.example.redesocial.models.dtos.MensagemDto
import com.example.redesocial.repository.ConectorApi
import com.example.redesocial.repository.EndpointsApi
import com.example.redesocial.util.converters.MensagemConverter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OperacoesMensagemService (){

    companion object{
        private lateinit var operacoes : OperacoesMensagemService

        fun getInstance() : OperacoesMensagemService{
            if(operacoes == null)
                operacoes = OperacoesMensagemService()

            return  operacoes
        }
    }

    fun buscarMensagem(activity: Activity, mensagemId : Int) : Mensagem?{
        val retrofitClient = ConectorApi
            .getRetrofitInstance("https://bemtevisocialnetworkapi.azurewebsites.net")

        val endpoint = retrofitClient.create(EndpointsApi::class.java)
        val callback = endpoint.buscarMensagem(mensagemId)
        var mensagem : Mensagem? = null

        callback.enqueue(object : Callback<MensagemDto> {
            override fun onFailure(call: Call<MensagemDto>, t: Throwable) {
                Toast.makeText(activity, "Problema ao tentar acessar os dados!", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<MensagemDto>, response: Response<MensagemDto>) {
                if(response.isSuccessful && response.body() != null)
                {
                    mensagem = MensagemConverter.getInstance().converterDtoParaMensagem(response.body()!!)
                }
            }
        })

        return mensagem
    }

    fun buscarTimeline(activity: Activity, perfilId : Int) : List<Mensagem>?{
        val retrofitClient = ConectorApi
            .getRetrofitInstance("https://bemtevisocialnetworkapi.azurewebsites.net")

        val endpoint = retrofitClient.create(EndpointsApi::class.java)
        val callback = endpoint.buscarTimeline(perfilId)
        var mensagens = mutableListOf<Mensagem>()

        callback.enqueue(object : Callback<List<MensagemDto>> {
            override fun onFailure(call: Call<List<MensagemDto>>, t: Throwable) {
                Toast.makeText(activity, "Problema ao tentar acessar os dados!", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<MensagemDto>>, response: Response<List<MensagemDto>>) {
                if(response.isSuccessful && !response.body().isNullOrEmpty()) {
                    response.body()!!.forEach {
                        mensagens.add(MensagemConverter.getInstance()
                            .converterDtoParaMensagem(it))
                    }
                }
            }
        })

        return mensagens
    }

    fun criarMensagem(activity: Activity, perfilId : Int, mensagem: Mensagem) : Boolean{
        val retrofitClient = ConectorApi
            .getRetrofitInstance("https://bemtevisocialnetworkapi.azurewebsites.net")

        val endpoint = retrofitClient.create(EndpointsApi::class.java)
        val callback = endpoint.criarMensagem(perfilId, MensagemConverter.getInstance().converterMensagemParaDto(mensagem))
        var sucesso = false

        callback.enqueue(object : Callback<MensagemDto> {
            override fun onFailure(call: Call<MensagemDto>, t: Throwable) {
                Toast.makeText(activity, "Problema ao tentar acessar os dados!", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<MensagemDto>, response: Response<MensagemDto>) {
                if(response.isSuccessful && response.body() != null)
                {
                    sucesso = true
                }
            }
        })

        return sucesso
    }

    fun deletarMensagem(activity: Activity, mensagemId : Int) : Boolean{
        val retrofitClient = ConectorApi
            .getRetrofitInstance("https://bemtevisocialnetworkapi.azurewebsites.net")

        val endpoint = retrofitClient.create(EndpointsApi::class.java)
        val callback = endpoint.deletarMensagem(mensagemId)
        var sucesso = false

        callback.enqueue(object : Callback<MensagemDto> {
            override fun onFailure(call: Call<MensagemDto>, t: Throwable) {
                Toast.makeText(activity, "Problema ao tentar acessar os dados!", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<MensagemDto>, response: Response<MensagemDto>) {
                if(response.isSuccessful && response.body() != null)
                {
                    sucesso = true
                }
            }
        })

        return sucesso
    }



}