package com.example.spacenews.domain

import com.example.spacenews.core.Query
import com.example.spacenews.core.UseCase
import com.example.spacenews.data.model.Post
import com.example.spacenews.data.repository.PostRepository
import kotlinx.coroutines.flow.Flow

class GetLatestPostsTitleContainsUseCase(private val repository: PostRepository) :
    UseCase<Query, List<Post>>() {

    override suspend fun execute(param: Query): Flow<List<Post>> =
        repository.listPostsTitleContains(param.type, param.option)


}