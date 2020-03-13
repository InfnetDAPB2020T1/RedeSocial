package com.example.redesocial.ui.editarPerfil


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.example.redesocial.R
import com.example.redesocial.ui.dadosPerfil.DadosPerfilViewModel

/**
 * A simple [Fragment] subclass.
 */
class EditarPerfilFragment : Fragment() {

    private lateinit var editarPerfilViewModel: EditarPerfilViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        editarPerfilViewModel =
            ViewModelProviders.of(this).get(EditarPerfilViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_editar_perfil, container, false)
        val textView: TextView = root.findViewById(R.id.text_editar_perfil)
        editarPerfilViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }


}
