package com.example.spacenews.domain

import com.example.spacenews.core.UseCase
import com.example.spacenews.data.model.Post
import com.example.spacenews.data.repository.PostRepository
import kotlinx.coroutines.flow.Flow

class GetLatestPostsUseCase(private val repository: PostRepository) :
    UseCase<String, List<Post>>() {

    override suspend fun execute(param: String): Flow<List<Post>> = repository.listPosts(param)


}