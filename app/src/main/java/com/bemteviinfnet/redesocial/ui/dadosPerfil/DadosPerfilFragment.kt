package com.bemteviinfnet.redesocial.ui.dadosPerfil


import android.app.Activity
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.bemteviinfnet.redesocial.MainActivity

import com.bemteviinfnet.redesocial.R
import com.bemteviinfnet.redesocial.services.OperacoesPerfilService
import com.bemteviinfnet.redesocial.ui.carregamentoalerta.LoadingAlerta
import com.bemteviinfnet.redesocial.viewmodel.PerfilViewModel
import com.google.android.material.textview.MaterialTextView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_dados_perfil.*


/**
 * A simple [Fragment] subclass.
 */
class DadosPerfilFragment : Fragment() {

    var auth = FirebaseAuth.getInstance()
    private  lateinit var perfilViewModel: PerfilViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_dados_perfil, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity!!.let { act->
            perfilViewModel = ViewModelProviders.of(act)
                .get(PerfilViewModel::class.java) }

        activity!!.findViewById<MaterialTextView>(R.id.nome_dados).text = perfilViewModel.perfilAtual!!.nome
        activity!!.findViewById<MaterialTextView>(R.id.email_dados).text = perfilViewModel.perfilAtual!!.email
        activity!!.findViewById<MaterialTextView>(R.id.dataNascimento_dados).text = perfilViewModel.perfilAtual!!.dataNascimento
        activity!!.findViewById<MaterialTextView>(R.id.text_sobre_dados).text = perfilViewModel.perfilAtual!!.sobre

        btn_logoff.setOnClickListener {
            auth.signOut()
            var intent = Intent(activity!!,MainActivity::class.java)
            startActivity(intent)
        }

        btn_deletar_perfil.setOnClickListener {
            ExcluirPerfilAsync(activity!!,perfilViewModel,auth).execute()
        }
    }

    class ExcluirPerfilAsync(activity: Activity, perfilViewModel: PerfilViewModel,auth: FirebaseAuth) : AsyncTask<Void, Void, Boolean>()
    {
        var activity = activity
        var perfilViewModel = perfilViewModel
        var auth = auth
        var dialogApi = LoadingAlerta(activity)

        override fun onPreExecute() {
            super.onPreExecute()
            dialogApi.startLoadingDialog("ExcluindoPerfil...")
        }

        override fun doInBackground(vararg params: Void?): Boolean {
            var sucesso = OperacoesPerfilService.getInstance().deletarPerfil(activity,perfilViewModel.perfilAtual!!.id!!)
            //dialogApi.dismiss()
            return sucesso
        }

        override fun onPostExecute(result: Boolean) {
            super.onPostExecute(result)
            if(result)
            {
                auth.currentUser!!.delete().addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        dialogApi.dismiss()
                        var intent = Intent(activity,MainActivity::class.java)
                        activity.startActivity(intent)
                    }
                }
            }
        }

    }

}
