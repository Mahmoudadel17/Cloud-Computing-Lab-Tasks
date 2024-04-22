package com.example.cloud

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.cloud.components.Constants
import com.example.cloud.navigation.AppNavigation
import com.example.cloud.ui.theme.CloudTheme
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.messaging.messaging
import android.content.SharedPreferences
import android.content.Context
import android.util.Log
import android.widget.Toast

class MainActivity : ComponentActivity() {
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Cached data is available while offline
        // and Firebase resends any writes
        // when network connectivity is restored.
        // Initialize Firebase
        FirebaseApp.initializeApp(this);
        // Enable disk persistence
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);


        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        // if first time open app set token as firebase token in sharedPreferences to use it in collection
        if (!sharedPreferences.getBoolean("FirstTime",false)){
            // first time
            Log.d("TAG","--------------------- hello form if first time ------------------------")
            Toast.makeText(this,"hello from first time",Toast.LENGTH_LONG).show()
            sharedPreferences.edit().putBoolean("FirstTime",true).apply()
            sharedPreferences.edit().putString(Constants.TOKEN,Firebase.messaging.token.toString()).apply()
        }
        setContent {
            CloudTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}


