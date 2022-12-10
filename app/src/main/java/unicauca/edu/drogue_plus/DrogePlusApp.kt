package unicauca.edu.drogue_plus

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import unicauca.edu.drogue_plus.di.dataSourceModule
import unicauca.edu.drogue_plus.di.repoModule
import unicauca.edu.drogue_plus.di.viewModelModule

class DrogePlusApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@DrogePlusApp)
            modules(dataSourceModule, repoModule, viewModelModule)
        }
    }
}