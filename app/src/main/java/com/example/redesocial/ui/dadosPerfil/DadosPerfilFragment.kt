package com.example.redesocial.ui.dadosPerfil


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.redesocial.MainActivity

import com.example.redesocial.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_dados_perfil.*


/**
 * A simple [Fragment] subclass.
 */
class DadosPerfilFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_dados_perfil, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_logoff.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            var intent = Intent(activity!!,MainActivity::class.java)
            startActivity(intent)
        }
    }


}
