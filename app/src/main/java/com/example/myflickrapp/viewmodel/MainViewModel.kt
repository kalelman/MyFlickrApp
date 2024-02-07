package com.example.myflickrapp.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myflickrapp.network.media.PhotoItem
import com.example.myflickrapp.repository.FlickrRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: FlickrRepository): ViewModel() {
    var searchResults = mutableStateOf<List<PhotoItem>>(emptyList())
    var isLoading = mutableStateOf(false)
    private var errorMessage = mutableStateOf("")

    fun searchPhotos(tags: String) {
        isLoading.value = true
        errorMessage.value = ""
        viewModelScope.launch {
            val result = repository.searchPhotos(tags)
            isLoading.value = false
            result.onSuccess { items ->
                searchResults.value = items
            }.onFailure { error ->
                errorMessage.value = error.message ?: "An error occurred"
            }
        }
    }
}
