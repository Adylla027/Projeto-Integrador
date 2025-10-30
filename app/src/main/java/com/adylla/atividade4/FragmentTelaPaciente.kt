package com.adylla.atividade4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.adylla.atividade4.databinding.FragmentTelaPacienteBinding
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel


class FragmentTelaPaciente : Fragment() {
    private var _binding: FragmentTelaPacienteBinding? = null
    private val args: FragmentTelaPacienteArgs by navArgs()
    private val binding get() = _binding!!

    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTelaPacienteBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getExtra()
        setupImageSlider()

        initListenerMenu()

        /*
        binding.cardViewDiario.setOnClickListener{
            findNavController().navigate(R.id.action_fragmentTelaPaciente_to_paginaDiario)

        }

        binding.cardViewAgenda.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentTelaPaciente_to_agendamentoFragment)
        }
        */

    }

    private fun initListenerMenu(){
        toggle = ActionBarDrawerToggle(requireActivity(),binding.drawerLayout,binding.toolbar,R.string.open, R.string.close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()



    }
    private fun getExtra(){
        val email = args.email
        val senha = args.senha
        Toast.makeText(requireContext(),"Email: $email, Senha: $senha",Toast.LENGTH_SHORT).show()
    }

    private fun setupImageSlider() {

        val imageList = ArrayList<SlideModel>()

        imageList.add(SlideModel(R.drawable.imagem_final_tela_profissional, "Diário"))
        imageList.add(SlideModel(R.drawable.logo2, "Logo"))

        binding.sliderImage.setImageList(imageList, ScaleTypes.FIT)

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}