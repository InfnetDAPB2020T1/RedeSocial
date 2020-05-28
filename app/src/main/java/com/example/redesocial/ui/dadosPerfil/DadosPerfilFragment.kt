package com.example.redesocial.ui.dadosPerfil


import android.app.Activity
import android.content.Intent
import android.os.AsyncTask
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
import com.example.redesocial.models.Perfil
import com.example.redesocial.services.OperacoesPerfilService
import com.example.redesocial.ui.carregamentoalerta.LoadingAlerta
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_dados_perfil.*


/**
 * A simple [Fragment] subclass.
 */
class DadosPerfilFragment : Fragment() {

    var auth = FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_dados_perfil, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        BuscarInformacoesPerfilAsync(activity!!,auth.currentUser!!.uid).execute()

        btn_logoff.setOnClickListener {
            auth.signOut()
            var intent = Intent(activity!!,MainActivity::class.java)
            startActivity(intent)
        }
    }

    class BuscarInformacoesPerfilAsync(activity: Activity,serieGerado : String) : AsyncTask<Void, Void, Perfil?>()
    {
        var activity = activity
        var serieGerado = serieGerado
        var dialogApi = LoadingAlerta(activity)

        override fun onPreExecute() {
            super.onPreExecute()
            dialogApi.startLoadingDialog("Buscando dados do usuario...")
        }

        override fun doInBackground(vararg params: Void?): Perfil? {
            var perfil = OperacoesPerfilService.getInstance().buscarPerfilPorSerie(activity,serieGerado)
            dialogApi.dismiss()
            return perfil
        }

        override fun onPostExecute(result: Perfil?) {
            super.onPostExecute(result)

            if(result != null)
            {
                activity.findViewById<TextView>(R.id.nome_dados).text = result.nome
                activity.findViewById<TextView>(R.id.email_dados).text = result.email
                activity.findViewById<TextView>(R.id.dataNascimento_dados).text = result.dataNascimento
                activity.findViewById<TextView>(R.id.text_sobre_dados).text = result.sobre
            }

        }

    }


}
