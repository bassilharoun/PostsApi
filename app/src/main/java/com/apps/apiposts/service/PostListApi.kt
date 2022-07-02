package com.apps.apiposts.service;

import com.apps.apiposts.model.PostsApiData
import retrofit2.Response
import retrofit2.http.GET;

interface PostListApi {
    @GET("/posts")
    suspend fun getPosts() : Response<List<PostsApiData>>

}
