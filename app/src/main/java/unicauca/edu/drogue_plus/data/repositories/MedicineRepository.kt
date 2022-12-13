package unicauca.edu.drogue_plus.data.repositories

import unicauca.edu.drogue_plus.data.datasource.MemoryDataSource
import unicauca.edu.drogue_plus.data.models.MapModel
import unicauca.edu.drogue_plus.data.models.MedicineModel

class MedicineRepository(private val memoryDataSource :MemoryDataSource) {
    suspend fun getMedicines(catergory :String?):List<MedicineModel>{
        return memoryDataSource.getMedicines(catergory)
    }

    suspend fun getLocation(): MapModel {
        return memoryDataSource.getLocation()
    }
}