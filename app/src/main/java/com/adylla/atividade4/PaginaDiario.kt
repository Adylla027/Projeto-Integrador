package com.adylla.atividade4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.adylla.atividade4.databinding.FragmentPaginaDiarioBinding
import com.google.android.gms.tasks.Task
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

class PaginaDiario : Fragment() {

    private var _binding: FragmentPaginaDiarioBinding? = null
    private val binding get() = _binding!!

    private lateinit var registroAdapter: RegistroAdapter

    private lateinit var reference: DatabaseReference

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

        reference = Firebase.database.reference
        auth = Firebase.auth

        navRegistros()
        initRecyclerViewRegistro()
        getRegistro()

        var menuAberto = false

        binding.floatBtnAdicionar.setOnClickListener {
            menuAberto = !menuAberto
            binding.linearLayoutFloatBtn.visibility =
                if (menuAberto) View.VISIBLE else View.GONE
        }

        binding.tollbarDiario.setOnClickListener{
            findNavController().navigateUp()
        }


    }

    private fun initRecyclerViewRegistro(){

        registroAdapter = RegistroAdapter{ registro ->
            optionSelected(registro)

        }

        binding.recyclerViewRegistro.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = registroAdapter
            setHasFixedSize(true)
        }

    }

    private fun optionSelected(registro: RegistroDiario){

        val action = PaginaDiarioDirections.actionPaginaDiarioToFragmentPaginaEscritaDiario()

        findNavController().navigate(action)
    }

    private fun getRegistro() {
        reference
            .child("registros")
            .child(auth.currentUser?.uid ?: "")
            .addValueEventListener(object: ValueEventListener{

                override fun onDataChange(snapshot: DataSnapshot) {
                    val registroList = mutableListOf<RegistroDiario>()

                    for (ds in snapshot.children){
                        val registro = ds.getValue(RegistroDiario::class.java)
                        if (registro != null) registroList.add(registro)
                    }
                    registroAdapter.submitList(registroList)

                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(requireContext(), "Erro ao carregar dados", Toast.LENGTH_SHORT).show()
                }

            })
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