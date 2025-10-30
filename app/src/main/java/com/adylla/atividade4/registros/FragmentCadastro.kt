package com.adylla.atividade4.registros

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.adylla.atividade4.R
import com.adylla.atividade4.databinding.FragmentCadastroBinding

class FragmentCadastro : Fragment() {
    private var _binding: FragmentCadastroBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCadastroBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setaVoltar()

        binding.buttnCadastro.setOnClickListener {

            val email = binding.EdittextEMAIL.text.toString().trim()
            val senha = binding.EdittextSENHA.text.toString().trim()

            //Validação do email e senha.
            if (email.isEmpty() || senha.isEmpty()) {
                Toast.makeText(requireContext(), "Preencha o email e a senha!", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val action: Int = R.id.action_fragmentCadastro_to_fragmentLogin



                findNavController().navigate(action)
            }
        }
    }

    private fun setaVoltar(){
        binding.toolbarCadastro.setOnClickListener{
            findNavController().navigate(R.id.action_fragmentCadastro_to_fragmentLogin)
        }
    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}