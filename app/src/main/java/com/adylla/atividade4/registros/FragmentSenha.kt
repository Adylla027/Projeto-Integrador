package com.adylla.atividade4.registros

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.adylla.atividade4.R
import com.adylla.atividade4.databinding.FragmentSenhaBinding
import com.google.firebase.auth.FirebaseAuth


class FragmentSenha : Fragment() {
    private var _binding: FragmentSenhaBinding? = null
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSenhaBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setaVoltar()

        auth = FirebaseAuth.getInstance()

        initListener()


        binding.buttnFINALIZAR.setOnClickListener{

            val email = binding.EdittextEMAIL.text.toString().trim()
            val senha = binding.EdittextSENHA.text.toString().trim()

            if (email.isEmpty() || senha.isEmpty() ) {
                Toast.makeText(requireContext(), "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
            }  else {
                val action = FragmentSenhaDirections.actionFragmentSenhaToFragmentLogin(email, senha)
                findNavController().navigate(action)
            }
        }

    }



    private fun setaVoltar(){
        binding.toolbarCadastro.setOnClickListener{
            findNavController().navigate(R.id.action_fragmentSenha_to_fragmentLogin)
        }
    }

    private fun initListener() {
        binding.buttnFINALIZAR.setOnClickListener {
            validateData()
        }
    }

    private fun validateData() {
        val email = binding.EdittextEMAIL.text.toString().trim()

        if (email.isNotBlank()) {
            recoverAccountUser(email)
        } else {
            Toast.makeText(requireContext(), "Preencha o email", Toast.LENGTH_SHORT).show()
        }
    }

    private fun recoverAccountUser(email: String) {
        try {
            auth.sendPasswordResetEmail( email)
                .addOnCompleteListener { recuperacao ->
                    if (recuperacao.isSuccessful) {
                        Toast.makeText(requireContext(), "Senha recuperada", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(requireContext(),
                             recuperacao.exception?.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        } catch (e: Exception) {
            Toast.makeText(
                 requireContext(),
                 e.message.toString(),
                Toast.LENGTH_SHORT
            ).show()
        }
    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}