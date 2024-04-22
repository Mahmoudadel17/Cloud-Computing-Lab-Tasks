package com.example.cloud.utility

import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.util.Log
import android.widget.Toast
import com.example.cloud.components.Constants
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.tasks.await

class FirebaseFirestoreStorage {
    private var storageRef: StorageReference = FirebaseStorage.getInstance().reference.child("Images")
    private var firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private lateinit var sharedPreferences: SharedPreferences
    // Toast.makeText(this," ${Firebase.installations.id}",Toast.LENGTH_LONG).show()

    fun uploadImage(image: Uri,context: Context){

        sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        storageRef = storageRef.child(System.currentTimeMillis().toString())
        storageRef.putFile(image).addOnCompleteListener { task ->


            if (task.isSuccessful) {
                storageRef.downloadUrl.addOnSuccessListener { uri ->

                    val map = HashMap<String, Any>()
                    map["pic"] = uri.toString()
                    val token = sharedPreferences.getString(Constants.TOKEN,"")
                    Log.d("TAG","--------------------- ${token} ------------------------")

                    firebaseFirestore.collection(token!!).add(map).addOnCompleteListener { firestoreTask ->

                        if (firestoreTask.isSuccessful){
                           Toast.makeText(context, "Uploaded Successfully", Toast.LENGTH_SHORT).show()
                            Log.d("TAG","--------------------- Uploaded Successfully ------------------------")
                        }else{
                            Toast.makeText(context, firestoreTask.exception?.message, Toast.LENGTH_SHORT).show()
                            Log.d("TAG","--------------------- ${firestoreTask.exception?.message} ------------------------")

                        }

                    }
                }

            }else{
                Toast.makeText(context, task.exception?.message, Toast.LENGTH_SHORT).show()
                Log.d("TAG","--------------------- ${task.exception?.message} ------------------------")

            }
        }
    }


    suspend fun getAllImages(context:Context): List<String> {
        sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        val mList = mutableListOf<String>()
        try {
            val token = sharedPreferences.getString(Constants.TOKEN,"")
            Log.d("TAG","--------------------- ${token} ------------------------")

            val querySnapshot = firebaseFirestore.collection(token!!).get().await()
            for (document in querySnapshot.documents) {
              Log.d("Tag"," -------------------------------- ${document} ---------------------------")

                val pic = document.getString("pic")
                pic?.let {
                    mList.add(it)
                }
            }
        } catch (e: Exception) {
            Log.d("Tag", "Error fetching images: ${e.message}")
        }
        return mList
    }


}