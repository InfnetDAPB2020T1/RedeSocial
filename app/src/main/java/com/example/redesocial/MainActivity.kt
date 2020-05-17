package com.example.redesocial

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.redesocial.util.Validacoes.Companion.verificaCampoPreenchido
import com.example.redesocial.util.Validacoes.Companion.verificaEmail
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*
import me.ibrahimsn.particle.ParticleView


class MainActivity : AppCompatActivity() {

    private lateinit var particleView: ParticleView
    private lateinit var auth : FirebaseAuth

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
                    var intent = Intent(this,TelaPrincipal::class.java)
                    startActivity(intent)

                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(baseContext, "Usuário não encontrado!",
                        Toast.LENGTH_SHORT).show()
                }

            }
    }
}
