package com.example.redesocial.ui.convites


import android.content.ClipData
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.redesocial.R
import com.google.android.material.bottomnavigation.BottomNavigationView


/**
 * A simple [Fragment] subclass.
 */
class ConvitesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_convites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var barraNav = activity!!.findViewById<BottomNavigationView>(R.id.bottom_navigation_view)

        configureBottomNavigation(barraNav)
    }

    fun configureBottomNavigation(barraNav : BottomNavigationView)
    {
        barraNav.setupWithNavController(activity!!.findNavController(R.id.host_fragment))
    }

}
