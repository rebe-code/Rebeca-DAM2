package com.example.ejerciciolistaseleccionmultiple

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
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
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.button)
        btn.setOnClickListener{
            dialogo()
        }
    }



    fun dialogo(){
        val builder : AlertDialog.Builder = AlertDialog.Builder(this)
        var opciones = arrayOf("Imagen1","Imagen2", "Imagen3")
        var itemSeleccionados = booleanArrayOf(false,false,false)

        builder
            .setTitle("Activar o desactivar imágenes")
            .setNegativeButton("Cancelar"){_,_->
                //Codigo de lo que queremos que se ejecute
                Toast.makeText(this,"Has pulsado cancelar", Toast.LENGTH_SHORT).show()
            }
            .setPositiveButton("Aceptar"){_,_->
                var cadena=""
                for(i in itemSeleccionados.indices){
                    if(itemSeleccionados[i])
                        cadena = cadena + opciones[i] + " seleccionado "
                }
                Toast.makeText(this,"$cadena", Toast.LENGTH_LONG).show()
            }
            .setMultiChoiceItems(
                opciones, itemSeleccionados) { dialog, which, isChecked ->
                val pulsado=opciones[which] //Obtenemos el nombre del item pulsado
                var estado= " que ha quedado desmarcado"
                if(isChecked) estado="que ha quedado marcado"
                Toast.makeText(this,"Se ha pulsado $pulsado $estado", Toast.LENGTH_SHORT).show()
            }

        val dialog: AlertDialog = builder.create()

        dialog.show()
    }
}