package com.example.dilogo1

import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class D2DialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder: AlertDialog.Builder = AlertDialog.Builder(it)
            val inflater = layoutInflater
            val dialogView = inflater.inflate(R.layout.dialogo_login, null)

            builder.setView(dialogView)
                .setTitle("Soy el titulo")
                .setNegativeButton("Cancelar") { _, _ ->
                    // Usamos context en lugar de this
                    Toast.makeText(context, "Has pulsado cancelar", Toast.LENGTH_SHORT).show()
                }
                .setPositiveButton("Aceptar") { _, _ ->
                    val etNombre: EditText = dialogView.findViewById(R.id.etUsuario)
                    val etContra: EditText = dialogView.findViewById(R.id.etContra)

                    if (etNombre.text.toString() == "usuario" && etContra.text.toString() == "1234") {
                        Toast.makeText(context, "Login correcto", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(context, "Login incorrecto", Toast.LENGTH_LONG).show()
                    }
                }

            builder.create()
        } ?: throw IllegalStateException("El activity no puede tener valor nulo")
    }
}
