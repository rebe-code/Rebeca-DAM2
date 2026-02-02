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

    val estado = mutableListOf(false,false,false,false)

    val frutas = listOf(R.drawable.fruta1,R.drawable.fruta2,R.drawable.fruta3,R.drawable.fruta4)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.ivImagen)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        ivCasilla1 = findViewById(R.id.ivCasilla1)
        ivCasilla1.setOnClickListener {
            if (estado[0] == false) {
                ivCasilla1.scaleType = ImageView.ScaleType.CENTER
                ivCasilla1.setImageResource(frutas[0])
                estado[0] = true
            } else {
                ivCasilla1.scaleType = ImageView.ScaleType.CENTER_CROP
                ivCasilla1.setImageResource(R.drawable.fondomemorion)
                estado[0] = false
            }
        }
        ivCasilla2.setOnClickListener {
            if (estado[1] == false) {
                ivCasilla2.scaleType = ImageView.ScaleType.CENTER
                ivCasilla2.setImageResource(frutas[1])
                estado[1] = true
            } else {
                ivCasilla2.scaleType = ImageView.ScaleType.CENTER_CROP
                ivCasilla2.setImageResource(R.drawable.fondomemorion)
                estado[1] = false
            }
        }
        ivCasilla3.setOnClickListener {
            if (estado[2] == false) {
                ivCasilla3.scaleType = ImageView.ScaleType.CENTER
                ivCasilla3.setImageResource(frutas[2])
                estado[2] = true
            } else {
                ivCasilla3.scaleType = ImageView.ScaleType.CENTER_CROP
                ivCasilla3.setImageResource(R.drawable.fondomemorion)
                estado[2] = false
            }
        }
        ivCasilla4.setOnClickListener {
            if (estado[3]==false) {
                ivCasilla4.scaleType = ImageView.ScaleType.CENTER
                ivCasilla4.setImageResource(frutas[3])
                estado[3]=true
            }else{
                ivCasilla4.scaleType = ImageView.ScaleType.CENTER_CROP
                ivCasilla4.setImageResource(R.drawable.fondomemorion)
                estado[3]=false
            }
        }

    }
}