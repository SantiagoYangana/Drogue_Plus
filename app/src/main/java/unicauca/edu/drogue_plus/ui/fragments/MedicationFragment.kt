package unicauca.edu.drogue_plus.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import unicauca.edu.drogue_plus.R
import unicauca.edu.drogue_plus.databinding.FragmentMedicationBinding
import unicauca.edu.drogue_plus.databinding.FragmentProfileBinding

class MedicationFragment : Fragment() {
    private var _binding: FragmentMedicationBinding? = null
    private val binding : FragmentMedicationBinding get() = _binding!!
    private val args:MedicationFragmentArgs by navArgs()
    private lateinit var MedicineAdapter: MedicineAdapter
    private lateinit var MedicineList: List<MedicineModel>
    private lateinit var  originalList: List<MedicineModel>
    lateinit var categories: List<String>

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
        originalList = listOf(
            MedicineModel(name = "Acetaminofen", R.drawable.ic_baseline_medication_24.toString(), state = true),
            MedicineModel(name = "Aspirina", R.drawable.ic_baseline_medication_24.toString(), state = true),
            MedicineModel(name = "Loratadina", R.drawable.ic_baseline_medication_24.toString(), state = true),
            MedicineModel(name = "Ranitidina", R.drawable.ic_baseline_medication_24.toString(), state = false),
            MedicineModel(name = "Dolex", R.drawable.ic_baseline_medication_24.toString(), state = true)
        )
        MedicineList = listOf(
            MedicineModel(name = "Acetaminofen", R.drawable.ic_baseline_medication_24.toString(), state = true),
            MedicineModel(name = "Aspirina", R.drawable.ic_baseline_medication_24.toString(), state = true),
            MedicineModel(name = "Loratadina", R.drawable.ic_baseline_medication_24.toString(), state = true),
            MedicineModel(name = "Ranitidina", R.drawable.ic_baseline_medication_24.toString(), state = false),
            MedicineModel(name = "Dolex", R.drawable.ic_baseline_medication_24.toString(), state = true)

        )
        if (args.search){
            binding.medicamentoFragmentSearch.visibility = View.VISIBLE
            binding.medicamentoFragmentTitleList.visibility = View.GONE
        }else{
            binding.medicamentoFragmentSearch.visibility = View.GONE
            binding.medicamentoFragmentTitleList.visibility = View.VISIBLE
            MedicineList = originalList.filter { x -> x.name == args.name }

    }
        MedicineAdapter = MedicineAdapter(MedicineList)
        MedicineAdapter.listener = object : OnMedicineClickListener {
            override fun onClick(item: MedicineModel) {
                //Log.d(tag: "HOLA" item.name)
            }

        }
        binding.medicamentoFragmentSearchAutocomplete.setAdapter(
            ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line,categories))
        binding.medicamentoFragmentSearchAutocomplete.setOnItemClickListener{parent, view, position, id ->
            val category = categories[position]
            if (category != "Todos") {

                MedicineList = originalList.filter { x -> x.name == category }
            }else {
                MedicineList = originalList
                MedicineAdapter.changeDataSet(MedicineList)
            }
        }

        binding.homeFragmentRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = MedicineAdapter
        }
    }

}