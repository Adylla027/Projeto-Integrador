package com.adylla.atividade4.registros

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.adylla.atividade4.R
import com.adylla.atividade4.Usuario
import com.adylla.atividade4.databinding.FragmentCadastroBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

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
        crpVisibilidade()
        validateData()
    }

  //função que torna visível ou não campo para preencher crp de acordo com o tipo de usuário
    private fun crpVisibilidade(){
        binding.rbPsicologo.setOnCheckedChangeListener { _,  isChecked ->
            if (isChecked){
                binding.editTextCRP.visibility = View.VISIBLE
            }
        }

        binding.rbPaciente.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                binding.editTextCRP.visibility = View.GONE
                binding.editTextCRP.text.clear()
            }
        }

    }
    private fun validateData(){
        binding.buttnCadastro.setOnClickListener {

            val email = binding.EdittextEMAIL.text.toString().trim()
            val senha = binding.EdittextSENHA.text.toString().trim()


            if (email.isEmpty() || senha.isEmpty()) {
                Toast.makeText(requireContext(), R.string.msg_empty, Toast.LENGTH_SHORT)
                    .show()

            }else {
                registerUser(email, senha)
            }
        }
    }
    private fun registerUser(email: String, senha: String) {
        val auth = FirebaseAuth.getInstance()

        auth.createUserWithEmailAndPassword(email, senha)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    val uid = auth.currentUser!!.uid

                    //indinca no banco o tipo de usuário de acordo com o radio button preenchido
                    val tipoUsuario = when {
                        binding.rbPaciente.isChecked -> "pacientes"
                        binding.rbPsicologo.isChecked -> "profissionais"
                        else -> "pacientes"
                    }
                    //variável com comando condicional para verificar se radio button do profissional e chama o edit text do crp, se não estiver preenchido não chama
                    val crp = if (binding.rbPsicologo.isChecked) binding.editTextCRP.text.toString().trim() else ""

                    //Captura data atual e registra no banco junto com os outros dados
                    val dataCadastro = java.text.SimpleDateFormat(
                        "dd/MM/yyyy",
                        java.util.Locale.getDefault()
                    ).format(java.util.Date())

                    //variavel que irá capturar atrvés de dataclass os dados necessários para registro no banco de dados
                    val usuario = Usuario(
                        uid = uid,
                        email = email,
                        tipo = tipoUsuario,
                        nome = "",
                        dataNasc = "",
                        telefone = "",
                        dataCads = dataCadastro,
                        crp = crp,
                        profissionalId = ""
                    )

                    //salva os dados no firebase
                    val dbRef = FirebaseDatabase.getInstance().reference
                    dbRef.child(tipoUsuario)
                        .child(uid)
                        .setValue(usuario)
                        .addOnCompleteListener { saveTask ->
                            if (saveTask.isSuccessful) {
                                Toast.makeText(requireContext(), R.string.msg_sucesso_cadastro, Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(requireContext(), R.string.msg_erros_salvar_dados, Toast.LENGTH_LONG).show()
                            }
                        }

                } else {
                    Toast.makeText(requireContext(), R.string.msg_erro_cadastro, Toast.LENGTH_LONG).show()
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(requireContext(), R.string.msg_falha_cadastro, Toast.LENGTH_LONG).show()
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