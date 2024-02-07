package com.example.myflickrapp.network.api

import com.example.myflickrapp.network.media.FlickrFeedResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrApiService {
    @GET("services/feeds/photos_public.gne?format=json&nojsoncallback=1")
    suspend fun searchPhotos(@Query("tags") tags: String): Response<FlickrFeedResponse>
}