package com.example.cloud

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.example.cloud.components.Constants
import com.example.cloud.components.Notification
import com.google.firebase.Firebase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FirebaseMessagingServiceM2 : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        // check message not null and call
        message.notification?.let {
            createNotification(it.title!!,it.body!!)

//            // Write a message to the database
//            val database = Firebase.database
//            val myRef = database.getReference("message")
//
//            myRef.setValue("Hello, World!")

            // add notification to realtime database
            val database = Firebase.database
            val databaseRef = database.getReference("notifications")
            val notification = Notification(it.title!!,it.body!!)

            val newNotificationRef = databaseRef.push()




            newNotificationRef.setValue(notification)
                .addOnSuccessListener {
                    Log.d("onMessageReceived","successfully saved")
                }
                .addOnFailureListener {
                    Log.d("onMessageReceived","Failure")
                }
        }
    }


    private fun createNotification(title:String, message:String){

        // make intent to open activity when user click on notification
        val intent = Intent(this,MainActivity::class.java)
        // flag to make activity in the top of stack
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val pendingIntent = PendingIntent.getActivity(this, Constants.REQUEST_CODE,intent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE)


        //
        val notification = NotificationCompat.Builder(applicationContext, Constants.CHANNEL_ID)
            .setSmallIcon(R.drawable.cloud)
            .setBadgeIconType(R.drawable.cloud)
            .setAutoCancel(true)
            .setVibrate(longArrayOf(1000,1000,1000,1000))
            .setOnlyAlertOnce(true)
            .setContentIntent(pendingIntent)
            .setContentTitle(title)
            .setContentText(message)
            .build()


        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val notificationChannel = NotificationChannel(
            Constants.CHANNEL_ID,
            Constants.CHANNEL_NAME,NotificationManager.IMPORTANCE_HIGH)
        notificationManager.createNotificationChannel(notificationChannel)

        notificationManager.notify(Constants.REQUEST_CODE,notification)



    }


}