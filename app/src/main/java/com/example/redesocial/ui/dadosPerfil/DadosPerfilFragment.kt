package com.example.redesocial.ui.dadosPerfil


import android.app.Activity
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.redesocial.MainActivity

import com.example.redesocial.R
import com.example.redesocial.models.Perfil
import com.example.redesocial.services.OperacoesPerfilService
import com.example.redesocial.ui.carregamentoalerta.LoadingAlerta
import com.example.redesocial.viewmodel.PerfilViewModel
import com.google.android.material.textfield.TextInputEditText
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
    }

}
