package com.example.guiasimagenes

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var ivCasilla1: ImageView
    lateinit var ivCasilla2: ImageView
    lateinit var ivCasilla3: ImageView
    lateinit var ivCasilla4: ImageView



    val animales = listOf(R.drawable.elefante,R.drawable.elefante1,R.drawable.jirafa,R.drawable.jirafa1)


    val estado = mutableListOf(false,false,false,false)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainLayout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        ivCasilla1 = findViewById(R.id.ivCasilla1)
        ivCasilla2 = findViewById(R.id.ivCasilla2)
        ivCasilla3 = findViewById(R.id.ivCasilla3)
        ivCasilla4 = findViewById(R.id.ivCasilla4)


        inicializar()


        ivCasilla1.setOnClickListener { cambiarEstado(0, ivCasilla1) }
        ivCasilla2.setOnClickListener { cambiarEstado(1, ivCasilla2) }
        ivCasilla3.setOnClickListener { cambiarEstado(2, ivCasilla3) }
        ivCasilla4.setOnClickListener { cambiarEstado(3, ivCasilla4) }
    }


    private fun inicializar() {
        ivCasilla1.setImageResource(R.drawable.fondomemorion)
        ivCasilla2.setImageResource(R.drawable.fondomemorion)
        ivCasilla3.setImageResource(R.drawable.fondomemorion)
        ivCasilla4.setImageResource(R.drawable.fondomemorion)
    }


    private fun cambiarEstado(indice: Int, imageView: ImageView) {
        if (!estado[indice]) {

            imageView.setImageResource(animales[indice])
            estado[indice] = true
        } else {

            imageView.setImageResource(R.drawable.fondomemorion)
            estado[indice] = false
        }
        }
    }
