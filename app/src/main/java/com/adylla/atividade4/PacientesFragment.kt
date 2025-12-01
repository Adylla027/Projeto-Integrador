package com.adylla.atividade4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.adylla.atividade4.databinding.FragmentPacientesBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database


class PacientesFragment : Fragment() {

    private var _binding: FragmentPacientesBinding? = null

    private val binding get() = _binding!!

    private lateinit var reference: DatabaseReference

    private lateinit var auth: FirebaseAuth



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPacientesBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        reference = Firebase.database.reference
        auth = Firebase.auth

        binding.toolbarPacientes.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    /*private fun optionSelected(){

        val action = PaginaDiarioDirections.actionPaginaDiarioToVisualizarDiario(passar parâmetros)
        findNavController().navigate(action)
    }*/


}
