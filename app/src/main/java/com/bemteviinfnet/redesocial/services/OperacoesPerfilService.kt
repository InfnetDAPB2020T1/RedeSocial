package com.bemteviinfnet.redesocial.services

import android.app.Activity
import android.widget.Toast
import com.bemteviinfnet.redesocial.models.Perfil
import com.bemteviinfnet.redesocial.models.dtos.PerfilDto
import com.bemteviinfnet.redesocial.repository.ConectorApi
import com.bemteviinfnet.redesocial.repository.EndpointsApi
import com.bemteviinfnet.redesocial.util.converters.PerfilConverter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OperacoesPerfilService {


    companion object{
        private var operacoes : OperacoesPerfilService? = null

        fun getInstance() : OperacoesPerfilService{
            if(operacoes == null)
                operacoes = OperacoesPerfilService()

            return operacoes as OperacoesPerfilService
        }

    }

    fun listarPerfis(activity: Activity) : List<Perfil>? {
        val retrofitClient = ConectorApi
            .getRetrofitInstance("https://bemtevisocialnetworkapi.azurewebsites.net")

        val endpoint = retrofitClient.create(EndpointsApi::class.java)
        val callback = endpoint.listarPerfis()
        var lista = mutableListOf<Perfil>()

        callback.enqueue(object : Callback<List<PerfilDto>> {
            override fun onFailure(call: Call<List<PerfilDto>>, t: Throwable) {
                Toast.makeText(activity, "Problema ao tentar listar perfis de usuário!", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<PerfilDto>>, response: Response<List<PerfilDto>>) {
                if(response.isSuccessful && !response.body().isNullOrEmpty())
                {
                    response.body()!!.forEach {
                        lista.add(PerfilConverter.getInstance().converterDtoParaPerfil(it))
                    }
                }

            }
        })

        return lista
    }

    fun buscarPerfil(activity: Activity, perfilId : Int) : Perfil?{
        val retrofitClient = ConectorApi
            .getRetrofitInstance("https://bemtevisocialnetworkapi.azurewebsites.net")

        val endpoint = retrofitClient.create(EndpointsApi::class.java)
        val callback = endpoint.buscarPerfil(perfilId)

        var resposta = callback.execute()

        if(resposta.isSuccessful)
        {
            var corpo = resposta.body()!!
            var id = corpo.id
            var email = corpo.email
            var nome = corpo.nome
            var serieGerado = corpo.serieGerado
            var dataNasc = corpo.dataNascimento
            var sobre = corpo.sobre
            var foto = corpo.foto

            return Perfil(id,serieGerado,nome,dataNasc,email,sobre,foto)
        }

        return null
    }

    fun criarPerfil(activity: Activity, perfil: Perfil) : Boolean{
        val retrofitClient = ConectorApi
            .getRetrofitInstance("https://bemtevisocialnetworkapi.azurewebsites.net")

        val endpoint = retrofitClient.create(EndpointsApi::class.java)
        val callback = endpoint.criarPerfil(PerfilConverter.getInstance().converterPerfilParaDto(perfil))
        var resposta = callback.execute()

        if(resposta.isSuccessful)
            return true;

        return false;
    }

    fun deletarPerfil(activity: Activity, perfilId: Int) : Boolean{
        val retrofitClient = ConectorApi
            .getRetrofitInstance("https://bemtevisocialnetworkapi.azurewebsites.net")

        val endpoint = retrofitClient.create(EndpointsApi::class.java)
        val callback = endpoint.deletarPerfil(perfilId)
        var resposta = callback.execute()

        if(resposta.isSuccessful)
            return true;

        return false;

    }

    fun atualizarPerfil(activity: Activity, perfil: Perfil) : Boolean{
        val retrofitClient = ConectorApi
            .getRetrofitInstance("https://bemtevisocialnetworkapi.azurewebsites.net")

        val endpoint = retrofitClient.create(EndpointsApi::class.java)
        val callback = endpoint.atualizarPerfil(perfil.id!!, PerfilConverter.getInstance().converterPerfilParaDto(perfil))
        var resposta = callback.execute()

        if(resposta.isSuccessful && resposta.body() != null)
            return true

        return false
    }

    fun buscarPerfilPorSerie(activity: Activity, serie : String) : Perfil?{
        val retrofitClient = ConectorApi
            .getRetrofitInstance("https://bemtevisocialnetworkapi.azurewebsites.net")

        val endpoint = retrofitClient.create(EndpointsApi::class.java)
        val callback = endpoint.buscarPerfilPorSerie(serie)
        var resposta = callback.execute()

        if(resposta.isSuccessful)
        {
            var corpo = resposta.body()!!
            var id = corpo.id
            var email = corpo.email
            var nome = corpo.nome
            var serieGerado = corpo.serieGerado
            var dataNasc = corpo.dataNascimento
            var sobre = corpo.sobre
            var foto = corpo.foto

            return Perfil(id,serieGerado,nome,dataNasc,email,sobre,foto)
        }

        return null
    }

    fun pesquisarUsuariosPorNome(activity: Activity, id : Int ,nome : String) : List<Perfil>? {
        val retrofitClient = ConectorApi
            .getRetrofitInstance("https://bemtevisocialnetworkapi.azurewebsites.net")

        val endpoint = retrofitClient.create(EndpointsApi::class.java)
        val callback = endpoint.pesquisarUsuarios(id, nome)

        var resultado = callback.execute()
        var corpo : List<PerfilDto>
        var lista = mutableListOf<Perfil>()

        if(resultado .isSuccessful && resultado.body() != null)
        {
            corpo = resultado.body()!!
            for(perfil in corpo)
            {
                lista.add(PerfilConverter.getInstance().converterDtoParaPerfil(perfil))
            }
        }

        return lista
    }

    fun verificarUsernameLivre(activity: Activity, nome : String) : Boolean{
        val retrofitClient = ConectorApi
            .getRetrofitInstance("https://bemtevisocialnetworkapi.azurewebsites.net")

        val endpoint = retrofitClient.create(EndpointsApi::class.java)
        val callback = endpoint.pesquisarUsernameLivre(nome)
        var resultado = callback.execute()

        if(resultado.isSuccessful && resultado.body() == true)
            return true

        return false
    }

}