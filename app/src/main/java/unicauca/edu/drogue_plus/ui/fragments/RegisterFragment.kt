package unicauca.edu.drogue_plus.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel
import unicauca.edu.drogue_plus.R
import unicauca.edu.drogue_plus.data.viewmodels.LoginViewModel
import unicauca.edu.drogue_plus.databinding.FragmentRegisterBinding
import unicauca.edu.drogue_plus.isValidPassword
import unicauca.edu.drogue_plus.ui.activities.LoginActivity

class RegisterFragment : Fragment() {

    private var _binding : FragmentRegisterBinding? = null
    private val binding: FragmentRegisterBinding get() = _binding!!
    private val loginViewModel: LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        binding.fragmentRegisterLbl2.setOnClickListener{
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        binding.fragmentRegisterButton.setOnClickListener{
            val id = binding.registerRadioGroup.checkedRadioButtonId
            val radioButton = requireActivity().findViewById(id) as RadioButton

            val activity: LoginActivity? = activity as LoginActivity?
            val municipio: String? = activity?.getMunicipio()
            Toast.makeText(context,municipio, Toast.LENGTH_SHORT).show()

            if(binding.registerPassword.text.toString().equals(binding.registerConfirmPassword)){
                loginViewModel.register(
                    binding.registerName.text.toString(),
                    radioButton.text.toString(),
                    binding.registerEmail.text.toString(),
                    binding.registerPassword.text.toString(),
                    municipio.toString()
                )
            }else{
                binding.registerConfirmPasswordLayout.error = getString(R.string.password_confirm_error)
            }



        }

        observeViewModels()
    }

    private fun observeViewModels(){
        loginViewModel.register.observe(viewLifecycleOwner, Observer{
            if(it){
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
            }else{
                Snackbar.make(binding.root,"Registro Invalido",Snackbar.LENGTH_LONG).show()
            }
        })
    }
}