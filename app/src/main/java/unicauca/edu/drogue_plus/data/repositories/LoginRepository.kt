package unicauca.edu.drogue_plus.data.repositories

import android.graphics.Bitmap
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.delay
import kotlinx.coroutines.tasks.await
import unicauca.edu.drogue_plus.USER_COLLECTION
import unicauca.edu.drogue_plus.data.datasource.MemoryDataSource
import unicauca.edu.drogue_plus.data.models.UserModel
import java.io.ByteArrayOutputStream

class LoginRepository(
    private val memoryDataSource: MemoryDataSource,
    private val authService: FirebaseAuth,
    private val db: FirebaseFirestore,
    private val storage: FirebaseStorage)
{
    suspend fun login(email: String, password: String, municipio: String) {
        try {
            authService.signInWithEmailAndPassword(email,password).await()
            val user = authService.currentUser!!
            val userInfo = hashMapOf(
                "municipio" to municipio
            )
            db.collection(USER_COLLECTION).document(user.uid).update(userInfo as Map<String, Any>).await()
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
            val info = db.collection(USER_COLLECTION).document(user.uid).get().await()
            return UserModel(user.uid,user.displayName!!,user.email!!,info.get("gender").toString(),photo,info.get("municipio").toString())
        }
        return null
    }

    suspend fun register(name: String, gender: String, email: String, password: String,municipio:String){
        try{
            authService.createUserWithEmailAndPassword(email,password).await()
            val user = authService.currentUser!!
            val profileUpdate = userProfileChangeRequest {
                displayName = name
            }
            user.updateProfile(profileUpdate).await()
            val userInfo = hashMapOf(
                "gender" to gender,
                "municipio" to municipio
            )
            db.collection(USER_COLLECTION).document(user.uid).set(userInfo).await()
        }catch (e: FirebaseAuthException){
            throw Exception(e.message)
        }
    }

    suspend fun uploadImage(image: Bitmap): UserModel?{
        val user = authService.currentUser
        if(user != null){
            val ref = storage.reference
            val imageRef = ref.child("${user.uid}.jpg")
            val baos: ByteArrayOutputStream = ByteArrayOutputStream()
            image.compress(Bitmap.CompressFormat.JPEG,100,baos)
            imageRef.putBytes(baos.toByteArray()).await()
            val url = imageRef.downloadUrl.await()
            val profileUpdate = userProfileChangeRequest {
                photoUri = url
            }
            user.updateProfile(profileUpdate).await()
        }
        return this.getCurrentUser()
    }
}