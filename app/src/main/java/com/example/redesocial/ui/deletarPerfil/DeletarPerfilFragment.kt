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
import kotlinx.android.synthetic.main.fragment_deletar_perfil.*


/**
 * A simple [Fragment] subclass.
 */
class DeletarPerfilFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_deletar_perfil, container, false)

        return root
    }


}
