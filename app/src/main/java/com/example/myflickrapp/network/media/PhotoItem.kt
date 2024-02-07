package com.example.myflickrapp.network.media

data class PhotoItem(
    val id: String,
    val title: String,
    val media: Media,
    val description: String,
    val author: String,
    val published: String
)