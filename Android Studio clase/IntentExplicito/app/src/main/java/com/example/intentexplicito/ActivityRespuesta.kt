package com.example.intentexplicito

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class ActivityRespuesta : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_activityrespuesta)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Obtenemos el intent que comenzó este Activity
        val intent=getIntent()

        val extras = intent.getBundleExtra("datos")

        //Obtenemos una referencia al textView donde queremos escribir
        val tvNombre: TextView =findViewById(R.id.tvNombreRecibido)
        val tvEdad: TextView=findViewById(R.id.tvEdadRecibida)
        val etTexto: TextInputEditText =findViewById(R.id.etTexto)


        val texto = extras?.getString("nombre")
        val edad = extras?.getInt("edad")

        //Obtenemos el valor envíado por el primer Activity
        //val texto = intent.getStringExtra("nombre")
        //val edad = intent.getIntExtra("edad",0)

        //Establecemmos el valor del TextView con el texto recibido
        tvNombre.text= texto
        tvEdad.text = edad.toString()

        etTexto?.append(texto+"\n")
        etTexto?.append(edad.toString()+"\n")

        val btRegresar: Button = findViewById(R.id.btRegresar)
        btRegresar.setOnClickListener {
            finish()
        }
    }
}