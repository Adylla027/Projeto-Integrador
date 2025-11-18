package com.adylla.atividade4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.adylla.atividade4.databinding.FragmentPaginaDiarioBinding
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth

class PaginaDiario : Fragment() {

    private var _binding: FragmentPaginaDiarioBinding? = null
    private val binding get() = _binding!!

    /*private lateinit var task: Task

    private lateinit var reference: DatabaseReference*/

    private lateinit var auth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding = FragmentPaginaDiarioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navRegistros()

        var menuAberto = false

        binding.floatBtnAdicionar.setOnClickListener {
            menuAberto = !menuAberto
            binding.linearLayoutFloatBtn.visibility = if (menuAberto) View.VISIBLE else View.GONE
        }

        binding.tollbarDiario.setOnClickListener{
            findNavController().navigateUp()
        }


    }

    private fun navRegistros(){
        binding.btnTexto.setOnClickListener {
            findNavController().navigate(R.id.action_paginaDiario_to_fragment_pagina_escrita_diario)
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}