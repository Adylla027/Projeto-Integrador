package com.adylla.atividade4.registros


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.adylla.atividade4.R
import com.adylla.atividade4.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth


class FragmentLogin : Fragment() {
    private var _binding: FragmentLoginBinding? = null

    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()


        novaSenha()
        criaCadastro()





        //caso o usuário tenha um  login
        binding.buttnEntrar.setOnClickListener{


            val emailDigitado = binding.EdittextEMAIL.text.toString().trim()
            val senhaDigitada = binding.EdittextSENHA.text.toString().trim()


            //Validação do email e senha.
            if(emailDigitado.isEmpty()|| senhaDigitada.isEmpty()){
                Toast.makeText(requireContext(),"Preencha o email e a senha!", Toast.LENGTH_SHORT).show()
            }
            else{
                val action = FragmentLoginDirections.actionFragmentLoginToFragmentTelaPaciente(emailDigitado,senhaDigitada)

                findNavController().navigate(action)
            }

        }
    }

    /*
    private fun validacao(){

        val emailDigitado = binding.EdittextEMAIL.text.toString().trim()
        val senhaDigitada = binding.EdittextSENHA.text.toString().trim()

        if (emailDigitado.isNotBlank()){
            if (senhaDigitada.isNotBlank()){
                loginUser(emailDigitado, senhaDigitada)
            }
        }

    }*/

    /*
    private fun loginUser(email: String, senha: String){
        try {
            auth.signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        findNavController().navigate(R.id.action_fragmentLogin_to_fragmentTelaPaciente)
                    }else{
                        Toast.makeText(requireContext(), task.exception?.message, Toast.LENGTH_SHORT).show()
                    }
                }
        }catch (e: Exception){
            Toast.makeText(requireContext(), e.message.toString(), Toast.LENGTH_SHORT).show()
        }
    }*/

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

