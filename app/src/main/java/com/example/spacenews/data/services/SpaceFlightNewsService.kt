package com.example.spacenews.data.services

import com.example.spacenews.data.entities.network.PostDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SpaceFlightNewsService {

    @GET("{type}")
    suspend fun listPosts(@Path("type") type: String): List<PostDTO>

    @GET("{type}")
    suspend fun listPostsTitleContains(
        @Path("type") type: String,
        @Query("title_contains") titleContains: String?
    ): List<PostDTO>
}
