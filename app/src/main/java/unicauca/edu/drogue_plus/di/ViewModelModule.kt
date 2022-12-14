package unicauca.edu.drogue_plus.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import unicauca.edu.drogue_plus.data.repositories.MedicineRepository
import unicauca.edu.drogue_plus.data.viewmodels.HomeViewModel
import unicauca.edu.drogue_plus.data.viewmodels.LoginViewModel
import unicauca.edu.drogue_plus.data.viewmodels.MedicineViewModel

val viewModelModule = module{
    viewModel{
        LoginViewModel(get())
    }
    viewModel{
        HomeViewModel(get())
    }
    viewModel{
        MedicineViewModel(get())
    }
}