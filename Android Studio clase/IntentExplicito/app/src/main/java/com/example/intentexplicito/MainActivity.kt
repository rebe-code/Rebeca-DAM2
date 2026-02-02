package com.example.intentexplicito

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

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
        val btEnviar : Button = findViewById(R.id.btEnviar)
        btEnviar.setOnClickListener {
            val intent: Intent =Intent(this, ActivityRespuesta::class.java)

            val etNombre: EditText =findViewById(R.id.etNombre)
            val etEdad: EditText =findViewById(R.id.etEdad)

            val extras = Bundle()

            extras.apply {
                putString("nombre", etNombre.text.toString())
                putInt("edad",etEdad.text.toString().toInt())
            }

            intent.putExtra("datos",extras)
            startActivity(intent)
        }

    }
}