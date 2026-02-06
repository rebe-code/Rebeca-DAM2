package com.example.ejercicio1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FlorAdapter(private val lista:List<Flor>): RecyclerView.Adapter<FlorAdapter.FlorViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FlorViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_elemeto_flor, parent, false)
        return FlorViewHolder(view)

    }

    override fun onBindViewHolder(
        holder: FlorViewHolder,
        position: Int
    ) {
        val flor = lista[position]
        holder.nombre?.text = flor.nombre
        holder.descripcion?.text = flor.descripcion
        holder.imagen?.setImageResource(flor.imagen)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    class FlorViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val nombre: TextView? = itemView.findViewById(R.id.tvFlores)
        val descripcion: TextView? = itemView.findViewById(R.id.textDescripcion)
        val imagen: ImageView? = itemView.findViewById(R.id.imgFlor)
    }


}