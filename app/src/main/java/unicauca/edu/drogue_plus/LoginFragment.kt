package unicauca.edu.drogue_plus

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import unicauca.edu.drogue_plus.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private var _binding : FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding get() = _binding!!

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
        }

        binding.fragmentLoginLbl2.setOnClickListener(){
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.loginForgotButton.setOnClickListener(){
            findNavController().navigate(R.id.action_loginFragment_to_forgotFragment)
        }
    }

}