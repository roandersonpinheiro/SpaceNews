package com.example.spacenews.data.services

import com.example.spacenews.data.model.Post
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface SpaceFlightNewsService {


    @GET("{type}")
    suspend fun listPosts(@Path("type") type: String): List<Post>

    @GET("{type}")
    suspend fun listPostsTitleContains(
        @Path("type") type: String,
        @Query("title_contains") titleContains: String?
    ): List<Post>
}