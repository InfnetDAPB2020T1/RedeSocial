package com.example.redesocial.ui.novaMensagem


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
class NovaMensagemFragment : Fragment() {

    private lateinit var novaMensagemViewModel: NovaMensagemViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        novaMensagemViewModel =
            ViewModelProviders.of(this).get(NovaMensagemViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_nova_mensagem, container, false)
        val textView: TextView = root.findViewById(R.id.text_nova_mensagem)
        novaMensagemViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }


}
