package com.example.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.RecyclerView

class RecetaAdapter(private val lista:List<Receta>): RecyclerView.Adapter<RecetaAdapter.RecetaViewHolder>() {

    var onClickBorrarElemento: ((Receta) -> Unit)? = null
    var onClickEditarElemento: ((Receta) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecetaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.elemento_receta, parent, false)
        return RecetaViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: RecetaViewHolder,
        position: Int
    ) {
        val persona = lista[position]
        holder.nombre?.text = persona.nombre
        holder.tiempo?.text = persona.tiempo
        holder.dificultad?.text = persona.dificultad
        holder.icono?.setColorFilter(persona.color.toColorInt())
        holder.foto?.setImageResource(persona.foto)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    inner class RecetaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombre: TextView? = itemView.findViewById(R.id.tvNombre)
        val tiempo : TextView?= itemView.findViewById(R.id.tvTiempo)
        val dificultad : TextView? = itemView.findViewById(R.id.tvDificultad)
        val icono : ImageView? = itemView.findViewById(R.id.ivIcono)
        val foto: ImageView? = itemView.findViewById(R.id.ivFoto)
        val btEditar: Button =  itemView.findViewById(R.id.btEditar)
        val btBorrar: Button =  itemView.findViewById(R.id.btBorrar)

        init {
            // El click del boton editar
            btEditar?.setOnClickListener{
                val pos = absoluteAdapterPosition
                if (pos != RecyclerView.NO_POSITION) {
                    val item = lista[absoluteAdapterPosition]
                    onClickEditarElemento?.invoke(item)
                }
            }

            // El click del boton borrar
            btBorrar?.setOnClickListener{
                val pos = absoluteAdapterPosition
                if (pos != RecyclerView.NO_POSITION) {
                    val item = lista[absoluteAdapterPosition]
                    onClickBorrarElemento?.invoke(item)
                }
            }
        }
    }
}