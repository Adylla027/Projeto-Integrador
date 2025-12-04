package com.adylla.atividade4

import android.app.AlertDialog
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
import com.google.firebase.auth.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.google.firebase.database.getValue
import kotlin.getValue

class VisualizarDiario : Fragment() {

    private lateinit var binding: FragmentVisualizarDiarioBinding

    private lateinit var reference: DatabaseReference

    private lateinit var auth: FirebaseAuth
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

        reference = Firebase.database.reference
        auth = Firebase.auth

        binding.textViewTitulo.text = registro.title
        binding.textViewLerDiario.text = registro.description

        binding.toolbar.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.btnCompartilhar.setOnClickListener{
            mostrarDialogCompartilhar(registro)

        }

        binding.buttonDELETE.setOnClickListener{
            deleteRegistro(registro)

        }

    }

    //Função que chama o popup de compartilhamento
    private fun mostrarDialogCompartilhar(registroDiario: RegistroDiario){
        requireContext().showCompartilharPopup {
            compartilharNotaProfissional(registroDiario)
        }
    }

    private fun compartilharNotaProfissional(registroDiario: RegistroDiario){
        val notaAtualizada = registroDiario.copy(iscompartilhada= true)

        val ref = FirebaseDatabase.getInstance()
            .getReference("registros")
            .child(registroDiario.id)

        ref.setValue(notaAtualizada)
            .addOnSuccessListener {
                Toast.makeText(requireContext(), "Nota compartilhada!", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(requireContext(), "Erro ao compartilhar.", Toast.LENGTH_SHORT).show()
            }

    }

    private fun deleteRegistro(registro: RegistroDiario) {
        reference
            .child( "registros")
            .child( auth.currentUser?.uid ?: "")
            .child( registro.id)
            .removeValue().addOnCompleteListener { result ->

                if (result.isSuccessful) {
                    Toast.makeText(requireContext(),"Registro deletado",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(requireContext(), "Algo deu errado",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

}

