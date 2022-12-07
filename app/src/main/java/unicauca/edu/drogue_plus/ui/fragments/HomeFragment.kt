package unicauca.edu.drogue_plus.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import unicauca.edu.drogue_plus.R
import unicauca.edu.drogue_plus.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding : FragmentHomeBinding get() = _binding!!
    private lateinit var serviceAdapter: ServiceAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        serviceAdapter = serviceAdapter {

            listOf(
                ServiceModel(
                    id: "1", icon: "Acetaminofen", tittle:"Medicamento no disponible",
                    R.drawable.ic_baseline_medication_24.toString()
                ),
            )
        }

        binding.homeFragmentRecycler.apply {  this.RecyclerView
            adapter = ServiceAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }


}