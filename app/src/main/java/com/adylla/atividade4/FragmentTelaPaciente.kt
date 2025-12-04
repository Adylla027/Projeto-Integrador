package com.adylla.atividade4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.adylla.atividade4.databinding.FragmentTelaPacienteBinding
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.google.android.material.navigation.NavigationView
import kotlin.getValue


class FragmentTelaPaciente : Fragment(), NavigationView.OnNavigationItemSelectedListener {
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
        botoes()

    }
    private fun initListenerMenu(){
        toggle = ActionBarDrawerToggle(requireActivity(),binding.drawerLayout,binding.toolbar,R.string.open, R.string.close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        binding.naview.setNavigationItemSelectedListener(this)
        binding.toolbar.setNavigationIcon(R.drawable.ic_menu)
    }
    private fun getExtra(){
        val email = args.email
        val senha = args.senha
    }
    private fun setupImageSlider() {

        val imageList = ArrayList<SlideModel>()

        imageList.add(SlideModel(R.drawable.imagem4_carrossel))
        imageList.add(SlideModel(R.drawable.imagem_carrossel))
        imageList.add(SlideModel(R.drawable.imagem3_carrossel))

        binding.sliderImage.setImageList(imageList, ScaleTypes.FIT)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        val navController = findNavController()

        when (item.itemId) {

            R.id.nav_diario ->{
                if (navController.currentDestination?.id != R.id.paginaDiario){
                    navController.navigate(R.id.action_fragmentTelaPaciente_to_paginaDiario)
                }
            }



            R.id.nav_agendamento ->{
                if (navController.currentDestination?.id != R.id.visualizarAgendamento){
                    navController.navigate(R.id.action_fragmentTelaPaciente_to_visualizarAgendamento)
                }
            }

            R.id.nav_compartilhados ->{
                if (navController.currentDestination?.id != R.id.fragmentCompartilhados){
                    navController.navigate(R.id.action_fragmentTelaPaciente_to_fragmentCompartilhados)
                }
            }
            R.id.nav_favoritos ->{
                if (navController.currentDestination?.id != R.id.fragmentFavoritos){
                    navController.navigate(R.id.action_fragmentTelaPaciente_to_fragmentFavoritos)
                }
            }
            R.id.nav_lixeira ->{
                if (navController.currentDestination?.id != R.id.fragmentLixeira){
                    navController.navigate(R.id.action_fragmentTelaPaciente_to_fragmentLixeira)
                }
            }

            R.id.nav_sair ->{
                if (navController.currentDestination?.id != R.id.fragmentLogin){
                    navController.navigate(R.id.action_fragmentTelaPaciente_to_fragmentLogin)
                }
            }

            else -> {

            }

        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun botoes(){
        binding.cardViewDiario.setOnClickListener{
            findNavController().navigate(R.id.action_fragmentTelaPaciente_to_paginaDiario)

        }

        binding.Imagediario.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentTelaPaciente_to_paginaDiario)
        }

        binding.textViewDiario.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentTelaPaciente_to_paginaDiario)
        }

        binding.cardViewAgenda.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentTelaPaciente_to_visualizarAgendamento)
        }

        binding.imageViewAgenda.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentTelaPaciente_to_visualizarAgendamento)
        }

        binding.textViewAgenda.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentTelaPaciente_to_visualizarAgendamento)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}