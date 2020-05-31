package com.example.redesocial.ui.timeline


import android.app.Activity
import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.redesocial.R
import com.example.redesocial.adapters.MensagemTimelineAdapter
import com.example.redesocial.models.Mensagem
import com.example.redesocial.models.MensagemTimeline
import com.example.redesocial.services.OperacoesMensagemService
import com.example.redesocial.ui.carregamentoalerta.LoadingAlerta
import com.example.redesocial.viewmodel.PerfilViewModel
import kotlinx.android.synthetic.main.fragment_timeline.*


/**
 * A simple [Fragment] subclass.
 */
class TimelineFragment : Fragment() {

    var listaMensagens = mutableListOf<Mensagem>()
    private lateinit var perfilViewModel: PerfilViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_timeline, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity!!.let { act->
            perfilViewModel = ViewModelProviders.of(act)
                .get(PerfilViewModel::class.java) }

        if(perfilViewModel.perfilAtual != null)
            BuscarTimelineAsync(activity!!,listaMensagens,perfilViewModel.perfilAtual!!.id!!).execute()
    }

    class BuscarTimelineAsync(activity: Activity, listaMensagens : MutableList<Mensagem>, perfilId : Int) : AsyncTask<Void, Void, List<Mensagem>?>()
    {
        var activity = activity
        var listaMensagens = listaMensagens
        var perfilId = perfilId
        var dialogApi = LoadingAlerta(activity)

        override fun onPreExecute() {
            super.onPreExecute()
            dialogApi.startLoadingDialog("Carregando timeline...")
        }

        override fun doInBackground(vararg params: Void?): List<Mensagem>? {
            var lista = OperacoesMensagemService.getInstance().buscarTimeline(activity, perfilId)
            dialogApi.dismiss()
            return lista
        }

        override fun onPostExecute(result: List<Mensagem>?) {
            super.onPostExecute(result)

            var lista = activity!!.findViewById<RecyclerView>(R.id.lista_timeline)

            if(!result.isNullOrEmpty())
            {
                result.forEach{
                    listaMensagens.add(it)
                }

                lista.layoutManager = LinearLayoutManager(activity)
                lista.adapter = MensagemTimelineAdapter(listaMensagens)
            }
            else
            {
                var vazio = activity!!.findViewById<TextView>(R.id.empty_view_timeline)
                lista.visibility = View.GONE
                vazio.visibility = View.VISIBLE
            }

        }

    }
}
