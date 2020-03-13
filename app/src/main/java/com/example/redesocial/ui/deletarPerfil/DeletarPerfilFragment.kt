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

    private lateinit var deletarPerfilViewModel: DeletarPerfilViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        deletarPerfilViewModel =
            ViewModelProviders.of(this).get(DeletarPerfilViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_deletar_perfil, container, false)

        val textnome: TextView = root.findViewById(R.id.text_nome)
        deletarPerfilViewModel.nome.observe(this, Observer {
            text_nome.text = it
        })
        val textcamponome: TextView = root.findViewById(R.id.text_camponome)
        deletarPerfilViewModel.camponome.observe(this, Observer {
            text_camponome.text = it
        })

        val textemail: TextView = root.findViewById(R.id.text_email)
        deletarPerfilViewModel.email.observe(this, Observer {
            text_email.text = it
        })
        val textcampoemail: TextView = root.findViewById(R.id.text_campoemail)
        deletarPerfilViewModel.campoemail.observe(this, Observer {
            text_campoemail.text = it
        })

        val textdatanascimento: TextView = root.findViewById(R.id.text_datanascimento)
        deletarPerfilViewModel.datanascimento.observe(this, Observer {
            text_datanascimento.text = it
        })
        val textcampodatanascimento: TextView = root.findViewById(R.id.text_campodatanascimento)
        deletarPerfilViewModel.campodatanascimento.observe(this, Observer {
            text_campodatanascimento.text = it
        })

        val textsobre: TextView = root.findViewById(R.id.text_sobre)
        deletarPerfilViewModel.sobre.observe(this, Observer {
            text_sobre.text = it
        })
        val textcamposobre: TextView = root.findViewById(R.id.text_camposobre)
        deletarPerfilViewModel.camposobre.observe(this, Observer {
            text_camposobre.text = it
        })

        return root
    }


}
