package com.example.cloud.home

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cloud.components.ViewImage
import com.example.cloud.utility.FirebaseFirestoreStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@Composable
fun ImagesScreen() {
    val firebaseFirestoreStorage = FirebaseFirestoreStorage()
    var imagesList = emptyList<String>()
    val context = LocalContext.current
    runBlocking {
        launch(Dispatchers.IO) {
            imagesList = firebaseFirestoreStorage.getAllImages(context)
        }
    }

    LazyColumn (modifier = Modifier.padding(12.dp)){
        items(imagesList){
            ViewImage(image = it)
        }

    }

}