package com.example.redesocial.ui.amizades


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.redesocial.R
import com.example.redesocial.adapters.PessoaAdapter
import kotlinx.android.synthetic.main.fragment_amizades.*


class AmizadesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_amizades, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configurarRecyclerView()
    }

    fun configurarRecyclerView()
    {
        listagemAmizades.layoutManager = LinearLayoutManager(activity)
        listagemAmizades.adapter = PessoaAdapter()
    }
}
