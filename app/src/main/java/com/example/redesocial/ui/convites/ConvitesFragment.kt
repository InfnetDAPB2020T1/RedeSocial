package com.example.redesocial.ui.convites


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
class ConvitesFragment : Fragment() {

    private lateinit var convitesViewModel: ConvitesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        convitesViewModel =
            ViewModelProviders.of(this).get(ConvitesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_convites, container, false)
        val textView: TextView = root.findViewById(R.id.text_convites)
        convitesViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }


}
