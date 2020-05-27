package com.example.redesocial.services

import android.app.Activity
import android.widget.Toast
import com.example.redesocial.models.Convite
import com.example.redesocial.models.Perfil
import com.example.redesocial.models.dtos.ConviteDto
import com.example.redesocial.models.dtos.PerfilDto
import com.example.redesocial.repository.ConectorApi
import com.example.redesocial.repository.EndpointsApi
import com.example.redesocial.util.converters.ConviteConverter
import com.example.redesocial.util.converters.PerfilConverter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OperacoesConviteService {

    companion object {
        private lateinit var operacoes: OperacoesConviteService

        fun getInstance() : OperacoesConviteService{
            if(operacoes == null)
                operacoes = OperacoesConviteService()

            return operacoes
        }
    }

    fun buscarMeusConvites(activity: Activity, perfilId : Int) : List<Convite>?{
        val retrofitClient = ConectorApi
            .getRetrofitInstance("https://bemtevisocialnetworkapi.azurewebsites.net")

        val endpoint = retrofitClient.create(EndpointsApi::class.java)
        val callback = endpoint.buscarMeusConvites(perfilId)
        var convites = mutableListOf<Convite>()

        callback.enqueue(object : Callback<List<ConviteDto>> {
            override fun onFailure(call: Call<List<ConviteDto>>, t: Throwable) {
                Toast.makeText(activity, "Problema ao tentar acessar os dados!", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<ConviteDto>>, response: Response<List<ConviteDto>>) {
                if(response.isSuccessful && !response.body().isNullOrEmpty()) {
                    response.body()!!.forEach {
                        convites.add(ConviteConverter.getInstance().converterDtoParaConvite(it))
                    }
                }
            }
        })

        return convites
    }

    fun buscarConvitesRecebidos(activity: Activity, perfilId : Int) : List<Convite>?{
        val retrofitClient = ConectorApi
            .getRetrofitInstance("https://bemtevisocialnetworkapi.azurewebsites.net")

        val endpoint = retrofitClient.create(EndpointsApi::class.java)
        val callback = endpoint.buscarConvitesRecebidos(perfilId)
        var convites = mutableListOf<Convite>()

        callback.enqueue(object : Callback<List<ConviteDto>> {
            override fun onFailure(call: Call<List<ConviteDto>>, t: Throwable) {
                Toast.makeText(activity, "Problema ao tentar acessar os dados!", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<ConviteDto>>, response: Response<List<ConviteDto>>) {
                if(response.isSuccessful && !response.body().isNullOrEmpty()) {
                    response.body()!!.forEach {
                        convites.add(ConviteConverter.getInstance().converterDtoParaConvite(it))
                    }
                }
            }
        })

        return convites
    }

    fun aceitarConvite(activity: Activity, perfilId : Int, idConvidante : Int) : Boolean{
        val retrofitClient = ConectorApi
            .getRetrofitInstance("https://bemtevisocialnetworkapi.azurewebsites.net")

        val endpoint = retrofitClient.create(EndpointsApi::class.java)
        val callback = endpoint.aceitarConvite(perfilId, idConvidante)
        var sucesso = false

        callback.enqueue(object : Callback<Boolean> {
            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                Toast.makeText(activity, "Problema ao tentar acessar os dados!", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                if(response.isSuccessful && response.body() != null) {
                    sucesso = true
                }
            }
        })

        return sucesso
    }

    fun bloquearUsuario(activity: Activity, perfilId : Int, idUsuario : Int) : Boolean{
        val retrofitClient = ConectorApi
            .getRetrofitInstance("https://bemtevisocialnetworkapi.azurewebsites.net")

        val endpoint = retrofitClient.create(EndpointsApi::class.java)
        val callback = endpoint.bloquearUsuario(perfilId, idUsuario)
        var sucesso = false

        callback.enqueue(object : Callback<Boolean> {
            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                Toast.makeText(activity, "Problema ao tentar acessar os dados!", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                if(response.isSuccessful && response.body() != null) {
                    sucesso = true
                }
            }
        })

        return sucesso
    }

    fun enviarConvite(activity: Activity, perfilId : Int, idConvidado : Int) : Boolean{
        val retrofitClient = ConectorApi
            .getRetrofitInstance("https://bemtevisocialnetworkapi.azurewebsites.net")

        val endpoint = retrofitClient.create(EndpointsApi::class.java)
        val callback = endpoint.enviarConvite(perfilId,idConvidado)
        var sucesso = false

        callback.enqueue(object : Callback<Boolean> {
            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                Toast.makeText(activity, "Problema ao tentar acessar os dados!", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                if(response.isSuccessful && response.body() != null) {
                    sucesso = true
                }
            }
        })

        return sucesso
    }

    fun buscarSugestoesAmizade(activity: Activity, perfilId : Int) : List<Perfil>?{
        val retrofitClient = ConectorApi
            .getRetrofitInstance("https://bemtevisocialnetworkapi.azurewebsites.net")

        val endpoint = retrofitClient.create(EndpointsApi::class.java)
        val callback = endpoint.buscarSugestoesAmizade(perfilId)
        var sugestoes = mutableListOf<Perfil>()

        callback.enqueue(object : Callback<List<PerfilDto>> {
            override fun onFailure(call: Call<List<PerfilDto>>, t: Throwable) {
                Toast.makeText(activity, "Problema ao tentar acessar os dados!", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<PerfilDto>>, response: Response<List<PerfilDto>>) {
                if(response.isSuccessful && !response.body().isNullOrEmpty()) {
                    response.body()!!.forEach {
                        sugestoes.add(PerfilConverter.getInstance().converterDtoParaPerfil(it))
                    }
                }
            }
        })

        return sugestoes
    }

    fun buscarAmigos(activity: Activity, perfilId : Int) : List<Perfil>?{
        val retrofitClient = ConectorApi
            .getRetrofitInstance("https://bemtevisocialnetworkapi.azurewebsites.net")

        val endpoint = retrofitClient.create(EndpointsApi::class.java)
        val callback = endpoint.buscarAmigos(perfilId)
        var amigos = mutableListOf<Perfil>()

        callback.enqueue(object : Callback<List<PerfilDto>> {
            override fun onFailure(call: Call<List<PerfilDto>>, t: Throwable) {
                Toast.makeText(activity, "Problema ao tentar acessar os dados!", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<PerfilDto>>, response: Response<List<PerfilDto>>) {
                if(response.isSuccessful && !response.body().isNullOrEmpty()) {
                    response.body()!!.forEach {
                        amigos.add(PerfilConverter.getInstance().converterDtoParaPerfil(it))
                    }
                }
            }
        })

        return amigos
    }

}