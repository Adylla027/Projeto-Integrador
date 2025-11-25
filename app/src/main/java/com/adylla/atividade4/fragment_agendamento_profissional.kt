package com.adylla.atividade4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.card.MaterialCardView


class fragment_agendamento_profissional : Fragment() {
    private lateinit var cardConsultasDia: MaterialCardView
        private lateinit var txtMensagem: TextView
        private lateinit var txtTitulo: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_agendamento_profissional,container,false)

        cardConsultasDia = view.findViewById(R.id.cardConsultasDia)
        txtMensagem = view.findViewById(R.id.txtMensagem)
        txtTitulo = view.findViewById(R.id.txtTitulo)

        val consultas = mutableListOf<String>()

        consultas.add("Consulta com o Dr. João - 10:00 ")
        consultas.add("Consulta com o Dra. Maria - 14:30 ")


        val txtMensagem = view.findViewById<TextView>(R.id.txtMensagem)

        if(consultas.isEmpty()){

            txtMensagem.visibility = View.VISIBLE
            txtMensagem.text = "Nenhuma consulta marcada para este dia"
        }else{
            txtMensagem.text = consultas.joinToString("\n")
        }


        return view



    }

}