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
    private lateinit var medicineAdapter:MedicineAdapter
    private lateinit var categories : List<String>
    private lateinit var MedicineList :List<MedicineModel>
    private lateinit var OriginalMedicineList :List<MedicineModel>

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
        MedicineList = listOf(
            MedicineModel(title = "Acetaminofen",R.drawable.img_aceta.toString(), state = "Medicamento Vigente"),
            MedicineModel(title = "Aspirina",R.drawable.img_aspirina.toString(), state = "Medicamento NO Vigente"),
            MedicineModel(title = "Loratadina",R.drawable.img_lorata.toString(), state = "Medicamento Vigente"),
            MedicineModel(title = "Ranitidina",R.drawable.img_ranitidina.toString(), state = "Medicamento Vigente"),
            MedicineModel(title = "Dolex",R.drawable.img_dolex.toString(), state = "Medicamento NO Vigente"),
            MedicineModel(title = "Acetaminofen",R.drawable.img_aceta.toString(), state = "Medicamento Vigente"),
            MedicineModel(title = "Dolex",R.drawable.img_dolex.toString(), state = "Medicamento NO Vigente")
        )
        OriginalMedicineList = listOf(
            MedicineModel(title = "Acetaminofen",R.drawable.img_aceta.toString(), state = "Medicamento Vigente"),
            MedicineModel(title = "Aspirina",R.drawable.img_aspirina.toString(), state = "Medicamento NO Vigente"),
            MedicineModel(title = "Loratadina",R.drawable.img_lorata.toString(), state = "Medicamento Vigente"),
            MedicineModel(title = "Ranitidina",R.drawable.img_ranitidina.toString(), state = "Medicamento Vigente"),
            MedicineModel(title = "Dolex",R.drawable.img_dolex.toString(), state = "Medicamento NO Vigente"),
            MedicineModel(title = "Acetaminofen",R.drawable.img_aceta.toString(), state = "Medicamento Vigente"),
            MedicineModel(title = "Dolex",R.drawable.img_dolex.toString(), state = "Medicamento NO Vigente")
        )

        medicineAdapter = MedicineAdapter(MedicineList)

        binding.medicamentoFragmentSearchAutocomplete.setAdapter(
            ArrayAdapter(requireContext(),android.R.layout.simple_dropdown_item_1line,categories))

        binding.medicamentoFragmentSearchAutocomplete.setOnItemClickListener { parent, view, position, l ->
            var category = categories[position]
            if (category !="Todos")
                MedicineList = OriginalMedicineList.filter { x -> x.title == category}
            else
                MedicineList =OriginalMedicineList
            medicineAdapter.changeDataSet(MedicineList)
        }

        binding.homeFragmentRecycler.apply {
            adapter = medicineAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }


}