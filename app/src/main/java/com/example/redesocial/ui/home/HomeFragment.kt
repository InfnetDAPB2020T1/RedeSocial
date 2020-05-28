package com.example.redesocial.ui.home

import android.app.Activity
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.redesocial.R
import com.example.redesocial.adapters.PessoaAdapter
import com.example.redesocial.models.Perfil
import com.example.redesocial.models.Pessoa
import com.example.redesocial.services.OperacoesConviteService
import com.example.redesocial.ui.carregamentoalerta.LoadingAlerta
import com.example.redesocial.util.converters.PerfilConverter
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    var listaPessoas = mutableListOf<Pessoa>(
        Pessoa("Lucas",R.drawable.userpeqbkggray),
        Pessoa("Eduardo",R.drawable.userpeqbkggray),
        Pessoa("Pedro",R.drawable.userpeqbkggray),
        Pessoa("Paulo",R.drawable.userpeqbkggray),
        Pessoa("Cecília",R.drawable.userpeqbkggray),
        Pessoa("Santos",R.drawable.userpeqbkggray)
    )

    var auth = FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configurarRecyclerView()
    }

    fun configurarRecyclerView()
    {
        listaPessoas = mutableListOf()
        if(!listaPessoas.isNullOrEmpty())
        {
            listagemPessoas.layoutManager = LinearLayoutManager(activity)
            listagemPessoas.adapter = PessoaAdapter(listaPessoas)
        }
        else
        {
            listagemPessoas.visibility = View.GONE
            empty_view_home.visibility = View.VISIBLE
        }
    }

    class CarregarSugestoesAmizadeAsync(activity: Activity, listaPessoas : MutableList<Perfil>, perfilId : Int) : AsyncTask<Void, Void, List<Perfil>?>()
    {
        var activity = activity
        var listaPessoas = listaPessoas
        var perfilId = perfilId
        var dialogApi = LoadingAlerta(activity)

        override fun onPreExecute() {
            super.onPreExecute()
            dialogApi.startLoadingDialog("Buscando sugestões de amizade...")
        }

        override fun doInBackground(vararg params: Void?): List<Perfil>? {
            var lista = OperacoesConviteService.getInstance().buscarSugestoesAmizade(activity,perfilId)
            dialogApi.dismiss()
            return lista
        }

        override fun onPostExecute(result: List<Perfil>?) {
            super.onPostExecute(result)

            if(!result.isNullOrEmpty())
            {
                result.forEach{
                    listaPessoas.add(it)
                }
            }

        }

    }
}