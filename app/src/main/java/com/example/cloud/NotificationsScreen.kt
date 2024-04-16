package com.example.cloud

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cloud.components.Notification
import com.example.cloud.components.NotificationView
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.google.firebase.database.getValue


@Composable
fun NotificationsScreen() {
    var allNotifications by remember {
        mutableStateOf(emptyList<Notification>())
    }

    val context = LocalContext.current
    LazyColumn (Modifier.padding(12.dp)){
        item {
            Text(text = "Notifications",color = Color(0xFFC9CBCC), fontSize = 50.sp)
        }
        val database = Firebase.database
        val databaseRef = database.getReference("notifications")

        // Read from the database
        databaseRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = snapshot.getValue<HashMap<String, Notification>>()
                value?.let {
                    val list = ArrayList<Notification>()
                    for ( (_,notification) in it){
                        list.add(notification)
                    }
                    list.reverse()
                    allNotifications = list

                }

            }
            override fun onCancelled(error: DatabaseError) {
                Log.w("TAG", "Failed to read value.", error.toException())
            }

        })

        items(allNotifications){
            NotificationView(title = it.title!!, message = it.message!!)
        }
    }
}