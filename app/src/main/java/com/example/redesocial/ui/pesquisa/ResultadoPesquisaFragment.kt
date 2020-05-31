package com.example.redesocial.ui.pesquisa

import android.app.Activity
import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.redesocial.R
import com.example.redesocial.adapters.SugestaoAdapter
import com.example.redesocial.models.Perfil
import com.example.redesocial.services.OperacoesPerfilService
import com.example.redesocial.ui.carregamentoalerta.LoadingAlerta
import com.example.redesocial.viewmodel.PerfilViewModel
import com.example.redesocial.viewmodel.PesquisaViewModel

/**
 * A simple [Fragment] subclass.
 */
class ResultadoPesquisaFragment : Fragment() {

    var listaPessoas = mutableListOf<Perfil>()
    private lateinit var perfilViewModel: PerfilViewModel
    private lateinit var pesquisaViewModel: PesquisaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_resultado_pesquisa, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity!!.let { act->
            perfilViewModel = ViewModelProviders.of(act)
                .get(PerfilViewModel::class.java) }

        activity!!.let { act->
            pesquisaViewModel = ViewModelProviders.of(act)
                .get(PesquisaViewModel::class.java) }

        if(perfilViewModel.perfilAtual != null && pesquisaViewModel.username != null)
            BuscarUsuariosAsync(activity!!,listaPessoas,perfilViewModel,pesquisaViewModel.username!!).execute()
    }

    class BuscarUsuariosAsync(activity: Activity, listaPessoas : MutableList<Perfil>, perfilViewModel: PerfilViewModel, nome : String) : AsyncTask<Void, Void, List<Perfil>?>() {
        var activity = activity
        var listaPessoas = listaPessoas
        var perfilViewModel = perfilViewModel
        var nome = nome
        var dialogApi = LoadingAlerta(activity)

        override fun onPreExecute() {
            super.onPreExecute()
            dialogApi.startLoadingDialog("Buscando usuários...")
        }

        override fun doInBackground(vararg params: Void?): List<Perfil>? {
            var listaPerf = OperacoesPerfilService.getInstance()
                .pesquisarUsuariosPorNome(activity, perfilViewModel.perfilAtual!!.id!!, nome)
            dialogApi.dismiss()
            return listaPerf
        }

        override fun onPostExecute(result: List<Perfil>?) {
            super.onPostExecute(result)

            var lista = activity!!.findViewById<RecyclerView>(R.id.listagem_pesquisa)

            if (!result.isNullOrEmpty()) {
                result.forEach {
                    listaPessoas.add(it)
                }

                lista.layoutManager = LinearLayoutManager(activity)
                lista.adapter = SugestaoAdapter(listaPessoas, activity, perfilViewModel)
            } else {
                var vazio = activity!!.findViewById<TextView>(R.id.empty_view_pesquisa)
                lista.visibility = View.GONE
                vazio.visibility = View.VISIBLE
            }

        }
    }

}
