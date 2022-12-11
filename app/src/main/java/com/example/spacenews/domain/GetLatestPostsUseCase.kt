package com.example.spacenews.domain

import com.example.spacenews.core.Query
import com.example.spacenews.core.Resource
import com.example.spacenews.core.UseCase
import com.example.spacenews.data.entities.model.Post
import com.example.spacenews.data.repository.PostRepository
import kotlinx.coroutines.flow.Flow

class GetLatestPostsUseCase(private val repository: PostRepository) :
    UseCase<Query, Resource<List<Post>>>() {

    override suspend fun execute(param: Query): Flow<Resource<List<Post>>> =
        repository.listPosts(param.type)
}
