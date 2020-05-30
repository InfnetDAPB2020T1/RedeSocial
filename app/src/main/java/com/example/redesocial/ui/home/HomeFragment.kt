package com.example.redesocial.ui.home

import android.app.Activity
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
import androidx.recyclerview.widget.RecyclerView
import com.example.redesocial.R
import com.example.redesocial.adapters.PerfilAdapter
import com.example.redesocial.adapters.PessoaAdapter
import com.example.redesocial.models.Perfil
import com.example.redesocial.models.Pessoa
import com.example.redesocial.services.OperacoesConviteService
import com.example.redesocial.services.OperacoesPerfilService
import com.example.redesocial.ui.carregamentoalerta.LoadingAlerta
import com.example.redesocial.viewmodel.PerfilViewModel
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    var listaPessoas = mutableListOf<Perfil>()

    var auth = FirebaseAuth.getInstance()
    private  lateinit var perfilViewModel: PerfilViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity!!.let { act->
            perfilViewModel = ViewModelProviders.of(act)
                .get(PerfilViewModel::class.java) }

        if(perfilViewModel.perfilAtual == null) {
            subscribe()

            BuscarInformacoesPerfilAsync(
                activity!!,
                auth.currentUser!!.uid,
                perfilViewModel,
                listaPessoas
            ).execute()
        }
        else
        {
            CarregarSugestoesAmizadeAsync(activity!!,listaPessoas,perfilViewModel.perfilAtual!!.id!!).execute()
        }

    }

    fun configurarRecyclerView()
    {
        listaPessoas = mutableListOf()
        if(!listaPessoas.isNullOrEmpty())
        {
            listagemPessoas.layoutManager = LinearLayoutManager(activity)
            listagemPessoas.adapter = PerfilAdapter(listaPessoas)
        }
        else
        {
            listagemPessoas.visibility = View.GONE
            empty_view_home.visibility = View.VISIBLE
        }
    }

    fun subscribe()
    {
        perfilViewModel.modificado.observe(this, Observer {
            if (perfilViewModel.perfilAtual != null) {
                var nav: NavigationView = activity!!.findViewById(R.id.nav_view)
                var header = nav.inflateHeaderView(R.layout.nav_header_tela_principal)
                //var header = navView.getHeaderView(0)
                var menuUsername = header.findViewById<TextView>(R.id.header_username)
                var menuEmail = header.findViewById<TextView>(R.id.header_email)

                menuUsername.text = perfilViewModel.perfilAtual!!.nome
                menuEmail.text = perfilViewModel.perfilAtual!!.email
            }
        })
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
            var listaPerf = OperacoesConviteService.getInstance().buscarSugestoesAmizade(activity,perfilId)
            dialogApi.dismiss()
            return listaPerf
        }

        override fun onPostExecute(result: List<Perfil>?) {
            super.onPostExecute(result)

            var lista = activity!!.findViewById<RecyclerView>(R.id.listagemPessoas)

            if(!result.isNullOrEmpty())
            {
                result.forEach{
                    listaPessoas.add(it)
                }

                lista.layoutManager = LinearLayoutManager(activity)
                lista.adapter = PerfilAdapter(listaPessoas)
            }
            else
            {
                var vazio = activity!!.findViewById<TextView>(R.id.empty_view_home)
                lista.visibility = View.GONE
                vazio.visibility = View.VISIBLE
            }

        }

    }

    class BuscarInformacoesPerfilAsync(activity: Activity, serieGerado : String,perfilViewModel: PerfilViewModel,listaPessoas: MutableList<Perfil>) : AsyncTask<Void, Void, Perfil?>()
    {
        var activity = activity
        var serieGerado = serieGerado
        var perfilViewModel = perfilViewModel
        var listaPessoas = listaPessoas
        var dialogApi = LoadingAlerta(activity)

        override fun onPreExecute() {
            super.onPreExecute()
            dialogApi.startLoadingDialog("Inicializando...")
        }

        override fun doInBackground(vararg params: Void?): Perfil? {
            var perfil = OperacoesPerfilService.getInstance().buscarPerfilPorSerie(activity,serieGerado)

            dialogApi.dismiss()

            return perfil
        }

        override fun onPostExecute(result: Perfil?) {
            super.onPostExecute(result)

            if(result != null)
            {
                perfilViewModel.setPerfil(Perfil(result.id,result.serieGerado,result.nome,result.dataNascimento,
                    result.email,result.sobre,result.foto))

                CarregarSugestoesAmizadeAsync(activity,listaPessoas,result.id!!).execute()

                perfilViewModel.setModificado()

            }

        }

    }
}