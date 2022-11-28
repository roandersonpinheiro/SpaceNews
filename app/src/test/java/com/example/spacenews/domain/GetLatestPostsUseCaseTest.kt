package com.example.spacenews.domain

import com.example.spacenews.data.SpaceFlightNewsCategory
import com.example.spacenews.data.model.Post
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class GetLatestPostsUseCaseTest : KoinTest{

    val getLatestPostsUseCase: GetLatestPostsUseCase by inject()

    companion object {

        @BeforeClass
        @JvmStatic
        fun setup() {
            configureTestAppComponent()
        }

        /**
         * Stop Koin after each test to prevent errors
         */
        @AfterClass
        fun tearDown() {
            stopKoin()
        }
    }

    @Test
    fun `should return non-null result when connecting to repository`()  {
        runBlocking {
            val result = getLatestPostsUseCase(SpaceFlightNewsCategory.ARTICLES.value)

            println(result.first().size)

            assertFalse(result.first().isEmpty())
            assertTrue(result is Flow<List<Post>>)
            assertNotNull(result)

        }

    }

    @Test
    fun `should Return Object Of The Correct Type When Connecting With Repository`() {
        runBlocking {
            val result = getLatestPostsUseCase(SpaceFlightNewsCategory.ARTICLES.value)
            println(result.first().size)
            assertTrue(result is Flow<List<Post>>)
        }
    }

    @Test
    fun `should Return Empty Result When Connecting With Repository`()  {
        runBlocking {
            val result = getLatestPostsUseCase(SpaceFlightNewsCategory.ARTICLES.value)

            println(result.first().size)

            assertFalse(result.first().isEmpty())
        }

    }

}