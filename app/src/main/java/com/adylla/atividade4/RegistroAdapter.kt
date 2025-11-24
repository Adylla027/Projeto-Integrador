package com.adylla.atividade4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.adylla.atividade4.databinding.EstiloRecyclerviewPaginaDiarioBinding

class RegistroAdapter(
    private val itemClick: (RegistroDiario, Int) -> Unit
): RecyclerView.Adapter<RegistroAdapter.RegistroViewHolder> (){

    private val registro = mutableListOf<RegistroDiario>()

    fun submitList(newList: List<RegistroDiario>){
        registro.clear()
        registro.addAll(newList)
    }

    inner class RegistroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val title = itemView.findViewById<TextView>(R.id.textTitulo_diario)
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RegistroViewHolder {

       val view = LayoutInflater.from(parent.context).inflate(R.layout.estilo_recyclerview_pagina_diario, parent, false)
        return RegistroViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: RegistroViewHolder,
        position: Int
    ) {
        val diario = registro[position]
        holder.title.text = diario.title

        holder.itemView.setOnClickListener {
            itemClick(diario)
        }
    }

    override fun getItemCount(): Int = registro.size


}