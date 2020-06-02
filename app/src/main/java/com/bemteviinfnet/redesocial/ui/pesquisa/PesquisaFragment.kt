package com.bemteviinfnet.redesocial.ui.pesquisa


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController

import com.bemteviinfnet.redesocial.R
import com.bemteviinfnet.redesocial.viewmodel.PesquisaViewModel
import kotlinx.android.synthetic.main.fragment_pesquisa.*


/**
 * A simple [Fragment] subclass.
 */
class PesquisaFragment : Fragment() {

    private lateinit var pesquisaViewModel: PesquisaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_pesquisa, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity!!.let { act->
            pesquisaViewModel = ViewModelProviders.of(act)
                .get(PesquisaViewModel::class.java) }

        btn_pesquisar_user.setOnClickListener {
            var nome = nome_pesquisar.text.toString()

            if(!nome.isNullOrBlank())
            {
                if(pesquisaViewModel != null)
                {
                    pesquisaViewModel.setNome(nome)

                    var navController = activity!!.findNavController(R.id.host_pesquisa)

                    navController.navigate(R.id.resultadoPesquisaFragment)
                }

            }

        }


    }


}
