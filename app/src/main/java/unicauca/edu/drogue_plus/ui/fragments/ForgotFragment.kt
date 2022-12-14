package unicauca.edu.drogue_plus.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import unicauca.edu.drogue_plus.R
import unicauca.edu.drogue_plus.databinding.FragmentForgotBinding

class ForgotFragment : Fragment() {

    private var _binding: FragmentForgotBinding?=null
    private val binding : FragmentForgotBinding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentForgotBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onStart(){
        super.onStart()
        binding.fragmentForgotLbl2.setOnClickListener(){
            findNavController().navigate(R.id.action_forgotFragment_to_registerFragment)
        }
    }

}