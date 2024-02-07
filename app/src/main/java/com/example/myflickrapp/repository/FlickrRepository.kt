package com.example.myflickrapp.repository

import com.example.myflickrapp.network.api.FlickrApiService
import com.example.myflickrapp.network.media.PhotoItem

class FlickrRepository(private val apiService: FlickrApiService) {
    suspend fun searchPhotos(tags: String): Result<List<PhotoItem>> {
        return try {
            val response = apiService.searchPhotos(tags)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!.items)
            } else {
                Result.failure(RuntimeException("API call failed"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}