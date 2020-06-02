package com.bemteviinfnet.redesocial.ui.editarPerfil


import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.AsyncTask
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController

import com.bemteviinfnet.redesocial.R
import com.bemteviinfnet.redesocial.models.Perfil
import com.bemteviinfnet.redesocial.services.OperacoesPerfilService
import com.bemteviinfnet.redesocial.ui.carregamentoalerta.LoadingAlerta
import com.bemteviinfnet.redesocial.viewmodel.PerfilViewModel
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.fragment_editar_perfil.*
import java.io.IOException


/**
 * A simple [Fragment] subclass.
 */
class EditarPerfilFragment : Fragment() {

    private var PICK_IMAGE = 1
    private var TAKE_PICTURE = 2
    private  lateinit var perfilViewModel: PerfilViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_editar_perfil, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity!!.let { act->
            perfilViewModel = ViewModelProviders.of(act)
                .get(PerfilViewModel::class.java) }

        if(perfilViewModel.perfilAtual!!.nome == perfilViewModel.perfilAtual!!.email)
            activity!!.findViewById<TextView>(R.id.username_editar).text = perfilViewModel.perfilAtual!!.email
        else
            activity!!.findViewById<TextView>(R.id.username_editar).text = perfilViewModel.perfilAtual!!.nome

        activity!!.findViewById<TextView>(R.id.dataNasc_editar).text = perfilViewModel.perfilAtual!!.dataNascimento
        activity!!.findViewById<TextView>(R.id.sobre_editar).text = perfilViewModel.perfilAtual!!.sobre

        btn_galeria.setOnClickListener {
            var galeria = Intent()
            galeria.setType("image/*")
            galeria.action = Intent.ACTION_GET_CONTENT

            startActivityForResult(Intent.createChooser(galeria,"Escolha a foto"),PICK_IMAGE)
        }

        btn_tirar_foto.setOnClickListener {
            Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
                takePictureIntent.resolveActivity(activity!!.packageManager)?.also {
                    startActivityForResult(takePictureIntent, TAKE_PICTURE)
                }
            }
        }

        btn_atlz_perfil.setOnClickListener {
            var nome = activity!!.findViewById<TextView>(R.id.username_editar).text.toString()
            var dataNasc = activity!!.findViewById<TextView>(R.id.dataNasc_editar).text.toString()
            var sobre = activity!!.findViewById<TextView>(R.id.sobre_editar).text.toString()
            AtualizarPerfilAsync(activity!!,perfilViewModel,nome,dataNasc, sobre).execute()
        }

        subscribe()
    }

    fun subscribe()
    {
        perfilViewModel.modificado.observe(this, Observer {
            if (perfilViewModel.perfilAtual != null) {
                var nav: NavigationView = activity!!.findViewById(R.id.nav_view)
                nav.removeHeaderView(nav.getHeaderView(0))
                var header = nav.inflateHeaderView(R.layout.nav_header_tela_principal)

                var menuUsername = header.findViewById<TextView>(R.id.header_username)
                var menuEmail = header.findViewById<TextView>(R.id.header_email)

                menuUsername.text = perfilViewModel.perfilAtual!!.nome
                menuEmail.text = perfilViewModel.perfilAtual!!.email
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK )
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
        else if (requestCode == TAKE_PICTURE && resultCode == Activity.RESULT_OK) {
            val imageBitmap = data!!.extras!!.get("data") as Bitmap
            foto_perfil_atlz.setImageBitmap(imageBitmap)
        }

    }

    class AtualizarPerfilAsync(activity: Activity,perfilViewModel: PerfilViewModel, nome : String, dataNasc : String,sobre : String) : AsyncTask<Void, Void, Boolean>()
    {
        var activity = activity
        var perfilViewModel = perfilViewModel
        var nome = nome
        var dataNasc = dataNasc
        var sobre = sobre
        var perfil : Perfil? = null
        var dialogApi = LoadingAlerta(activity)

        override fun onPreExecute() {
            super.onPreExecute()



            dialogApi.startLoadingDialog("Atualizando informações...")
        }

        override fun doInBackground(vararg params: Void?): Boolean {

            perfil = Perfil(perfilViewModel.perfilAtual!!.id,perfilViewModel.perfilAtual!!.serieGerado,
                        nome,dataNasc,perfilViewModel.perfilAtual!!.email,sobre,perfilViewModel.perfilAtual!!.foto)

            var sucesso = OperacoesPerfilService.getInstance().atualizarPerfil(activity, perfil!!)
            dialogApi.dismiss()
            return sucesso
        }

        override fun onPostExecute(result: Boolean) {
            super.onPostExecute(result)

            if(result)
            {
                var atual = perfilViewModel.perfilAtual!!

                atual.nome = perfil!!.nome
                atual.dataNascimento = perfil!!.dataNascimento
                atual.sobre = perfil!!.sobre

                perfilViewModel.setModificado()
                activity.findNavController(R.id.nav_host_fragment).navigate(R.id.nav_dados_perfil)
            }
            else
                Toast.makeText(activity, "Ocorreu um problema na atualização dos dados!", Toast.LENGTH_SHORT).show()

        }

    }
}
