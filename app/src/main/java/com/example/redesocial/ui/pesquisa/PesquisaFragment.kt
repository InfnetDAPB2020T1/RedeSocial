package com.example.redesocial.ui.pesquisa


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
class PesquisaFragment : Fragment() {

    private lateinit var pesquisaViewModel: PesquisaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        pesquisaViewModel =
            ViewModelProviders.of(this).get(PesquisaViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_pesquisa, container, false)
        val textView: TextView = root.findViewById(R.id.text_pesquisa)
        pesquisaViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }


}
