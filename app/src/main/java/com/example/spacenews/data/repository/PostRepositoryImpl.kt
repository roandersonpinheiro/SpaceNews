package com.example.spacenews.data.repository

import com.example.spacenews.core.RemoteException
import com.example.spacenews.core.Resource
import com.example.spacenews.core.networkBoundResource
import com.example.spacenews.data.dao.PostDao
import com.example.spacenews.data.entities.db.toModel
import com.example.spacenews.data.entities.model.Post
import com.example.spacenews.data.entities.network.PostDTO
import com.example.spacenews.data.entities.network.toDb
import com.example.spacenews.data.services.SpaceFlightNewsService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class PostRepositoryImpl(
    private val service: SpaceFlightNewsService,
    private val dao: PostDao
) : PostRepository {

    private val readFromDatabase = {
        dao.listPosts().map {
            it.sortedBy { postDb ->
                postDb.publishedAt
            }.reversed().toModel()
        }
    }

    private val clearDbAndSave: suspend (List<PostDTO>) -> Unit = { list: List<PostDTO> ->
        dao.clearDb()
        dao.saveAll(list.toDb())
    }


    override suspend fun listPosts(category: String): Flow<Resource<List<Post>>> =
        networkBoundResource(
            query = readFromDatabase,
            fetch = { service.listPosts(category) },
            saveFetchResult = { listPostDto ->
                clearDbAndSave(listPostDto)
            },
            onError = { RemoteException("Could not connect to SpaceFlightNews. Displaying cached content.") }
        )


    override suspend fun listPostsTitleContains(
        category: String,
        titleContains: String?
    ): Flow<Resource<List<Post>>> = networkBoundResource(
        query = readFromDatabase,
        fetch = { service.listPostsTitleContains(category, titleContains) },
        saveFetchResult = { listPostDto ->
            clearDbAndSave(listPostDto)
        },
        onError = { RemoteException("Could not connect to SpaceFlightNews. Displaying cached content.") }
    )

}