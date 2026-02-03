package com.example.recyclerview

import android.os.Bundle
import android.widget.Toast
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



        val recetas = listOf(
            Receta("#275447", "Tortilla de patata", "25 minutos", "Fácil", R.drawable.tortilla),
            Receta("#607d8b", "Lasaña casera", "50 minutos", "Media", R.drawable.lasana),
            Receta("#03a9f4", "Brownie de chocolate", "40 minutos", "Fácil", R.drawable.brownie),
            Receta("#C75447", "Paella mixta", "1 hora", "Difícil", R.drawable.paella),
            Receta("#775447", "Ensalada César", "15 minutos", "Fácil", R.drawable.ensalada),
            Receta("#F75447", "Sopa de verduras", "35 minutos", "Media", R.drawable.sopa),
            Receta("#A75447", "Pizza casera", "1 hora", "Media", R.drawable.pizza),
            Receta("#175447", "Crepes dulces", "20 minutos", "Fácil", R.drawable.crepes)
        )



        //Obtenemos una referencia al RecyclerView
        val rv = findViewById<RecyclerView>(R.id.rvListaRecetas)

        //Le asignamos un LayoutManager al RecyclerView de tipo LinearLayoutManager
        rv.layoutManager = LinearLayoutManager(this)
        //rv.layoutManager = GridLayoutManager(this, 2)

        var adaptador = RecetaAdapter(recetas)

        adaptador.onClickBorrarElemento = { receta ->
            Toast.makeText(this, "Vas a borrar la ${receta.nombre}", Toast.LENGTH_SHORT).show()
        }

        adaptador.onClickEditarElemento = { receta ->
            Toast.makeText(this, "Vas a editar la ${receta.nombre}", Toast.LENGTH_SHORT).show()
        }

        //Asignamos un adapter al RecyclerView
        rv.adapter = adaptador
    }
}