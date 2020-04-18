package com.example.redesocial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import me.ibrahimsn.particle.ParticleView

class MainActivity : AppCompatActivity() {

    private lateinit var particleView: ParticleView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        particleView = findViewById(R.id.particleView)

        btnLogin.setOnClickListener{

            var username = usernameLogin.text.toString()
            var password = passwordLogin.text.toString()

            if(verificaCampoPreenchido(username) && verificaCampoPreenchido(password))
            {
                if(verificaUsuario(username,password))
                {
                    var i = Intent(this, TelaPrincipal::class.java)
                    startActivity(i)
                }
                else
                {
                    Toast.makeText(this,"Usuário não cadastrado!",Toast.LENGTH_SHORT).show()
                }
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

    fun verificaUsuario(username : String, password : String) : Boolean
    {
        var usuarioRegistrado = "user"
        var senhaRegistrada = "senha"

        if(username.trim() == usuarioRegistrado && password.trim() == senhaRegistrada)
            return true;

        return false
    }

    fun verificaCampoPreenchido(param : String) : Boolean
    {
        if(param != null && param.trim().isNotEmpty())
            return true

        return false
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
