package com.bemteviinfnet.redesocial.ui.novaMensagem


import android.app.Activity
import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController

import com.bemteviinfnet.redesocial.R
import com.bemteviinfnet.redesocial.models.Mensagem
import com.bemteviinfnet.redesocial.services.OperacoesMensagemService
import com.bemteviinfnet.redesocial.ui.carregamentoalerta.LoadingAlerta
import com.bemteviinfnet.redesocial.viewmodel.PerfilViewModel
import com.google.android.material.textfield.TextInputEditText


/**
 * A simple [Fragment] subclass.
 */
class NovaMensagemFragment : Fragment() {

    private  lateinit var perfilViewModel: PerfilViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_nova_mensagem, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity!!.let { act->
            perfilViewModel = ViewModelProviders.of(act)
                .get(PerfilViewModel::class.java) }

        if(perfilViewModel.perfilAtual != null)
        {
            activity!!.findViewById<Button>(R.id.btn_criar_nova_mensagem).setOnClickListener {
                var texto = activity!!.findViewById<TextInputEditText>(R.id.input_nova_mensagem).text.toString()
                CriarMensagemAsync(activity!!,perfilViewModel,texto).execute()
            }
        }
    }


    class CriarMensagemAsync(activity: Activity, perfilViewModel: PerfilViewModel, textoMensagem : String) : AsyncTask<Void, Void, Boolean>()
    {
        var activity = activity
        var perfilViewModel = perfilViewModel
        var textoMensagem = textoMensagem
        var dialogApi = LoadingAlerta(activity)

        override fun onPreExecute() {
            super.onPreExecute()
            dialogApi.startLoadingDialog("Enviando mensagem...")
        }

        override fun doInBackground(vararg params: Void?): Boolean {
            var mensagem = Mensagem(0,textoMensagem, perfilViewModel.perfilAtual!!)
            var sucesso = OperacoesMensagemService.getInstance().criarMensagem(activity,perfilViewModel.perfilAtual!!.id!!,mensagem)

            dialogApi.dismiss()

            return sucesso
        }

        override fun onPostExecute(result: Boolean) {
            super.onPostExecute(result)

            if(result)
            {
                activity.findNavController(R.id.nav_host_fragment).navigate(R.id.nav_timeline)
            }

        }

    }


}
