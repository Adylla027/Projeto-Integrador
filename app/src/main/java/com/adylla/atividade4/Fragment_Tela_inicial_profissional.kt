package com.adylla.atividade4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adylla.atividade4.databinding.FragmentTelaInicialProfissionalBinding

class Fragment_Tela_inicial_profissional : Fragment() {

    private var _binding: FragmentTelaInicialProfissionalBinding? = null
    // Essa propriedade é válida apenas entre onCreateView e onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTelaInicialProfissionalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}