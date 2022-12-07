package unicauca.edu.drogue_plus.di

import org.koin.dsl.module
import unicauca.edu.drogue_plus.data.repositories.HomeRepository
import unicauca.edu.drogue_plus.data.repositories.LoginRepository

val repoModule = module {
    single{ LoginRepository(get(),get(),get())}
    single{ HomeRepository()}
}