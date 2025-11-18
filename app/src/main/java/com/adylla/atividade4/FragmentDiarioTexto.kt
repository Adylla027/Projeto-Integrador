package com.adylla.atividade4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.adylla.atividade4.databinding.FragmentDiarioTextoBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database
import java.lang.ref.Reference

class FragmentDiarioTexto : Fragment() {

    private var _binding: FragmentDiarioTextoBinding? = null

    private val binding get() = _binding!!

    private lateinit var registroDiario: RegistroDiario

    private var novoRegistroDiario: Boolean = true

    private lateinit var reference: DatabaseReference

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDiarioTextoBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        reference = Firebase.database.reference
        auth = Firebase.auth

        initListener()

    }

    private fun initListener(){
        binding.btnSalvar.setOnClickListener {
            validateData()
        }
    }

    private fun validateData(){
        val title = binding.editTextTitulo.text.toString().trim()
        val description = binding.inputEditText.text.toString().trim()

        if (title.isNotBlank() || description.isNotBlank()){
            if (novoRegistroDiario) registroDiario = RegistroDiario("", "", "")
            registroDiario.id = reference.database.reference.push().key ?: ""
            registroDiario.title = title
            registroDiario.description = description

            saveRegistro()

        }
    }

    private fun saveRegistro(){
        reference
            .child("registros")
            .child(auth.currentUser?.uid ?: "")
            .child(registroDiario.id)
            .setValue(registroDiario).addOnCompleteListener { result ->
                if (result.isSuccessful){
                    Toast.makeText(requireContext(), "Sucesso", Toast.LENGTH_SHORT).show()
                    if (novoRegistroDiario){
                        findNavController().popBackStack()
                    }else{
                        Toast.makeText(requireContext(), "Teste", Toast.LENGTH_SHORT).show()

                    }
                }else{
                    Toast.makeText(requireContext(), "Erro", Toast.LENGTH_SHORT).show()
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    }
