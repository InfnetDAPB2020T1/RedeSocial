package com.example.redesocial.ui.amizades


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
class AmizadesFragment : Fragment() {

    private lateinit var amizadesViewModel: AmizadesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        amizadesViewModel =
            ViewModelProviders.of(this).get(AmizadesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_amizades, container, false)
        val textView: TextView = root.findViewById(R.id.text_amizades)
        amizadesViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }


}
