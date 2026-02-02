package com.example.recyclerview


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.RecyclerView

class PersonaAdapter(private  val lista:List<Persona>):RecyclerView.Adapter<PersonaAdapter.PersonaViewHolder>() {

    class PersonaViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val nombre: TextView?=itemView.findViewById(R.id.tvNombre)
        val ciudad: TextView?=itemView.findViewById(R.id.tvCiudad)
        val estado: TextView?=itemView.findViewById(R.id.tvEstado)
        val icono: ImageView?=itemView.findViewById(R.id.ivIcono)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.elemento_persona, parent, false)
        return PersonaViewHolder(view)
    }


    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: PersonaViewHolder, position: Int) {
       val persona=lista[position]
        holder.nombre?.text=persona.nombre
        holder.ciudad?.text=persona.ciudad
        holder.estado?.text=persona.estado
        holder.icono?.setColorFilter(persona.color.toColorInt())
    }

}