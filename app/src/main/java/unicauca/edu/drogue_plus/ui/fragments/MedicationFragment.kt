package unicauca.edu.drogue_plus.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import unicauca.edu.drogue_plus.R
import unicauca.edu.drogue_plus.data.models.MedicineModel
import unicauca.edu.drogue_plus.data.viewmodels.MedicineViewModel
import unicauca.edu.drogue_plus.databinding.FragmentMedicationBinding

class MedicationFragment : Fragment() {
    private var _binding: FragmentMedicationBinding? = null
    private val binding : FragmentMedicationBinding get() = _binding!!
    private lateinit var medicineAdapter:MedicineAdapter
    private lateinit var categories : List<String>
    private val medicineViewModel : MedicineViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMedicationBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        categories = listOf(
            "Todos", "Acetaminofen","Aspirina", "Loratadina","Ranitidina","Dolex"
        )

        medicineAdapter = MedicineAdapter(mutableListOf())

        binding.medicamentoFragmentSearchAutocomplete.setAdapter(
            ArrayAdapter(requireContext(),android.R.layout.simple_dropdown_item_1line,categories))

        binding.medicamentoFragmentSearchAutocomplete.setOnItemClickListener { parent, view, position, l ->
            var category = categories[position]
            if (category !="Todos")
                medicineViewModel.getMedicines(category)
            else
                medicineViewModel.getMedicines(null)
//            medicineAdapter.changeDataSet(MedicineList)
        }

        binding.homeFragmentRecycler.apply {
            adapter = medicineAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        observeViewModels()
    }
    private fun observeViewModels(){
        medicineViewModel.medicines.observe(viewLifecycleOwner, Observer {
            medicineAdapter.changeDataSet(it)
        })
    }


}