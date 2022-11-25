package unicauca.edu.drogue_plus

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import com.google.android.material.datepicker.MaterialDatePicker
import unicauca.edu.drogue_plus.databinding.FragmentRegisterBinding
import java.text.SimpleDateFormat
import java.util.*

class RegisterFragment : Fragment() {

    private var _binding : FragmentRegisterBinding? = null
    private val binding: FragmentRegisterBinding get() = _binding!!

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

    }
}