package com.example.redesocial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_cadastro.*
import me.ibrahimsn.particle.ParticleView

class Cadastro : AppCompatActivity() {

    private lateinit var particleView: ParticleView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        particleView = findViewById(R.id.particleViewCadastro)

        btnEnviaCadastro.setOnClickListener {
            var i = Intent(this, TelaPrincipal::class.java)
            startActivity(i)
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
