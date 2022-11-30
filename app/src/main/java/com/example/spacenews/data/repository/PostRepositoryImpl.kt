package com.example.spacenews.data.repository

import com.example.spacenews.core.RemoteException
import com.example.spacenews.data.model.Post
import com.example.spacenews.data.network.toModel
import com.example.spacenews.data.services.SpaceFlightNewsService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class PostRepositoryImpl(private val service: SpaceFlightNewsService) : PostRepository {

    override suspend fun listPosts(category: String): Flow<List<Post>> = flow {

        try {
            val postList = service.listPosts(category).toModel()
            emit(postList)
        } catch (e: HttpException) {
            throw RemoteException("Unable to retrieve posts" + e.message())
        }
    }

    override suspend fun listPostsTitleContains(
        category: String,
        titleContains: String?
    ): Flow<List<Post>> = flow {

        try {
            val postList =
                service.listPostsTitleContains(type = category, titleContains = titleContains)
                    .toModel()
            emit(postList)
        } catch (e: HttpException) {
            throw RemoteException("Unable to retrieve posts" + e.message())
        }
    }
}
