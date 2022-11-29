package com.example.spacenews.domain

import com.example.spacenews.core.Query
import com.example.spacenews.data.SpaceFlightNewsCategory
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test
import org.koin.core.context.stopKoin
import org.koin.test.AutoCloseKoinTest
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.assertTrue


class GetLatestPostsTitleContainsUseCaseTest : AutoCloseKoinTest() {
    val getLatestPostsTitleContainsUseCase: GetLatestPostsTitleContainsUseCase by inject()
    private val type = SpaceFlightNewsCategory.ARTICLES.value
    private val searchString = "mars"

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
    fun `should return valid results when executing search`() {
        runBlocking {
            val result = getLatestPostsTitleContainsUseCase(Query(type, searchString))
            var assertion = true
            result.first().forEach { post ->
                println(post.title)
                assertion = assertion && post.title.lowercase().contains(searchString)
            }
            assertTrue(assertion)

        }
    }

}