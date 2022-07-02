package com.apps.apiposts.model

data class PostsApiData(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
)