package com.example.redesocial.ui.convites

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
import com.example.redesocial.adapters.AmizadeAdapter
import com.example.redesocial.adapters.PessoaAdapter
import com.example.redesocial.models.Convite
import com.example.redesocial.models.Perfil
import com.example.redesocial.models.Pessoa
import com.example.redesocial.services.OperacoesConviteService
import com.example.redesocial.ui.carregamentoalerta.LoadingAlerta
import com.example.redesocial.viewmodel.PerfilViewModel
import kotlinx.android.synthetic.main.fragment_convites_enviados.*

/**
 * A simple [Fragment] subclass.
 */
class ConvitesEnviados : Fragment() {

    var listaPessoas = mutableListOf<Perfil>()
    private lateinit var perfilViewModel: PerfilViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_convites_enviados, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity!!.let { act->
            perfilViewModel = ViewModelProviders.of(act)
                .get(PerfilViewModel::class.java) }

        if(perfilViewModel.perfilAtual != null)
            BuscarConvitesEnviadosAsync(activity!!,listaPessoas,perfilViewModel).execute()
    }

    class BuscarConvitesEnviadosAsync(activity: Activity, listaPessoas : MutableList<Perfil>, perfilViewModel: PerfilViewModel) : AsyncTask<Void, Void, List<Convite>?>() {
        var activity = activity
        var listaPessoas = listaPessoas
        var perfilViewModel = perfilViewModel
        var dialogApi = LoadingAlerta(activity)

        override fun onPreExecute() {
            super.onPreExecute()
            dialogApi.startLoadingDialog("Buscando convites enviados...")
        }

        override fun doInBackground(vararg params: Void?): List<Convite>? {
            var listaPerf = OperacoesConviteService.getInstance()
                .buscarMeusConvites(activity, perfilViewModel.perfilAtual!!.id!!)
            dialogApi.dismiss()
            return listaPerf
        }

        override fun onPostExecute(result: List<Convite>?) {
            super.onPostExecute(result)

            var lista = activity!!.findViewById<RecyclerView>(R.id.listagem_convites_enviados)

            if (!result.isNullOrEmpty()) {
                result.forEach {
                    listaPessoas.add(it.convidado)
                }

                lista.layoutManager = LinearLayoutManager(activity)
                lista.adapter = AmizadeAdapter(listaPessoas, activity, perfilViewModel)
            } else {
                var vazio = activity!!.findViewById<TextView>(R.id.empty_view_convites_enviados)
                lista.visibility = View.GONE
                vazio.visibility = View.VISIBLE
            }

        }
    }
}
