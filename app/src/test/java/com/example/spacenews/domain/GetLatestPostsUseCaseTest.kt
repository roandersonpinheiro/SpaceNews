//package com.example.spacenews.domain
//
//import com.example.spacenews.core.Query
//import com.example.spacenews.data.SpaceFlightNewsCategory
//import com.example.spacenews.data.entities.model.Post
//import junit.framework.Assert.assertTrue
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.first
//import kotlinx.coroutines.runBlocking
//import org.junit.AfterClass
//import org.junit.BeforeClass
//import org.junit.Test
//import org.koin.core.context.stopKoin
//import org.koin.test.KoinTest
//import org.koin.test.inject
//import kotlin.test.assertFalse
//
//class GetLatestPostsUseCaseTest : KoinTest {
//
//    val getLatestPostsUseCase: GetLatestPostsUseCase by inject()
//    private val articles = "articles"
//
//    companion object {
//
//        @BeforeClass
//        @JvmStatic
//        fun setup() {
//            configureTestAppComponent()
//        }
//
//        /**
//         * Stop Koin after each test to prevent errors
//         */
//        @AfterClass
//        fun tearDown() {
//            stopKoin()
//        }
//    }
//
//    @Test
//    fun `Should return non-null result when connecting to repository`() {
//        runBlocking {
//            val result = getLatestPostsUseCase(Query(articles))
//
//            println(result.first().size)
//
//            assertFalse(result.first().isEmpty())
//        }
//    }
//
//    @Test
//    fun `Should return Object Of The Correct Type When Connecting With Repository`() {
//        runBlocking {
//            val result = getLatestPostsUseCase(Query(articles))
//
//            println(result.first().size)
//            assertTrue(result is Flow<List<Post>>)
//        }
//    }
//
//    @Test
//    fun `Should return Empty Result When Connecting With Repository`() {
//        runBlocking {
//            val result = getLatestPostsUseCase(Query(SpaceFlightNewsCategory.ARTICLES.value))
//
//            println(result.first().size)
//
//            assertFalse(result.first().isEmpty())
//        }
//    }
//    @Test
//    fun `should correctly stop a received object`() {
//        runBlocking {
//            val response = getLatestPostsUseCase(Query(SpaceFlightNewsCategory.ARTICLES.value))
//            val result = response.first().first()
//
//            assertTrue(result is Post)
//        }
//    }
//}




// Uses cases
//    @Test
//    fun `should return list with success`() = runBlocking {
//
//        //GIVEN
//
//        coEvery { postRepository.listPosts(PostsMock.type) } returns PostsMock.mockPostEntityListNetwork()
//
//        //WHEN
//        val result = getLatestPostsUseCase(Query(PostsMock.type)).first()
//
//        //THEN
//        assertEquals(result.data?.size, PostsMock.mockPostResourceSuccess().data?.size)
//    }

//    @Test(expected = RemoteException::class)
//    fun `should return exception with error`() = runBlocking {
//
//        //GIVEN
//
//        coEvery { postRepository.listPosts(PostsMock.type) } throws RemoteException("Could not connect to SpaceFlightNews. Displaying cached content.")
//
//        //WHEN
//        val result = getLatestPostsUseCase(Query(PostsMock.type)).first()
//
//        assertEquals(
//            result.error?.message,
//            "Could not connect to SpaceFlightNews. Displaying cached content."
//        )
//
//
//    }

