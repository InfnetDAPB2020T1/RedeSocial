package com.example.redesocial.ui.convites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.redesocial.R
import com.example.redesocial.adapters.PessoaAdapter
import kotlinx.android.synthetic.main.fragment_convites_recebidos.*

/**
 * A simple [Fragment] subclass.
 */
class ConvitesRecebidos : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_convites_recebidos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configurarRecyclerView()
    }

    fun configurarRecyclerView()
    {
        listagem_convites_recebidos.layoutManager = LinearLayoutManager(activity)
        listagem_convites_recebidos.adapter = PessoaAdapter()
    }
}
