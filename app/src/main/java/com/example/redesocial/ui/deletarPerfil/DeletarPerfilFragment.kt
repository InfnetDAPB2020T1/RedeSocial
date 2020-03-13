package com.example.redesocial.ui.deletarPerfil

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
class DeletarPerfilFragment : Fragment() {

    private lateinit var deletarPerfilViewModel: DeletarPerfilViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        deletarPerfilViewModel =
            ViewModelProviders.of(this).get(DeletarPerfilViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_deletar_perfil, container, false)
        val textView: TextView = root.findViewById(R.id.text_deletar_perfil)
        deletarPerfilViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }


}
