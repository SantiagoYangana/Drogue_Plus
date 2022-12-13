package unicauca.edu.drogue_plus.data.datasource

import unicauca.edu.drogue_plus.R
import unicauca.edu.drogue_plus.data.models.MedicineModel
import unicauca.edu.drogue_plus.data.models.UserModel

class MemoryDataSource {

    suspend fun getCurrentUser():UserModel{
        return UserModel(
            "1",
            "Pepito",
            "Perez",
            "pep@gmail.com",
            "https://memes.co.in/memes/update/uploads/2021/12/InShot_20211209_222013681-768x768.jpg"
        )
    }
    suspend fun getMedicines(category : String?): List<MedicineModel>{
        var list = listOf(
            MedicineModel(title = "Acetaminofen", R.drawable.img_aceta.toString(), state = "Medicamento Vigente"),
            MedicineModel(title = "Aspirina", R.drawable.img_aspirina.toString(), state = "Medicamento NO Vigente"),
            MedicineModel(title = "Loratadina", R.drawable.img_lorata.toString(), state = "Medicamento Vigente"),
            MedicineModel(title = "Ranitidina", R.drawable.img_ranitidina.toString(), state = "Medicamento Vigente"),
            MedicineModel(title = "Dolex", R.drawable.img_dolex.toString(), state = "Medicamento NO Vigente"),
            MedicineModel(title = "Acetaminofen", R.drawable.img_aceta.toString(), state = "Medicamento Vigente"),
            MedicineModel(title = "Dolex", R.drawable.img_dolex.toString(), state = "Medicamento NO Vigente")
        )

        if (category !=null){
            return list.filter { c -> c.title == category}
        }
        return list
    }
}