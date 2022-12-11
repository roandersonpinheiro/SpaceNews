package com.example.spacenews.data.repository

import com.example.spacenews.core.Resource
import com.example.spacenews.data.entities.model.Post
import kotlinx.coroutines.flow.Flow

interface PostRepository {

    suspend fun listPosts(category: String): Flow<Resource<List<Post>>>

    suspend fun listPostsTitleContains(
        category: String,
        titleContains: String?
    ): Flow<Resource<List<Post>>>
}
