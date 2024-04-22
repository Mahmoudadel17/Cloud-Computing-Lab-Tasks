package com.example.cloud.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cloud.home.HomeScreen
import com.example.cloud.home.ImagesScreen
import com.example.cloud.home.NotificationsScreen


@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.Home.route) {
        composable(route = Screens.Home.route) {
            HomeScreen(navController)
        }
        composable(route = Screens.Notifications.route) {
            NotificationsScreen()
        }
        composable(route = Screens.Images.route) {
            ImagesScreen()
        }
    }
}