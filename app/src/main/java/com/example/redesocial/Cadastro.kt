package com.example.redesocial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.redesocial.util.Validacoes
import kotlinx.android.synthetic.main.activity_cadastro.*
import me.ibrahimsn.particle.ParticleView

class Cadastro : AppCompatActivity() {

    private lateinit var particleView: ParticleView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        particleView = findViewById(R.id.particleViewCadastro)

        btnEnviaCadastro.setOnClickListener {

            var username = usernameCadastro.text.toString()
            var password = passwordCadastro.text.toString()
            var email = emailCadastro.text.toString()

            if(Validacoes.verificaCampoPreenchido(username) && Validacoes.verificaCampoPreenchido(password) &&
                Validacoes.verificaCampoPreenchido(email))
            {
                if(Validacoes.verificaEmail(email))
                {
                    var dadosValidos = Validacoes.verificaDadosCadastro(username,email,password)

                    if(dadosValidos == null)
                    {
                        var i = Intent(this, TelaPrincipal::class.java)
                        startActivity(i)
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
}
