package com.example.myflickrapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.myflickrapp.network.api.RetrofitBuilder
import com.example.myflickrapp.repository.FlickrRepository
import com.example.myflickrapp.ui.theme.MyflickrappTheme
import com.example.myflickrapp.ui.theme.components.AppNavigation
import com.example.myflickrapp.viewmodel.MainViewModel
import com.example.myflickrapp.viewmodel.MainViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val apiService = RetrofitBuilder.flickrApiService
        val repository = FlickrRepository(apiService)
        val viewModelFactory = MainViewModelFactory(repository)

        setContent {
            MyflickrappTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    AppNavigation(viewModel = viewModelFactory.create(MainViewModel::class.java))
                }
            }
        }
    }
}