package unicauca.edu.drogue_plus.di

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import org.koin.dsl.module
import unicauca.edu.drogue_plus.data.datasource.MemoryDataSource

val dataSourceModule = module{
    single {MemoryDataSource()}

    single{
        Firebase.auth
    }

    single{
        Firebase.firestore
    }

    single{
        Firebase.storage("gs://drogeplus.appspot.com")
    }
}