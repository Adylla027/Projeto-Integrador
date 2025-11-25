package com.adylla.atividade4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adylla.atividade4.databinding.FragmentLoginBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth


class AgendamentoFragment : Fragment() {
    private var _binding: AgendamentoFragment? = null

    private val binding get() = _binding!!

    //private lateinit var reference: DatabaseReference

    private lateinit var auth: FirebaseAuth

    /*override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding = AgendamentoFragment.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }*/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //reference = Firebase.database.reference
        auth = Firebase.auth
    }

    private fun agendarConsulta(){
        
    }


}