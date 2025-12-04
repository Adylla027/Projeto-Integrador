package com.adylla.atividade4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.adylla.atividade4.databinding.ItemConsultaBinding
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

class ConsultaAdapter(

    private val itemClick: (Consulta) -> Unit = {}
) : RecyclerView.Adapter<ConsultaAdapter.ConsultaViewHolder>() {

    private val agendaList = mutableListOf<Consulta>()

    fun submitList(newList: List<Consulta>) {
        agendaList.clear()
        agendaList.addAll(newList)
        notifyDataSetChanged()
    }

    inner class ConsultaViewHolder(val binding: ItemConsultaBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConsultaViewHolder {
        val binding = ItemConsultaBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ConsultaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ConsultaViewHolder, position: Int) {
        val consulta = agendaList[position]

        holder.binding.textNomePaciente.text = consulta.nomePaciente
        holder.binding.textViewProfissional.text = consulta.profissional
        holder.binding.textViewData.text = consulta.data
        holder.binding.textViewHorario.text = consulta.horario
        holder.binding.textViewLocal.text = consulta.local

        holder.binding.root.setOnClickListener {
            itemClick(consulta)
        }
    }

    override fun getItemCount(): Int = agendaList.size

}