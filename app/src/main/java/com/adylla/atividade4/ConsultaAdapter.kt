package com.adylla.atividade4

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ConsultaAdapter(
    private val listaConsultas: List<Consulta>
): RecyclerView.Adapter<ConsultaAdapter.ConsultaViewHolder>(){


    // ViewHolder — controla cada item da lista
    class  ConsultaViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        val textNomePaciente: TextView = itemView.findViewById(R.id.textNomePaciente)
        val textHorario: TextView = itemView.findViewById(R.id.TextHorario)

    }

    
}

