package unicauca.edu.drogue_plus.data.datasource

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
}