package com.example.agendamentoclinica

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Configura o botão de AGENDAR
        val btnAgendar = findViewById<Button>(R.id.btnAgendar)
        btnAgendar.setOnClickListener {
            val intent = Intent(this, FormActivity::class.java)
            startActivity(intent)
        }

        // 2. Configura o botão de VER LISTA
        val btnLista = findViewById<Button>(R.id.btnLista)
        btnLista.setOnClickListener {
            // Navega para a tela de Lista
            val intent = Intent(this, ListaActivity::class.java)
            startActivity(intent)
        }
    }
}