package com.adylla.atividade4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.adylla.atividade4.databinding.EstiloRecyclerviewPaginaDiarioBinding

class RegistroAdapter(
    private val registroSelected: (RegistroDiario, Int) -> Unit
): ListAdapter<RegistroDiario, RegistroDiario.MyViewHolder>(DIFF_CALBACK){

    companion object{
        private val DIFF_CALBACK = object: DiffUtil.ItemCallback<RegistroDiario>(){
            override fun areItemsTheSame(
                oldItem: RegistroDiario,
                newItem: RegistroDiario
            ): Boolean {
                return oldItem.id == newItem.id && oldItem.title == newItem.title

            }

            override fun areContentsTheSame(
                oldItem: RegistroDiario,
                newItem: RegistroDiario
            ): Boolean {
                return oldItem == newItem && oldItem.title == newItem.title
            }
        }

    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {

        val binding= EstiloRecyclerviewPaginaDiarioBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int
    ) {
        val registro = getItem(position)
        holder.binding.textTituloDiario.text = registro.title
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    inner class MyViewHolder(val binding: EstiloRecyclerviewPaginaDiarioBinding): RecyclerView.ViewHolder(binding.root){

    }

}