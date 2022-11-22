package com.example.spacenews.data.services

import com.example.spacenews.data.model.Post
import retrofit2.http.GET
import retrofit2.http.Path


interface SpaceFlightNewsService {


    @GET("{type}")
    suspend fun listPosts(@Path("type") type: String): List<Post>

}