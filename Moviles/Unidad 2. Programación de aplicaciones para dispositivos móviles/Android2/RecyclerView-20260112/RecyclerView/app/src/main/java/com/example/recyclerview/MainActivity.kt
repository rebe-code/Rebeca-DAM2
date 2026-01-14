package com.example.recyclerview

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val personas = listOf(
            Persona("#275447","Rebeca","Oviedo","Activo"),
            Persona("#607d8b","Kevin","Oviedo","Activo"),
            Persona("#C75447","Paula","Oviedo","Activo"),
            Persona("#275447","Maria","Oviedo","Activo"),
            Persona("#275447","Juan","Oviedo","Activo"),
            Persona("#275447","Maite","Oviedo","Activo")
        )

        val rv = findViewById<RecyclerView>(R.id.rvListaPersonas)
        rv.layoutManager=LinearLayoutManager(this)
        rv.adapter = PersonaAdapter(personas)
    }
}