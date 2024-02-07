package com.example.myflickrapp.ui.theme.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myflickrapp.network.media.PhotoItem
import com.example.myflickrapp.ui.theme.screens.DetailView
import com.example.myflickrapp.ui.theme.screens.PhotosScreen
import com.example.myflickrapp.viewmodel.MainViewModel
import com.google.gson.Gson

@Composable
fun AppNavigation(viewModel: MainViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "photos") {
        composable("photos") {
            PhotosScreen(viewModel = viewModel, navController = navController)
        }
        composable(
            "detail/{photoItem}",
            arguments = listOf(navArgument("photoItem") { type = NavType.StringType })
        ) { backStackEntry ->
            val photoItemJson = backStackEntry.arguments?.getString("photoItem") ?: ""
            val photoItem = Gson().fromJson(photoItemJson, PhotoItem::class.java)
            DetailView(photoItem = photoItem)
        }
    }
}