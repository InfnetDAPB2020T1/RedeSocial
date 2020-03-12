package com.example.redesocial.ui.timeline


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
class TimelineFragment : Fragment() {

    private lateinit var timelineViewModel: TimelineViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        timelineViewModel =
            ViewModelProviders.of(this).get(TimelineViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_slideshow, container, false)
        val textView: TextView = root.findViewById(R.id.text_slideshow)
        timelineViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }


}
