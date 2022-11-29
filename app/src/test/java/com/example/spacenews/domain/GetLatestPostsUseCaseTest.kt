package com.example.spacenews.domain

import com.example.spacenews.core.Query
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

class GetLatestPostsUseCaseTest : KoinTest {

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

//    @Test
//    fun `Should return non-null result when connecting to repository`() {
//        runBlocking {
//            val result = getLatestPostsUseCase(Query(SpaceFlightNewsCategory.ARTICLES.value))
//
//            println(result.first().size)
//
//            assertFalse(result.first().isEmpty())
//            //Obs
//            assertTrue(true)
//            assertNotNull(result)
//
//        }
//
//    }

//    @Test
//    fun `Should return Object Of The Correct Type When Connecting With Repository`() {
//        runBlocking {
//            val result = getLatestPostsUseCase(Query(SpaceFlightNewsCategory.ARTICLES.value))
//            println(result.first().size)
//            //Obs
//            assertTrue(true)
//        }
//    }

    @Test
    fun `Should return Empty Result When Connecting With Repository`() {
        runBlocking {
            val result = getLatestPostsUseCase(Query(SpaceFlightNewsCategory.ARTICLES.value))

            println(result.first().size)

            assertFalse(result.first().isEmpty())
        }

    }

}