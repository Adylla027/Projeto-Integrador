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
import com.adylla.atividade4.databinding.FragmentVisualizarAgendamentoBinding
import com.adylla.atividade4.databinding.FragmentVisualizarDiarioBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

class VisualizarAgendamento : Fragment() {

    private var _binding: FragmentVisualizarAgendamentoBinding? = null
    private val binding get() = _binding!!

    private lateinit var ConsultaAdapter: ConsultaAdapter

    private lateinit var reference: DatabaseReference

    private lateinit var auth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVisualizarAgendamentoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        reference = Firebase.database.reference
        auth = Firebase.auth

        ConsultaAdapter = ConsultaAdapter()
        binding.recyclerConsultas.adapter = ConsultaAdapter
        binding.recyclerConsultas.layoutManager = LinearLayoutManager(requireContext())

        getConsulta()

        binding.toolbarPacientes.setOnClickListener{
            findNavController().navigateUp()
        }



    }

    //verifica se o usuário está logado
    private fun getConsulta() {
        val uid = auth.currentUser?.uid
        if (uid == null) {
            Toast.makeText(requireContext(), "Usuário não logado", Toast.LENGTH_SHORT).show()
            return
        }

        reference
            .child("agendamentos")
            .child(uid)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (!snapshot.exists()) {
                        Toast.makeText(requireContext(), "Snapshot vazio!", Toast.LENGTH_SHORT).show()
                        return
                    }
                    val agendaList = mutableListOf<Consulta>()
                    //verifica se os dados de agendamento existem
                    for (ds in snapshot.children) {
                        val consulta = ds.getValue(Consulta::class.java)
                        if (consulta != null) agendaList.add(consulta)
                    }
                    ConsultaAdapter.submitList(agendaList)
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(requireContext(), "Erro ao carregar dados", Toast.LENGTH_SHORT).show()
                }
            })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
