package com.example.cloud.navigation


sealed class Screens(val route:String){

    data object Home : Screens(route = "home")
    data object Notifications : Screens(route = "notifications")


}