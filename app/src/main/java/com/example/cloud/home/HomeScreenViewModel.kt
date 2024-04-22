package com.example.cloud.home


import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cloud.utility.FirebaseFirestoreStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeScreenViewModel : ViewModel(){
    private val firebaseFirestoreStorage = FirebaseFirestoreStorage()

    private var _state by mutableStateOf<Bitmap?>(null)
    private var imageUri by mutableStateOf<Uri?>(null)
    val state: State<Bitmap?>
        get() = derivedStateOf { _state }


    fun onSelectImage(bitmap:Bitmap?,uri:Uri?){
        _state = bitmap
        imageUri = uri
    }

    fun onUploadImageClick(context: Context){
        imageUri?.let {
            firebaseFirestoreStorage.uploadImage(it,context)
        }
        _state = null
        imageUri = null
    }

//    fun getAllImages(): List<String> {
//        // Log.d("Tag"," -------------------------------- ${mList.get(0)} ---------------------------")
//        return firebaseFirestoreStorage.getAllImages().toMutableList()
//    }

}