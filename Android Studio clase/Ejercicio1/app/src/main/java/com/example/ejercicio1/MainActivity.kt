package com.example.ejercicio1

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


        val flores=listOf(
            Flor("Azalea",R.string.descripcion_azalea.toString(),R.drawable.azalea),
            Flor("Jacinto",R.string.descripcion_jacinto.toString(),R.drawable.jacinto),
            Flor("Margarita",R.string.descripcion_margarita.toString(),R.drawable.margarita)
        )


        val rv = findViewById<RecyclerView>(R.id.rvFlores)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = FlorAdapter(flores)
    }
}

