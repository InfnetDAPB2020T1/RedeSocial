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
        val textView: TextView = root.findViewById(R.id.text_dados_perfil)
        dadosPerfilViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }


}
