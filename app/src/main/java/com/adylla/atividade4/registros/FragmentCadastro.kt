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
import com.google.firebase.auth.FirebaseAuth

class FragmentCadastro : Fragment() {
    private var _binding: FragmentCadastroBinding? = null
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth

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

        /*
        binding.buttnCadastro.setOnClickListener {

            val nome = binding.EdittextNOME.text.toString().trim()
            val dataNascimento = binding.EdittextNASCIMENTO.text.toString().trim()
            val telefone = binding.EdittextTELEFONE.text.toString().trim()
            val email = binding.EdittextEMAIL.text.toString().trim()
            val senha = binding.EdittextSENHA.text.toString().trim()
            val confirmarSenha = binding.EdittextCONFIRMASENHA.text.toString().trim()*/




            /*
            //Validação do email e senha.
            if (email.isEmpty() || senha.isEmpty()) {
                Toast.makeText(requireContext(), "Preencha o email e a senha!", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val action = R.id.action_fragmentCadastro_to_fragmentLogin



                findNavController().navigate(action)
            }
        }*/
    }

    /*
    private fun validacao(){
        when{
            nome.isEmpty
        }
    }*/

    private fun registerUser(nome: String, email: String, senha: String){
        try {
            val auth = FirebaseAuth.getInstance()

            auth.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        findNavController().navigate(R.id.action_fragmentCadastro_to_fragmentLogin)

                    } else {
                        Toast.makeText(requireContext(), task.exception?.message, Toast.LENGTH_SHORT).show()
                    }
                }
        }catch (e: Exception){
            Toast.makeText(requireContext(), e.message.toString(), Toast.LENGTH_SHORT).show()
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