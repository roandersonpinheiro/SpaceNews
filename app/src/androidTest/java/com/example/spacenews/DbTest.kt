package com.example.spacenews

import com.example.spacenews.data.entities.db.LaunchDb
import com.example.spacenews.data.entities.db.PostDb
import org.junit.Before

open class DbTest {

    lateinit var dbPosts: List<PostDb>

    @Before
    fun createPostsForTest() {
        // um Post sem eventos de lançamento
        val postNoLaunches = PostDb(
            id = 12783,
            title = "SpaceX ready for back to back astronaut, Starlink launches",
            url = "https://www.teslarati.com/spacex-back-to-back-starlink-astronaut-launches-crew-3/",
            imageUrl = "https://spacenews.com/wp-content/uploads/2021/11/crew2-chutes.jpg",
            summary = "Two SpaceX Falcon 9 rockets remain on track to attempt back-to-back.",
            publishedAt = "2021-11-10T10:07:44.000Z",
            updatedAt = "2021-11-10T10:08:01.340Z",
            launches = emptyArray(),
        )

        val postWithLaunches = PostDb(
            id = 12782,
            title = "Crew-3 mission cleared for launch",
            url = "https://spacenews.com/crew-3-mission-cleared-for-launch/",
            imageUrl = "https://spacenews.com/wp-content/uploads/2021/11/crew2-chutes.jpg",
            summary = "NASA and SpaceX are ready to proceed with the launch of a commercial.",
            publishedAt = "2021-11-10T09:27:02.000Z",
            updatedAt = "2021-11-10T09:38:23.654Z",
            launches = arrayOf(
                LaunchDb(
                    id = "0d779392-1a36-4c1e-b0b8-ec11e3031ee6",
                    provider = "Launch Library 2"
                )
            )

        )

        dbPosts = listOf(postWithLaunches, postNoLaunches)
    }
}
