package unicauca.edu.drogue_plus.data.repositories

import unicauca.edu.drogue_plus.data.datasource.MemoryDataSource
import unicauca.edu.drogue_plus.data.models.MapModel

class HomeRepository(private val memoryDataSource : MemoryDataSource){
    suspend fun getLocation():MapModel{
        return memoryDataSource.getLocation()
    }
}