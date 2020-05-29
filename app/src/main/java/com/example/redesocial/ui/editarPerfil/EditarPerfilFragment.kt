package com.example.redesocial.ui.editarPerfil


import android.app.Activity
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders

import com.example.redesocial.R
import com.example.redesocial.models.Perfil
import com.example.redesocial.services.OperacoesPerfilService
import com.example.redesocial.ui.carregamentoalerta.LoadingAlerta
import com.example.redesocial.viewmodel.PerfilViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_editar_perfil.*
import java.io.IOException


/**
 * A simple [Fragment] subclass.
 */
class EditarPerfilFragment : Fragment() {

    private var PICK_IMAGE = 1
    var auth = FirebaseAuth.getInstance()
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

        /*btn_atlz_perfil.setOnClickListener {
            AtualizarPerfilAsync(activity!!,12)
        }*/
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

    class AtualizarPerfilAsync(activity: Activity,id : Int) : AsyncTask<Void, Void, Perfil?>()
    {
        var activity = activity
        var perfilId = id
        var dialogApi = LoadingAlerta(activity)

        override fun onPreExecute() {
            super.onPreExecute()

            var nome = activity.findViewById<TextView>(R.id.username_editar).text
            var dataNasc = activity.findViewById<TextView>(R.id.dataNasc_editar).text
            var sobre = activity.findViewById<TextView>(R.id.sobre_editar).text

            // Criar perfil, ou pegar o perfil guardado em um LiveData

            dialogApi.startLoadingDialog("Atualizando informações...")
        }

        override fun doInBackground(vararg params: Void?): Perfil? {
            var perfil = OperacoesPerfilService.getInstance().buscarPerfil(activity,perfilId)
            dialogApi.dismiss()
            return perfil
        }

        override fun onPostExecute(result: Perfil?) {
            super.onPostExecute(result)

            if(result != null)
               Toast.makeText(activity, "Dados alterados!", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(activity, "Ocorreu um problema na atualização dos dados!", Toast.LENGTH_SHORT).show()


        }

    }
}
