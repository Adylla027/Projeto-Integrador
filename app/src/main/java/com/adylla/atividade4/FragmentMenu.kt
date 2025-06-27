package com.adylla.atividade4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.adylla.atividade4.databinding.FragmentMenuBinding

class FragmentMenu : Fragment() {
    private var _binding: FragmentMenuBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navegaMenu()
    }
// Abre e fecha o menu
    private fun navegaMenu(){
        binding.imagemenu.setOnClickListener{
            findNavController().navigate(R.id.action_fragmentMenu_to_fragmentTelaPaciente)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}