package com.example.redesocial.ui.timeline


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.redesocial.R
import com.example.redesocial.adapters.MensagemTimelineAdapter
import com.example.redesocial.models.MensagemTimeline
import kotlinx.android.synthetic.main.fragment_timeline.*


/**
 * A simple [Fragment] subclass.
 */
class TimelineFragment : Fragment() {

    var listaMensagens = mutableListOf<MensagemTimeline>(
        MensagemTimeline("Lucas",R.drawable.userquadrado,"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."),
        MensagemTimeline("Martins",R.drawable.userquadrado,"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."),
        MensagemTimeline("Pedro",R.drawable.userquadrado,"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."),
        MensagemTimeline("Paulo",R.drawable.userquadrado,"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."),
        MensagemTimeline("Cecilia",R.drawable.userquadrado,"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."),
        MensagemTimeline("Santos",R.drawable.userquadrado,"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."),
        MensagemTimeline("Seu Madruga",R.drawable.userquadrado,"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."),
        MensagemTimeline("Rocky Balboa",R.drawable.userquadrado,"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_timeline, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configurarRecyclerView()
    }

    fun configurarRecyclerView() {

        if(!listaMensagens.isNullOrEmpty())
        {
            lista_timeline.layoutManager = LinearLayoutManager(activity)
            lista_timeline.adapter = MensagemTimelineAdapter(listaMensagens)
        }
        else
        {
            lista_timeline.visibility = View.GONE
            empty_view_timeline.visibility = View.VISIBLE
        }
    }
}
