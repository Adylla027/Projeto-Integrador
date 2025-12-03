package com.adylla.atividade4.introducao

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.adylla.atividade4.R
import com.adylla.atividade4.databinding.FragmentSplashBinding
import com.google.firebase.auth.FirebaseAuth

class FragmentSplash : Fragment() {
    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Navega automaticamente para a tela de login após 5 segundos
        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(R.id.action_fragmentTelaInicial_to_fragmentIntroducao)
        }, 3000)

        auth = FirebaseAuth.getInstance()

    }
    /*private fun checkAuth(){
        try {
            val currentUser = auth.currentUser

            if(currentUser != null){
                findNavController().navigate(R.id.action_fragmentTelaInicial_to_fragmentTelaPaciente)
            }else{
                findNavController().navigate(R.id.action_fragmentLogin_to_fragmentCadastro)
            }
        }catch (e: Exception){
            Toast.makeText(requireContext(), e.message.toString(), Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_fragmentLogin_to_fragmentCadastro)
        }
    }*/

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}