package com.example.redesocial.repository

import com.example.redesocial.models.dtos.ConviteDto
import com.example.redesocial.models.dtos.MensagemDto
import com.example.redesocial.models.dtos.PerfilDto
import retrofit2.Call
import retrofit2.http.*

interface EndpointsApi {

    @GET("perfil")
    fun listarPerfis() : Call<List<PerfilDto>>

    @GET("/perfil/{id}")
    fun buscarPerfil(@Path("id") id : Int) : Call<PerfilDto>

    @POST("/perfil")
    fun criarPerfil(@Body perfilDto: PerfilDto) : Call<PerfilDto>

    @PUT("perfil/{id}")
    fun atualizarPerfil(@Path("id") id : Int, @Body perfilDto: PerfilDto) : Call<PerfilDto>

    @DELETE("perfil/{id}")
    fun deletarPerfil(@Path("id") id : Int) : Call<PerfilDto>

    @GET("perfil/buscarpornumserie/{serie}")
    fun buscarPerfilPorSerie(@Path("serie") serie : String) : Call<PerfilDto>

    @GET("perfil/{id}/pessoas/{nome}")
    fun pesquisarUsuarios(@Path("id") id : Int, @Path("nome") nome : String) : Call<List<PerfilDto>>

    @GET("perfil/usernamelivre/{username}")
    fun pesquisarUsernameLivre(@Path("username") username : String) : Call<Boolean>

    @GET("mensagem/busca/{id}")
    fun buscarMensagem(@Path("id") id : Int) : Call<MensagemDto>

    @GET("mensagem/timeline/{id}")
    fun buscarTimeline(@Path("id") id : Int) : Call<List<MensagemDto>>

    @POST("mensagem/{idPerfil}")
    fun criarMensagem(@Path("idPerfil") idPerfil : Int, @Body mensagemDto: MensagemDto) : Call<Unit>

    @DELETE("mensagem/{id}")
    fun deletarMensagem(@Path("id") id : Int) : Call<MensagemDto>

    @GET("amizades/meusconvites/{id}")
    fun buscarMeusConvites(@Path("id") id : Int) : Call<List<ConviteDto>>

    @GET("amizades/convitesrecebidos/{id}")
    fun buscarConvitesRecebidos(@Path("id") id : Int) : Call<List<ConviteDto>>

    @GET("amizades/{perfilId}/aceitar/{idConvidante}")
    fun aceitarConvite(@Path("perfilId") perfilId : Int, @Path("idConvidante") idConvidante : Int) : Call<Unit>

    @GET("amizades/{perfilId}/bloquear/{idUsuario}")
    fun bloquearUsuario(@Path("perfilId") perfilId : Int, @Path("idUsuario") idUsuario : Int) : Call<Unit>

    @GET("amizades/{perfilId}/convidar/{idConvidado}")
    fun enviarConvite(@Path("perfilId") perfilId : Int, @Path("idConvidado") idConvidado : Int) : Call<Unit>

    @GET("amizades/sugestoes/{id}")
    fun buscarSugestoesAmizade(@Path("id") id : Int) : Call<List<PerfilDto>>

    @GET("amizades/amigos/{id}")
    fun buscarAmigos(@Path("id") id : Int) : Call<List<PerfilDto>>
}