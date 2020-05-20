package com.example.redesocial.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.redesocial.R
import com.example.redesocial.adapters.PessoaAdapter
import com.example.redesocial.models.Pessoa
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    var listaPessoas = mutableListOf<Pessoa>(
        Pessoa("Lucas",R.drawable.userpeqbkggray),
        Pessoa("Eduardo",R.drawable.userpeqbkggray),
        Pessoa("Pedro",R.drawable.userpeqbkggray),
        Pessoa("Paulo",R.drawable.userpeqbkggray),
        Pessoa("Cecília",R.drawable.userpeqbkggray),
        Pessoa("Santos",R.drawable.userpeqbkggray)
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configurarRecyclerView()
    }

    fun configurarRecyclerView()
    {
        listaPessoas = mutableListOf()
        if(!listaPessoas.isNullOrEmpty())
        {
            listagemPessoas.layoutManager = LinearLayoutManager(activity)
            listagemPessoas.adapter = PessoaAdapter(listaPessoas)
        }
        else
        {
            listagemPessoas.visibility = View.GONE
            empty_view_home.visibility = View.VISIBLE
        }
    }
}