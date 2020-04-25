package com.example.redesocial.ui.convites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.redesocial.R
import com.example.redesocial.adapters.PessoaAdapter
import kotlinx.android.synthetic.main.fragment_convites_enviados.*

/**
 * A simple [Fragment] subclass.
 */
class ConvitesEnviados : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_convites_enviados, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configurarRecyclerView()
    }

    fun configurarRecyclerView()
    {
        listagem_convites_enviados.layoutManager = LinearLayoutManager(activity)
        listagem_convites_enviados.adapter = PessoaAdapter()
    }
}
