package com.example.redesocial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import androidx.transition.Fade
import androidx.transition.TransitionSet

class TelaInicial : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_inicial)
        Handler().postDelayed({
            val i = Intent(this@TelaInicial, MainActivity::class.java)
            TransitionSet().apply {
                addTransition(Fade())

                addTarget(R.id.telaLogin)
            }
            startActivity(i)
            finish()
        }, 3000)
    }
}
