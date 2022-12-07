package unicauca.edu.drogue_plus.data.repositories

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.delay
import kotlinx.coroutines.tasks.await
import unicauca.edu.drogue_plus.USER_COLLECTION
import unicauca.edu.drogue_plus.data.datasource.MemoryDataSource
import unicauca.edu.drogue_plus.data.models.UserModel

class LoginRepository(
    private val memoryDataSource: MemoryDataSource,
    private val authService: FirebaseAuth,
    private val db: FirebaseFirestore)
{
    suspend fun login(email: String, password: String) {
        try {
            authService.signInWithEmailAndPassword(email,password).await()
        }catch (e: FirebaseAuthException){
            throw Exception(e.message)
        }
    }

    suspend fun logOut() {
        authService.signOut()
    }

    suspend fun getCurrentUser(): UserModel? {
        val user = authService.currentUser
        if (user != null){
            var photo: String? = null
            if(user.photoUrl != null){
                photo = user.photoUrl.toString()
            }
            return UserModel(user.uid,user.displayName!!,user.email!!,"",photo)
        }
        return null
    }

    suspend fun register(name: String, gender: String, email: String, password: String){
        try{
            authService.createUserWithEmailAndPassword(email,password).await()
            val user = authService.currentUser!!
            val profileUpdate = userProfileChangeRequest {
                displayName = name
            }
            user.updateProfile(profileUpdate).await()
            val userInfo = hashMapOf(
                "gender" to gender
            )
            db.collection(USER_COLLECTION).document(user.uid).set(userInfo).await()
        }catch (e: FirebaseAuthException){
            throw Exception(e.message)
        }
    }
}