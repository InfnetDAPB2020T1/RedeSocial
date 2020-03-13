package com.example.redesocial.ui.dadosPerfil


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.example.redesocial.R
import kotlinx.android.synthetic.main.fragment_dados_perfil.*


/**
 * A simple [Fragment] subclass.
 */
class DadosPerfilFragment : Fragment() {

    private lateinit var dadosPerfilViewModel: DadosPerfilViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dadosPerfilViewModel =
            ViewModelProviders.of(this).get(DadosPerfilViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dados_perfil, container, false)

        val campoEmail: TextView = root.findViewById(R.id.text_dados_perfil)
        dadosPerfilViewModel.campoEmail.observe(this, Observer {
            text_email_label.text = it
        })
        val email: TextView = root.findViewById(R.id.text_dados_perfil)
        dadosPerfilViewModel.email.observe(this, Observer {
            text_email.text = it
        })

        return root
    }


}
