package unicauca.edu.drogue_plus.data.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import unicauca.edu.drogue_plus.data.models.MedicineModel
import unicauca.edu.drogue_plus.data.repositories.MedicineRepository

class MedicineViewModel(private val repo:MedicineRepository): ViewModel(){
    private val _medicines :MutableLiveData<List<MedicineModel>> = MutableLiveData()
    val medicines : LiveData<List<MedicineModel>> get() = _medicines

    fun getMedicines(category : String?){
        viewModelScope.launch {
            _medicines.postValue(repo.getMedicines(category))
        }
    }
}