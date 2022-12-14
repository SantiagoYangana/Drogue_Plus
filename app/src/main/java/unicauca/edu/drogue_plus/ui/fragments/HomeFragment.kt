package unicauca.edu.drogue_plus.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import unicauca.edu.drogue_plus.R
import unicauca.edu.drogue_plus.data.viewmodels.LoginViewModel
import unicauca.edu.drogue_plus.databinding.FragmentHomeBinding
import unicauca.edu.drogue_plus.databinding.FragmentMedicationBinding



class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!
    private lateinit var serviceAdapter: MedicineAdapter


    private val loginViewModel: LoginViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        loginViewModel.currentUser()
        serviceAdapter = MedicineAdapter(
            mutableListOf()
        )
    }

/*
override fun onStart(){
        super.onStart()
        loginViewModel.currentUser()
    }
*/

}
