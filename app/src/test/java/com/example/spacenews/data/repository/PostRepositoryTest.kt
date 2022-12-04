package com.example.spacenews.data.repository


import com.example.spacenews.core.RemoteException
import com.example.spacenews.core.Resource
import com.example.spacenews.data.dao.PostDao
import com.example.spacenews.data.entities.model.Post
import com.example.spacenews.domain.GetLatestPostsUseCase
import com.example.spacenews.mock.PostsMock
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class PostRepositoryTest {

    private val dao = mockk<PostDao>()
    private val postRepository = mockk<PostRepository>()


    @Test(expected = RemoteException::class)
    fun `should return exception with error`() = runBlocking {

        //GIVEN
        coEvery { dao.listPosts() } returns PostsMock.mockPostEntityDb()
        coEvery { postRepository.listPosts(PostsMock.type) } throws RemoteException("Could not connect to SpaceFlightNews. Displaying cached content.")

        //WHEN
        val result = postRepository.listPosts(PostsMock.type).first()

        //THEN
    }


    @Test
    fun `should return list with success`() = runBlocking {

        //GIVEN
        coEvery { dao.listPosts() } returns PostsMock.mockPostEntityDb()
        coEvery { postRepository.listPosts(PostsMock.type) } returns PostsMock.mockPostEntityListNetwork()

        //WHEN
        val result = postRepository.listPosts(PostsMock.type).first()

        //THEN
        assertEquals(result.data?.size, PostsMock.mockPostResourceSuccess().data?.size)
        assertEquals(result.data, PostsMock.mockPostResourceSuccess().data)
        assertTrue(result is Resource.Success)
    }

    @Test
    fun `should return list empty with success`() {
        runBlocking {
            //GIVEN
            coEvery { dao.listPosts() } returns PostsMock.mockPostEntityDb()
            coEvery { postRepository.listPosts(PostsMock.type) } returns PostsMock.mockPostEntityListNetworkEmpty()

            //WHEN
            val result = postRepository.listPosts(PostsMock.type).first()

            //THEN
            assertEquals(result.data?.size, 0)
            assertTrue(result.data?.isEmpty()!!)
            assertTrue(result is Resource.Success)
        }
    }

    @Test
    fun `should return valid results when executing search`() {
        runBlocking {
            //GIVEN
            coEvery { dao.listPosts() } returns PostsMock.mockPostEntityDb()
            coEvery {
                postRepository.listPostsTitleContains(
                    PostsMock.type,
                    PostsMock.searchString
                )
            } returns PostsMock.mockPostEntityListNetwork()

            val result =
                postRepository.listPostsTitleContains(PostsMock.type, PostsMock.searchString)
                    .first()

            //THEN
            assertEquals(result.data?.size, PostsMock.mockPostResourceSuccess().data?.size)
            assertEquals(result.data, PostsMock.mockPostResourceSuccess().data)
            assertTrue(result is Resource.Success)
        }

    }


}