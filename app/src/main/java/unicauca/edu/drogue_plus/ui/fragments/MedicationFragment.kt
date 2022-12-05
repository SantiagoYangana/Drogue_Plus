package unicauca.edu.drogue_plus.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import unicauca.edu.drogue_plus.R
import unicauca.edu.drogue_plus.databinding.FragmentMedicationBinding
import unicauca.edu.drogue_plus.databinding.FragmentProfileBinding

class MedicationFragment : Fragment() {
    private var _binding: FragmentMedicationBinding? = null
    private val binding : FragmentMedicationBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMedicationBinding.inflate(inflater,container,false)
        return binding.root
    }

}