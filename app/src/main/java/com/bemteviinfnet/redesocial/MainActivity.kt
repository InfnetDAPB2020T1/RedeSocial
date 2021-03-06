package com.bemteviinfnet.redesocial

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bemteviinfnet.redesocial.ui.carregamentoalerta.LoadingAlerta
import com.bemteviinfnet.redesocial.util.Validacoes.Companion.verificaCampoPreenchido
import com.bemteviinfnet.redesocial.util.Validacoes.Companion.verificaEmail
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import me.ibrahimsn.particle.ParticleView


class MainActivity : AppCompatActivity() {

    private lateinit var particleView: ParticleView
    private lateinit var auth : FirebaseAuth
    private lateinit var dialog : LoadingAlerta

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        particleView = findViewById(R.id.particleView)

        auth = FirebaseAuth.getInstance()

        btnLogin.setOnClickListener{

            var email = emailLogin.text.toString()
            var password = passwordLogin.text.toString()

            if(verificaCampoPreenchido(email) && verificaEmail(email) && verificaCampoPreenchido(password))
            {
                dialog = LoadingAlerta(this)
                dialog.startLoadingDialog("Autenticando...")
                verificaLogin(email,password);
            }
            else
            {
                Toast.makeText(this,"Preencha corretamente os campos!",Toast.LENGTH_SHORT).show()
            }

        }

        btnIrParaCadastro.setOnClickListener{
            var i = Intent(this, Cadastro::class.java)
            startActivity(i)
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if(currentUser != null)
        {
            var intent = Intent(this,TelaPrincipal::class.java)
            startActivity(intent)
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

    fun verificaLogin(email : String, password : String)
    {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success
                    dialog.dismiss()
                    var intent = Intent(this,TelaPrincipal::class.java)
                    startActivity(intent)

                } else {
                    // If sign in fails, display a message to the user.
                    dialog.dismiss()
                    Toast.makeText(baseContext, "Usuário não encontrado!",
                        Toast.LENGTH_SHORT).show()
                }

            }
    }
}
