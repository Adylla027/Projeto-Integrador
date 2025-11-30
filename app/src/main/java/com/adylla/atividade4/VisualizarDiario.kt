package com.adylla.atividade4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.adylla.atividade4.databinding.FragmentVisualizarDiarioBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.database
import kotlin.getValue

class VisualizarDiario : Fragment() {

    private lateinit var binding: FragmentVisualizarDiarioBinding
    private val args by navArgs<VisualizarDiarioArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVisualizarDiarioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val registro = args.registroDiario

        binding.textViewTitulo.text = registro.title
        binding.textViewLerDiario.text = registro.description

        binding.toolbar.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.btnCompartilhar.setOnClickListener {
            DialogCompartilhar().show(parentFragmentManager, "DialogCompartilhar")
        }

        setFragmentResultListener("compartilharRequest"){_,bundle ->
            val confirmado = bundle.getBoolean("Confirme")
            if(confirmado){
                CompartilharProfissional()
            }
        }

    }

    private fun CompartilharProfissional(){
        val auth = FirebaseAuth.getInstance()

        val userId = auth.currentUser?.uid?: return
        val registro = args.registroDiario

        val dados = mapOf(
            "id" to registro.id,
            "title" to registro.title,
            "description" to registro.description
        )

        Firebase.database.reference
            .child("compartilharProfissinal")
            .child(userId)
            .child(registro.id)
            .setValue(dados)
            .addOnCompleteListener {
                if (it.isSuccessful){
                    Toast.makeText(requireContext(), "Compartilhado com o Psicólogo", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(requireContext(), "Erro ao Compartilhar", Toast.LENGTH_SHORT).show()
                }

            }
    }

}

