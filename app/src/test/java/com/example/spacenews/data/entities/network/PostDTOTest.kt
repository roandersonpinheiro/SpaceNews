package com.example.spacenews.data

import com.example.spacenews.data.entities.model.Post
import com.example.spacenews.data.entities.network.LaunchDTO
import com.example.spacenews.data.entities.network.PostDTO
import junit.framework.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class PostDTOTest {

    private val launchDto = LaunchDTO(
        id = "0d779392-1a36-4c1e-b0b8-ec11e3031ee6",
        provider = "Launch Library 2"
    )

    private val postDto = PostDTO(
        id = 12783,
        title = "SpaceX ready for back to back astronaut, Starlink launches",
        url = "https://www.teslarati.com/spacex-back-to-back-starlink-astronaut-launches-crew-3/",
        imageUrl = "https://www.teslarati.jpg",
        summary = "Two SpaceX Falcon 9 rockets remain on track to attempt back-to-back astronaut",
        publishedAt = "2021-11-10T10:07:44.000Z",
        updatedAt = "2021-11-10T10:08:01.340Z",
        launches = arrayOf(launchDto)
    )

    @Test
    fun `should correctly convert to model entity`() {
        val post: Post = postDto.toModel()

        // test whether the converted object is of the right type
        assertTrue(post is Post)
        // ... if the title attribute of the DTO object is right...
        assertTrue(post.title == postDto.title)
        // ... and that the launches attribute is not empty.
        assertTrue(post.launches.isNotEmpty())
    }
}
