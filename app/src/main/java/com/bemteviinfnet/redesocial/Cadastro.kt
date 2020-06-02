package com.bemteviinfnet.redesocial

import android.app.Activity
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bemteviinfnet.redesocial.models.Perfil
import com.bemteviinfnet.redesocial.services.OperacoesPerfilService
import com.bemteviinfnet.redesocial.ui.carregamentoalerta.LoadingAlerta
import com.bemteviinfnet.redesocial.util.Validacoes
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_cadastro.*
import me.ibrahimsn.particle.ParticleView


class Cadastro : AppCompatActivity() {

    private lateinit var particleView: ParticleView
    private lateinit var auth : FirebaseAuth
    private lateinit var dialog : LoadingAlerta

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        particleView = findViewById(R.id.particleViewCadastro)
        auth = FirebaseAuth.getInstance()

        btnEnviaCadastro.setOnClickListener {

            var password = passwordCadastro.text.toString()
            var email = emailCadastro.text.toString()

            if(Validacoes.verificaCampoPreenchido(password) &&
                Validacoes.verificaCampoPreenchido(email))
            {
                if(Validacoes.verificaEmail(email))
                {
                    var dadosValidos = Validacoes.verificaDadosCadastro(email,password)

                    if(dadosValidos == null)
                    {
                        dialog = LoadingAlerta(this)
                        dialog.startLoadingDialog("Criando usuário...")
                        criarConta(email, password,this,dialog)
                    }
                    else
                        Toast.makeText(this,dadosValidos, Toast.LENGTH_SHORT).show()
                }
                else
                    Toast.makeText(this,"Formato de email inválido!", Toast.LENGTH_SHORT).show()
            }
            else
                Toast.makeText(this,"Preencha corretamente os campos!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()
        particleView.resume()
    }

    override fun onPause() {
        super.onPause()
        particleView.pause()
    }

    fun criarConta(email : String, password : String, activity: Activity,dialog : LoadingAlerta){

        criarNoFirebase(email, password,dialog, activity)

    }

    fun criarNoFirebase(email : String, password : String, dialog : LoadingAlerta, activity: Activity){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    dialog.dismiss()
                    criarPerfilApiAsync(email,password,auth.uid.toString(),activity).execute()

                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(baseContext, "Usuário não foi cadastrado!",
                        Toast.LENGTH_SHORT).show()
                }

            }
    }



    class BuscarNaApiAsync(email : String, password : String, activity: Activity) : AsyncTask<Void, Void, Perfil>() {

        var email = email
        var password = password
        var activity = activity

        override fun doInBackground(vararg params: Void?): Perfil {
            var perfil = OperacoesPerfilService.getInstance().buscarPerfil(activity, 1)
            return perfil!!
        }

        override fun onPostExecute(result: Perfil?) {
            super.onPostExecute(result)

            var intent = Intent(activity, TelaPrincipal::class.java)
            activity.startActivity(intent)
        }

    }

    class criarPerfilApiAsync(email : String, password : String, serieGerado : String, activity: Activity) : AsyncTask<Void, Void, Boolean>(){

        var perfil = Perfil(0,serieGerado,email,"01/01/2000",email,"Usuário da rede BemTeVi","foto")
        var activity = activity
        var dialogApi = LoadingAlerta(activity)

        override fun onPreExecute() {
            super.onPreExecute()
            dialogApi.startLoadingDialog("Criando perfil...")
        }

        override fun doInBackground(vararg params: Void?): Boolean {
            dialogApi.startLoadingDialog("Criando perfil...")
            var sucesso = OperacoesPerfilService.getInstance().criarPerfil(activity, perfil)
            dialogApi.dismiss()
            return sucesso
        }

        override fun onPostExecute(result: Boolean?) {
            super.onPostExecute(result)

            if(result == true)
            {
                var intent = Intent(activity, TelaPrincipal::class.java)
                activity.startActivity(intent)
            }

            var intent = Intent(activity, MainActivity::class.java)
            activity.startActivity(intent)
        }
    }

}

