package com.adylla.atividade4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adylla.atividade4.databinding.FragmentTelaInicialBinding
import android.os.Handler
import android.os.Looper
import androidx.navigation.fragment.findNavController



class FragmentTelaInicial : Fragment() {
    private var _binding: FragmentTelaInicialBinding? = null
    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTelaInicialBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Navega automaticamente para a tela de login após 5 segundos
        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(R.id.action_fragmentTelaInicial_to_fragmentLogin)
        }, 10000) // tempo em milissegundos (5 segundos)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}