package com.example.spacenews.mock

import com.example.spacenews.core.RemoteException
import com.example.spacenews.core.Resource
import com.example.spacenews.data.SpaceFlightNewsCategory
import com.example.spacenews.data.entities.db.LaunchDb
import com.example.spacenews.data.entities.db.PostDb
import com.example.spacenews.data.entities.model.Launch
import com.example.spacenews.data.entities.model.Post
import com.example.spacenews.data.entities.network.LaunchDTO
import com.example.spacenews.data.entities.network.PostDTO
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

object PostsMock {
    val type = SpaceFlightNewsCategory.ARTICLES.value
    val searchString = "mars"


    private val launchDto = LaunchDTO(
        id = "0d779392-1a36-4c1e-b0b8-ec11e3031ee6",
        provider = "Launch Library 2"
    )
    private val launch = Launch(
        id = "0d779392-1a36-4c1e-b0b8-ec11e3031ee6",
        provider = "Launch Library 2"
    )

    private val launchDb = LaunchDb(
        id = "0d779392-1a36-4c1e-b0b8-ec11e3031ee6",
        provider = "Launch Library 2"
    )

    fun mockPostEntityDb() = flowOf(
        listOf(
            PostDb(
                id = 1,
                title = "Planeta terra",
                url = "https://www.teslarati.com/spacex-back-to-back-starlink-astronaut-launches-crew-3/",
                imageUrl = "https://spacenews.com/wp-content/uploads/2021/11/crew2-chutes.jpg",
                summary = "Two SpaceX Falcon 9 rockets remain on track to attempt back-to-back astronaut",
                publishedAt = "2021-11-10T10:07:44.000Z",
                updatedAt = "2021-11-10T10:08:01.340Z",
                launches = arrayOf(launchDb)
            ),
            PostDb(
                id = 2,
                title = "Jupiter",
                url = "https://www.teslarati.com/spacex-back-to-back-starlink-astronaut-launches-crew-3/",
                imageUrl = "https://spacenews.com/wp-content/uploads/2021/11/crew2-chutes.jpg",
                summary = "Two SpaceX Falcon 9 rockets remain on track to attempt back-to-back astronaut",
                publishedAt = "2021-11-10T10:07:44.000Z",
                updatedAt = "2021-11-10T10:08:01.340Z",
                launches = arrayOf(launchDb)
            ),
            PostDb(
                id = 3,
                title = "Mars",
                url = "https://www.teslarati.com/spacex-back-to-back-starlink-astronaut-launches-crew-3/",
                imageUrl = "https://spacenews.com/wp-content/uploads/2021/11/crew2-chutes.jpg",
                summary = "Two SpaceX Falcon 9 rockets remain on track to attempt back-to-back astronaut",
                publishedAt = "2021-11-10T10:07:44.000Z",
                updatedAt = "2021-11-10T10:08:01.340Z",
                launches = arrayOf(launchDb)
            )

        )
    )

    fun mockPostEntityListNetworkEmpty() = flowOf(
        Resource.Success(
            data = listOf<Post>()
        )
    )


    fun mockPostEntityListNetwork() = flowOf(
        Resource.Success(
            data = listOf(
                Post(
                    id = 1,
                    title = "Planeta terra",
                    url = "https://www.teslarati.com/spacex-back-to-back-starlink-astronaut-launches-crew-3/",
                    imageUrl = "https://spacenews.com/wp-content/uploads/2021/11/crew2-chutes.jpg",
                    summary = "Two SpaceX Falcon 9 rockets remain on track to attempt back-to-back astronaut",
                    publishedAt = "2021-11-10T10:07:44.000Z",
                    updatedAt = "2021-11-10T10:08:01.340Z",
                    launches = arrayOf(launch)
                ),
                Post(
                    id = 2,
                    title = "Jupiter",
                    url = "https://www.teslarati.com/spacex-back-to-back-starlink-astronaut-launches-crew-3/",
                    imageUrl = "https://spacenews.com/wp-content/uploads/2021/11/crew2-chutes.jpg",
                    summary = "Two SpaceX Falcon 9 rockets remain on track to attempt back-to-back astronaut",
                    publishedAt = "2021-11-10T10:07:44.000Z",
                    updatedAt = "2021-11-10T10:08:01.340Z",
                    launches = arrayOf(launch)
                ),
                Post(
                    id = 3,
                    title = "Mars",
                    url = "https://www.teslarati.com/spacex-back-to-back-starlink-astronaut-launches-crew-3/",
                    imageUrl = "https://spacenews.com/wp-content/uploads/2021/11/crew2-chutes.jpg",
                    summary = "Two SpaceX Falcon 9 rockets remain on track to attempt back-to-back astronaut",
                    publishedAt = "2021-11-10T10:07:44.000Z",
                    updatedAt = "2021-11-10T10:08:01.340Z",
                    launches = arrayOf(launch)
                )
            )
        )
    )


    fun mockPostEntity() = listOf(
        Post(
            id = 1,
            title = "Planeta terra",
            url = "https://www.teslarati.com/spacex-back-to-back-starlink-astronaut-launches-crew-3/",
            imageUrl = "https://spacenews.com/wp-content/uploads/2021/11/crew2-chutes.jpg",
            summary = "Two SpaceX Falcon 9 rockets remain on track to attempt back-to-back astronaut",
            publishedAt = "2021-11-10T10:07:44.000Z",
            updatedAt = "2021-11-10T10:08:01.340Z",
            launches = arrayOf(launch)
        ),
        Post(
            id = 2,
            title = "Jupiter",
            url = "https://www.teslarati.com/spacex-back-to-back-starlink-astronaut-launches-crew-3/",
            imageUrl = "https://spacenews.com/wp-content/uploads/2021/11/crew2-chutes.jpg",
            summary = "Two SpaceX Falcon 9 rockets remain on track to attempt back-to-back astronaut",
            publishedAt = "2021-11-10T10:07:44.000Z",
            updatedAt = "2021-11-10T10:08:01.340Z",
            launches = arrayOf(launch)
        ),
        Post(
            id = 3,
            title = "Mars",
            url = "https://www.teslarati.com/spacex-back-to-back-starlink-astronaut-launches-crew-3/",
            imageUrl = "https://spacenews.com/wp-content/uploads/2021/11/crew2-chutes.jpg",
            summary = "Two SpaceX Falcon 9 rockets remain on track to attempt back-to-back astronaut",
            publishedAt = "2021-11-10T10:07:44.000Z",
            updatedAt = "2021-11-10T10:08:01.340Z",
            launches = arrayOf(launch)
        )

    )

    fun mockPostResourceSuccess(): Resource<List<Post>> =
        Resource.Success(data = mockPostEntity())

    fun mockPostResourceSuccessEmpty(): Resource<List<PostDTO>> =
        Resource.Success(data = emptyList())

    fun mockPostResourceError(): Resource<List<PostDTO>> =
        Resource.Error(
            data = null,
            error = RemoteException(
                "Could not connect to SpaceFlightNews. " +
                        "Displaying cached content."
            )
        )


}