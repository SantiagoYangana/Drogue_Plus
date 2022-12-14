package unicauca.edu.drogue_plus.data.repositories

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import unicauca.edu.drogue_plus.MEDICINE_COLLECTION
import unicauca.edu.drogue_plus.data.datasource.MemoryDataSource
import unicauca.edu.drogue_plus.data.models.MapModel
import unicauca.edu.drogue_plus.data.models.MedicineModel

class MedicineRepository(private val memoryDataSource :MemoryDataSource ,
    private val db:FirebaseFirestore, private val storage: FirebaseStorage) {

    suspend fun getMedicines(catergory :String?):List<MedicineModel>{
        val result :List<MedicineModel>
        if(catergory!=null){
            result = db.collection(MEDICINE_COLLECTION).whereEqualTo("title",catergory).get().await().toObjects()
        }else{
            result = db.collection(MEDICINE_COLLECTION).get().await().toObjects()
        }
        return result.map {
            val ref = storage.reference
            val iconRef = ref.child(it.icon)
            it.icon = iconRef.downloadUrl.await().toString()
            return@map it
        }
    }

    suspend fun getLocation(municipio: String): MapModel {
        return memoryDataSource.getLocation(municipio)
    }
}