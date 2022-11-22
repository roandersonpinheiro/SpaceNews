package com.example.spacenews.data.repository


import com.example.spacenews.core.RemoteException
import com.example.spacenews.data.model.Post
import com.example.spacenews.data.services.SpaceFlightNewsService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException


class PostRepositoryImpl(private val service: SpaceFlightNewsService) : PostRepository {


    override suspend fun listPosts(category: String): Flow<List<Post>> = flow {


        try {
            val postList = service.listPosts(category)
            emit(postList)
        } catch (ex: HttpException) {
            throw RemoteException("Unable to retrieve posts")
        }

    }
}