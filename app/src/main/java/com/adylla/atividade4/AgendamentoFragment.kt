package com.adylla.atividade4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.adylla.atividade4.databinding.FragmentAgendamentoBinding
import com.adylla.atividade4.databinding.FragmentLoginBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class AgendamentoFragment : Fragment() {
    private var _binding: FragmentAgendamentoBinding? = null

    private val binding get() = _binding!!

    private lateinit var reference: DatabaseReference

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding = FragmentAgendamentoBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //reference = Firebase.database.reference
        auth = Firebase.auth

        binding.toolbarAgendamento.setOnClickListener{
            findNavController().navigateUp()
        }


      binding.calendarview.setOnDateChangeListener {_,year, month, day ->
          val dia = String.format("%02d",day)
          val mes = String.format("%02d",month+1)
          val data = "$dia-$mes-$year"

      }

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }




}