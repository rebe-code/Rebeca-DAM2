package com.example.dilogo1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
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

        var boton: Button = findViewById(R.id.btBotonDialogo)

        boton.setOnClickListener {
            //dialogoSimple()
            //dialogoListaOpcionUnica()
            //ejercicio1()
            //dialogoListaSeleccionMultiple()
            //dialogoPersonalizado()
            D2DialogFragment().show(supportFragmentManager,"Dialogo1")
        }
    }

        fun dialogoSimple(){
            val builder : AlertDialog.Builder = AlertDialog.Builder(this)
            builder.setMessage("Soy el mensaje").setTitle("Soy el título")
                .setNeutralButton("Mas tarde..."){dialog,wich->
                    Toast.makeText(this,"Has pulsado el botón neutro",Toast.LENGTH_SHORT).show()
                }
                .setPositiveButton("Aceptar"){dialog,wich->
                    Toast.makeText(this,"Has pulsado el botón positivo",Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("Cancelar"){dialog,wich->
                    Toast.makeText(this,"Has pulsado el botón negativo",Toast.LENGTH_SHORT).show()
                }

            val dialog: AlertDialog = builder.create()
            dialog.show()
        }


    fun dialogoListaOpcionUnica(){
        val builder : AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Soy el título")
            .setNegativeButton("Cancelar"){dialog,wich->
                Toast.makeText(this,"Has pulsado el botón negativo",Toast.LENGTH_SHORT).show()
            }
            .setItems(arrayOf("Item uno","Item dos","Item tres")){_,which->
                when (which){
                    0->Toast.makeText(this,"Has pulsado la primera opción",Toast.LENGTH_SHORT).show()
                    1->Toast.makeText(this,"Has pulsado la segunda opción",Toast.LENGTH_SHORT).show()
                    2->Toast.makeText(this,"Has pulsado la tercera opción",Toast.LENGTH_SHORT).show()
                }
            }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    val COLOR_ROJO = R.color.rojo
    val COLOR_VERDE = R.color.verde
    val COLOR_AZUL = R.color.azul
    val esteLayout = findViewById<ConstraintLayout>(R.id.main)

    fun ejercicio1() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Elige un color de fondo...")
            .setNegativeButton("Cancelar") { dialog, wich ->
                Toast.makeText(this, "Has pulsado el botón negativo", Toast.LENGTH_SHORT).show()
            }
            .setItems(arrayOf("Rojo", "Verde", "Azul")) { _, which ->
                when (which) {
                    0 -> {
                        esteLayout.setBackgroundColor(getResources().getColor(COLOR_ROJO, theme))
                        Toast.makeText(this, "Has elegido Rojo", Toast.LENGTH_SHORT).show()
                    }

                    1 -> {
                        esteLayout.setBackgroundColor(getResources().getColor(COLOR_VERDE, theme))
                        Toast.makeText(this, "Has elegido Verde", Toast.LENGTH_SHORT).show()
                    }

                    2 -> {
                        esteLayout.setBackgroundColor(getResources().getColor(COLOR_AZUL, theme))
                        Toast.makeText(this, "Has elegido Azul", Toast.LENGTH_SHORT).show()
                    }
                }

                val dialog: AlertDialog = builder.create()
                dialog.show()
            }
    }

    fun dialogoListaSeleccionMultiple(){
        val builder : AlertDialog.Builder = AlertDialog.Builder(this)
        var opciones = arrayOf("Item One","Item Two", "Item Three")
        var itemSeleccionados = booleanArrayOf(true,false,false)

        builder
            .setTitle("Soy el titulo")
            .setNegativeButton("Cancelar"){_,_->
                //Codigo de lo que queremos que se ejecute
                Toast.makeText(this,"Has pulsado cancelar",Toast.LENGTH_SHORT).show()
            }
            .setPositiveButton("Aceptar"){_,_->
                var cadena=""
                for(i in itemSeleccionados.indices){
                    if(itemSeleccionados[i])
                        cadena = cadena + opciones[i] + " seleccionado "
                }
                Toast.makeText(this,"$cadena",Toast.LENGTH_LONG).show()
            }
            .setMultiChoiceItems(
                opciones, itemSeleccionados) { dialog, which, isChecked ->
                val pulsado=opciones[which] //Obtenemos el nombre del item pulsado
                var estado= " que ha quedado desmarcado"
                if(isChecked) estado="que ha quedado marcado"
                Toast.makeText(this,"Se ha pulsado $pulsado $estado",Toast.LENGTH_SHORT).show()
            }

        val dialog: AlertDialog = builder.create()

        dialog.show()
    }

    fun dialogoPersonalizado(){
        val builder : AlertDialog.Builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.dialogo_login,null)

        builder.setView(dialogView)

        builder
            .setTitle("Soy el titulo")
            .setNegativeButton("Cancelar"){_,_->
                //Codigo de lo que queremos que se ejecute
                Toast.makeText(this,"Has pulsado cancelar",Toast.LENGTH_SHORT).show()
            }
            .setPositiveButton("Aceptar"){_,_->
                var etNombre:EditText = dialogView.findViewById(R.id.etUsuario)
                var etContra:EditText = dialogView.findViewById(R.id.etContra)
                if (etNombre.text.toString()=="usuario" && etContra.text.toString()=="1234"){
                    Toast.makeText(this,"Login correcto",Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(this,"Login incorrecto",Toast.LENGTH_LONG).show()
                }
            }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}

