package com.adylla.atividade4

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.view.View

import androidx.recyclerview.widget.RecyclerView

class ConsultaAdapter(
    private val listaConsultas: List<Consulta>
): RecyclerView.Adapter<ConsultaAdapter.ConsultaViewHolder>(){


    // ViewHolder — controla cada item da lista
    class  ConsultaViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        val textNomePaciente: TextView = itemView.findViewById(R.id.textNomePaciente)
        val textHorario: TextView = itemView.findViewById(R.id.textHorario)

    }
    // Cria o modelo visual (inflar o item_consulta)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConsultaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_consulta,parent,false)

        return ConsultaViewHolder(view)
    }

    // Preenche cada item com os dados
    override fun onBindViewHolder(holder: ConsultaViewHolder, position: Int) {
        val consulta = listaConsultas[position]

        holder.textNomePaciente.text = consulta.nomePaciente?: "Paciente Desconhecido"
        holder.textHorario.text = consulta.horario?: "--:--"
    }

    override fun getItemCount(): Int = listaConsultas.size

}

