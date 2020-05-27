package com.example.redesocial.services

import android.app.Activity
import android.widget.Toast
import com.example.redesocial.models.Perfil
import com.example.redesocial.models.dtos.PerfilDto
import com.example.redesocial.repository.ConectorApi
import com.example.redesocial.repository.EndpointsApi
import com.example.redesocial.util.converters.PerfilConverter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Console

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
        //var perfil : Perfil? = null

        var corpo = callback.execute().body()!!
        var id = corpo.id
        var email = corpo.email
        var nome = corpo.nome
        var serieGerado = corpo.serieGerado
        var dataNasc = corpo.dataNascimento
        var sobre = corpo.sobre
        var foto = corpo.foto

        var perfil = Perfil(id,serieGerado,nome,dataNasc,email,sobre,foto)

        /*callback.enqueue(object : Callback<PerfilDto> {
            override fun onFailure(call: Call<PerfilDto>, t: Throwable) {
                Toast.makeText(activity, "Problema ao tentar acessar os dados!", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<PerfilDto>, response: Response<PerfilDto>) {
                if(response.isSuccessful)
                {
                    var id = response.body()!!.id
                    var email = response.body()!!.email
                    var nome = response.body()!!.nome
                    var serieGerado = response.body()!!.serieGerado
                    var dataNasc = response.body()!!.dataNascimento
                    var sobre = response.body()!!.sobre
                    var foto = response.body()!!.foto



                    /*perfil = PerfilConverter.getInstance().converterDtoParaPerfil(response.body()!! as PerfilDto)
                    var i = 0*/

                }
            }
        })*/

        return perfil
    }

    fun criarPerfil(activity: Activity, perfil: Perfil) : Boolean{
        val retrofitClient = ConectorApi
            .getRetrofitInstance("https://bemtevisocialnetworkapi.azurewebsites.net")

        val endpoint = retrofitClient.create(EndpointsApi::class.java)
        val callback = endpoint.criarPerfil(PerfilConverter.getInstance().converterPerfilParaDto(perfil))
        var sucesso = false
        var perfilDto = callback.execute().body()

        /*var corpo = callback.execute().body()!!
        var id = corpo.id
        var email = corpo.email
        var nome = corpo.nome
        var serieGerado = corpo.serieGerado
        var dataNasc = corpo.dataNascimento
        var sobre = corpo.sobre
        var foto = corpo.foto*/

        if(perfilDto == null)
            return false

        return true;

        /*callback.enqueue(object : Callback<PerfilDto> {
            override fun onFailure(call: Call<PerfilDto>, t: Throwable) {
                Toast.makeText(activity, "Problema ao tentar acessar os dados!", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<PerfilDto>, response: Response<PerfilDto>) {
                if(response.isSuccessful && response.body() != null)
                {
                    println("\n\n\n\n ${response.body()}")
                }
                else
                {
                    println("\n\n\n\n ${response.errorBody()}")
                    var i = 0
                }
            }
        })*/

        //return sucesso
    }

    fun deletarPerfil(activity: Activity, perfilId: Int) : Boolean{
        val retrofitClient = ConectorApi
            .getRetrofitInstance("https://bemtevisocialnetworkapi.azurewebsites.net")

        val endpoint = retrofitClient.create(EndpointsApi::class.java)
        val callback = endpoint.deletarPerfil(perfilId)
        var sucesso = false

        callback.enqueue(object : Callback<PerfilDto> {
            override fun onFailure(call: Call<PerfilDto>, t: Throwable) {
                Toast.makeText(activity, "Problema ao tentar acessar os dados!", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<PerfilDto>, response: Response<PerfilDto>) {
                if(response.isSuccessful && response.body() != null)
                    sucesso = true;
            }
        })

        return sucesso
    }

    fun atualizarPerfil(activity: Activity, perfil: Perfil) : Boolean{
        val retrofitClient = ConectorApi
            .getRetrofitInstance("https://bemtevisocialnetworkapi.azurewebsites.net")

        val endpoint = retrofitClient.create(EndpointsApi::class.java)
        val callback = endpoint.atualizarPerfil(perfil.id!!, PerfilConverter.getInstance().converterPerfilParaDto(perfil))
        var sucesso = false

        callback.enqueue(object : Callback<PerfilDto> {
            override fun onFailure(call: Call<PerfilDto>, t: Throwable) {
                Toast.makeText(activity, "Problema ao tentar acessar os dados!", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<PerfilDto>, response: Response<PerfilDto>) {
                if(response.isSuccessful && response.body() != null)
                    sucesso = true;
            }
        })

        return sucesso
    }

    fun buscarPerfilPorSerie(activity: Activity, serie : String) : Perfil?{
        val retrofitClient = ConectorApi
            .getRetrofitInstance("https://bemtevisocialnetworkapi.azurewebsites.net")

        val endpoint = retrofitClient.create(EndpointsApi::class.java)
        val callback = endpoint.buscarPerfilPorSerie(serie)
        var perfil : Perfil? = null

        callback.enqueue(object : Callback<PerfilDto> {
            override fun onFailure(call: Call<PerfilDto>, t: Throwable) {
                Toast.makeText(activity, "Problema ao tentar acessar os dados!", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<PerfilDto>, response: Response<PerfilDto>) {
                if(response.isSuccessful && response.body() != null)
                {
                    perfil = PerfilConverter.getInstance().converterDtoParaPerfil(response.body()!!)
                }
            }
        })

        return perfil
    }

    fun pesquisarUsuariosPorNome(activity: Activity, id : Int ,nome : String) : List<Perfil>? {
        val retrofitClient = ConectorApi
            .getRetrofitInstance("https://bemtevisocialnetworkapi.azurewebsites.net")

        val endpoint = retrofitClient.create(EndpointsApi::class.java)
        val callback = endpoint.pesquisarUsuarios(id, nome)
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

    fun verificarUsernameLivre(activity: Activity, nome : String) : Boolean{
        val retrofitClient = ConectorApi
            .getRetrofitInstance("https://bemtevisocialnetworkapi.azurewebsites.net")

        val endpoint = retrofitClient.create(EndpointsApi::class.java)
        val callback = endpoint.pesquisarUsernameLivre(nome)
        var livre = true

        callback.enqueue(object : Callback<Boolean> {
            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                Toast.makeText(activity, "Problema ao tentar acessar os dados!", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                if(response.isSuccessful && response.body() != null)
                {
                    livre = false
                }
            }
        })

        return livre
    }

}