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
import com.google.firebase.database.FirebaseDatabase
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

        compartilharComPsicologo()


    }

    private fun buscarIdPsicologo(
        pacienteId: String,
        callback: (String?) -> Unit
    ){
        FirebaseDatabase.getInstance().reference
            .child("usuarios")
            .child(pacienteId)
            .child("psicologoId")
            .get()
            .addOnSuccessListener {callback(it.getValue(String::class.java))  }
            .addOnFailureListener {callback(null) }

    }

    private fun compartilharComPsicologo(){

        val pacienteId = FirebaseAuth.getInstance().currentUser?.uid ?: return
        val registro = args.registroDiario

        buscarIdPsicologo(pacienteId){ psicologoId ->

            if (psicologoId == null){
                Toast.makeText(requireContext(), "Nenhum psicólogo vinculado", Toast.LENGTH_SHORT).show()

            }else{
                val ref = FirebaseDatabase.getInstance().reference
                    .child("compartilhamentos")
                    .child(psicologoId)
                    .child(pacienteId)
                    .child(registro.id)

                ref.setValue(registro)
                    .addOnSuccessListener{
                        Toast.makeText(requireContext(), "Compartilhado com sucesso", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener{
                        Toast.makeText(requireContext(), "Erro ao compartilhar", Toast.LENGTH_SHORT).show()
                    }
            }

        }




    }


}

