package unicauca.edu.drogue_plus.data.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import unicauca.edu.drogue_plus.data.models.MapModel
import unicauca.edu.drogue_plus.data.models.MedicineModel
import unicauca.edu.drogue_plus.data.repositories.HomeRepository

class HomeViewModel(private val repo:HomeRepository): ViewModel() {
    private val _location : MutableLiveData<MapModel> = MutableLiveData()
    val location : LiveData<MapModel> get() = _location

    fun getLocation(){
        viewModelScope.launch {
            _location.postValue(repo.getLocation())
        }
    }
}