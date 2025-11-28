package com.adylla.atividade4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.adylla.atividade4.databinding.FragmentVisualizarDiarioBinding
import com.google.firebase.auth.FirebaseAuth
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
            compartilharComPsicologo()
        }
    }
    private fun getIdPaciente(): String{
        return FirebaseAuth.getInstance().currentUser?.uid ?: ""
    }

    private fun getIdPsicologo(): String{
        return args.psicologoId
    }

    private fun compartilharComPsicologo(){
        val diario = args.registroDiario
        val pacienteId = getIdPaciente()
        val psicologoId = getIdPsicologo()

        val compartilhamentoId = "$pacienteId-$psicologoId"

        val db = FirebaseAuth.getInstance().getReference("compartilhamentos")
        db.child(compartilhamentoId).get().addOnSuccessListener{ snap ->

            if (snap.exists()){

                val compartilhamento = snap.getValue(Compartilhar::class.java)
                val novaLista = compartilhamento!!.idDiariosCompartilhados + diario.id

                db.child(compartilhamentoId)
                    .child("idDiariosCompartilhados")
                    .setValue(novaLista)

            }else{

                val novoCompartilhamento = Compartilhar(
                    compartilhamentoId = compartilhamentoId,
                    idPaciente = pacienteId,
                    idPsicologo = psicologoId,
                    idDiariosCompartilhados = listOf(diario.id)
                )
                db.child(compartilhamentoId).setValue(novoCompartilhamento)
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

}

