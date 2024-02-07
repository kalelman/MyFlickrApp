package com.example.myflickrapp.ui.theme.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myflickrapp.ui.theme.components.ImageThumbnail
import com.example.myflickrapp.viewmodel.MainViewModel
import com.google.gson.Gson
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun PhotosScreen(viewModel: MainViewModel, navController: NavController) {
    // Set the initial value of searchText to "porcupine"
    var searchText by remember { mutableStateOf("porcupine") }

    // Use LaunchedEffect to perform an initial search when the screen is first displayed
    LaunchedEffect(Unit) {
        viewModel.searchPhotos(searchText)
    }

    Column {
        TextField(
            value = searchText,
            onValueChange = { newText ->
                searchText = newText
                viewModel.searchPhotos(newText.trim()) // Trigger search
            },
            label = { Text("Search") },
            singleLine = true,
            trailingIcon = {
                if (viewModel.isLoading.value) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(20.dp)
                            .padding(end = 8.dp)
                    )
                } else {
                    IconButton(onClick = { viewModel.searchPhotos(searchText.trim()) }) {
                        Icon(Icons.Filled.Search, "search")
                    }
                }
            }
        )
        Box(modifier = Modifier.fillMaxSize()) {
            if (viewModel.isLoading.value) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(4.dp)
                ) {
                    items(viewModel.searchResults.value) { photo ->
                        ImageThumbnail(photoItem = photo) {
                            photo.id.let { _ ->
                                val photoItemJson = Gson().toJson(photo)
                                navController.navigate(
                                    "detail/${
                                        URLEncoder.encode(
                                            photoItemJson,
                                            StandardCharsets.UTF_8.toString()
                                        )
                                    }"
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

