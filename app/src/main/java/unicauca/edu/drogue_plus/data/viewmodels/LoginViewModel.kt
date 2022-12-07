package unicauca.edu.drogue_plus.data.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import unicauca.edu.drogue_plus.data.models.UserModel
import unicauca.edu.drogue_plus.data.repositories.LoginRepository

class LoginViewModel(private val repo: LoginRepository) : ViewModel() {
    private var _login: MutableLiveData<Boolean> = MutableLiveData()
    val login: LiveData<Boolean> get() = _login

    private var _register: MutableLiveData<Boolean> = MutableLiveData()
    val register: LiveData<Boolean> get() = _register

    private var _user: MutableLiveData<UserModel?> = MutableLiveData()
    val user: LiveData<UserModel?> get() = _user

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                repo.login(email, password)
                _login.postValue(true)
            } catch (e: Exception) {
                _login.postValue(false)
            }
        }
    }

    fun register(name:String, gender:String, email:String,password: String){
        viewModelScope.launch {
            try {
                repo.register(name,gender,email, password)
                _register.postValue(true)
            } catch (e: Exception) {
                _register.postValue(false)
            }
        }
    }
    fun logOut(){
        viewModelScope.launch {
            try {
                repo.logOut()
                _user.postValue(null)
            } catch (e: Exception) {
            }

        }
    }
    fun currentUser(){
        viewModelScope.launch{
            _user.postValue(repo.getCurrentUser())
        }
    }
}