package com.example.redesocial.ui.convites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.redesocial.R
import com.example.redesocial.adapters.PessoaAdapter
import com.example.redesocial.models.Pessoa
import kotlinx.android.synthetic.main.fragment_convites_recebidos.*

/**
 * A simple [Fragment] subclass.
 */
class ConvitesRecebidos : Fragment() {

    var listaPessoas = mutableListOf<Pessoa>(
        Pessoa("Lucas",R.drawable.userpeqbkggray),
        Pessoa("Eduardo",R.drawable.userpeqbkggray),
        Pessoa("Pedro",R.drawable.userpeqbkggray),
        Pessoa("Paulo",R.drawable.userpeqbkggray),
        Pessoa("Cecília",R.drawable.userpeqbkggray),
        Pessoa("Santos",R.drawable.userpeqbkggray)
    )

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
        if(!listaPessoas.isNullOrEmpty()) {
            listagem_convites_recebidos.layoutManager = LinearLayoutManager(activity)
            listagem_convites_recebidos.adapter = PessoaAdapter(listaPessoas)
        }
        else
        {
            listagem_convites_recebidos.visibility = View.GONE
            empty_view_convites_recebidos.visibility = View.VISIBLE
        }
    }
}
