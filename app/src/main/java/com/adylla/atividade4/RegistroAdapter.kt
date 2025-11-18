package com.adylla.atividade4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adylla.atividade4.databinding.EstiloRecyclerviewPaginaDiarioBinding

class RegistroAdapter: RecyclerView.Adapter<RegistroAdapter.MyViewHolder> (){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {

        val view = EstiloRecyclerviewPaginaDiarioBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int
    ) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    inner class MyViewHolder(val binding: EstiloRecyclerviewPaginaDiarioBinding): RecyclerView.ViewHolder(binding.root){

    }

}