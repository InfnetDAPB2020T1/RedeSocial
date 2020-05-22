package com.example.redesocial.ui.editarPerfil


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.example.redesocial.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_editar_perfil.*
import java.io.IOException


/**
 * A simple [Fragment] subclass.
 */
class EditarPerfilFragment : Fragment() {

    private var PICK_IMAGE = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_editar_perfil, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_galeria.setOnClickListener {
            var galeria = Intent()
            galeria.setType("image/*")
            galeria.action = Intent.ACTION_GET_CONTENT

            startActivityForResult(Intent.createChooser(galeria,"Escolha a foto"),PICK_IMAGE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 1 && resultCode == Activity.RESULT_OK )
        {
            var imageUri = data!!.data
            try{
                var bitmap = MediaStore.Images.Media.getBitmap(activity!!.contentResolver,imageUri)
                foto_perfil_atlz.setImageBitmap(bitmap)
            }
            catch (ex : IOException)
            {
                ex.printStackTrace()
            }
        }
    }


}
