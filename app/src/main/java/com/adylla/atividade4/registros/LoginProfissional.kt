package com.adylla.atividade4.registros

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.adylla.atividade4.R
import com.adylla.atividade4.databinding.FragmentLoginBinding
import com.adylla.atividade4.databinding.FragmentLoginProfissionalBinding
import com.google.firebase.auth.FirebaseAuth

class LoginProfissional : Fragment() {

    private var _binding: FragmentLoginProfissionalBinding? = null

    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLoginProfissionalBinding.inflate(inflater,container, false)

        val view = binding.root
        return view
       }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()
        validateData()
        novaSenha()
        criaCadastro()

    }


    private fun validateData(){
        //caso o usuário tenha login
        binding.buttnEntrar.setOnClickListener {
            val email = binding.EdittextEMAIL.text.toString().trim()
            val senha = binding.EdittextSENHA.text.toString().trim()

            //validação de email e senha
            if (email.isEmpty() || senha.isEmpty()){

                Toast.makeText(requireContext(), "Preencha com email e senha!", Toast.LENGTH_SHORT).show()

            }else{
                loginUser(email, senha)
            }

        }

    }
    private fun loginUser(email: String, senha: String){

        try {
            auth.signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener { task ->
                    if(task.isSuccessful){

                        val action = LoginProfissionalDirections
                            .actionLoginProfissionalToFragmentTelaInicialProfissional(email,senha)

                        findNavController().navigate(action)
                    }else{
                        Toast.makeText(requireContext(), task.exception?.message, Toast.LENGTH_SHORT).show()
                    }

                }
        }catch (e: Exception){
            Toast.makeText(requireContext(), e.message.toString(), Toast.LENGTH_SHORT).show()
        }
    }


    //O usuário não tem senha
    private fun novaSenha(){
        binding.textviewSenha.setOnClickListener{
            findNavController().navigate(R.id.action_fragmentLogin_to_fragmentSenha)
        }
    }


    // O usuário não tem cadastro
    private fun criaCadastro(){
        binding.textviewCadastro.setOnClickListener{
            findNavController().navigate(R.id.action_fragmentLogin_to_fragmentCadastro)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}