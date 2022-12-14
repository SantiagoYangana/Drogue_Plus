package unicauca.edu.drogue_plus.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel
import unicauca.edu.drogue_plus.R
import unicauca.edu.drogue_plus.data.viewmodels.LoginViewModel
import unicauca.edu.drogue_plus.databinding.FragmentLoginBinding
import unicauca.edu.drogue_plus.isValidEmail
import unicauca.edu.drogue_plus.isValidPassword
import unicauca.edu.drogue_plus.ui.activities.HomeActivity
import unicauca.edu.drogue_plus.ui.activities.LoginActivity

class LoginFragment : Fragment() {

    private var _binding : FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding get() = _binding!!

    private val loginViewModel: LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {




        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        val activity: LoginActivity? = activity as LoginActivity?
        val municipio: String? = activity?.getMunicipio()
        Toast.makeText(context,municipio,Toast.LENGTH_SHORT).show()

        binding.fragmentLoginButton.setOnClickListener{
            if(!binding.loginEmail.text.toString().isValidEmail()){
                binding.loginEmailLayout.error = getString(R.string.email_error)
            }else{
                binding.loginEmailLayout.error = null
            }

            if(!binding.loginPassword.text.toString().isValidPassword()){
                binding.loginPasswordLayout.error = getString(R.string.password_error)
            }else{
                binding.loginPasswordLayout.error = null
            }

            if(binding.loginEmail.text.toString().isValidEmail() && binding.loginPassword.text.toString().isValidPassword()){
                loginViewModel.login(binding.loginEmail.text.toString(),binding.loginPassword.text.toString(),municipio.toString())

            }
        }

        binding.fragmentLoginLbl2.setOnClickListener(){
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.loginForgotButton.setOnClickListener(){
            findNavController().navigate(R.id.action_loginFragment_to_forgotFragment)
        }

        observerViewModel()
    }

    private fun observerViewModel(){
        loginViewModel.login.observe(this, Observer{
            if(it){
                val intent = Intent(requireContext(), HomeActivity::class.java )
                startActivity(intent)
                requireActivity().finish()
            }else{
                Snackbar.make(binding.root,getString(R.string.login_error),Snackbar.LENGTH_LONG).show()
            }

        })
    }
}