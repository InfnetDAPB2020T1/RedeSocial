package com.example.redesocial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.redesocial.util.Validacoes
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_cadastro.*
import me.ibrahimsn.particle.ParticleView

class Cadastro : AppCompatActivity() {

    private lateinit var particleView: ParticleView
    private lateinit var auth : FirebaseAuth

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
                        criarConta(email, password)
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

    fun criarConta(email : String, password : String){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success
                    var intent = Intent(this,TelaPrincipal::class.java)
                    startActivity(intent)

                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(baseContext, "Usuário não foi cadastrado!",
                        Toast.LENGTH_SHORT).show()
                }

            }
    }
}
