package com.example.spacenews.data

import com.example.spacenews.data.services.SpaceFlightNewsService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class SpaceFlightNewsServiceTest {

    lateinit var mockWebServer: MockWebServer
    lateinit var service: SpaceFlightNewsService

    @Before
    fun createService() {
        val factory = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(MoshiConverterFactory.create(factory))
            .build()
            .create(SpaceFlightNewsService::class.java)
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    @Test
    fun `should Reach Correct Endpoint When Receiving Parameter`() {
        runBlocking {
            // testar o endpoint articles
            mockWebServer.enqueue(MockResponse().setBody("[]"))
            service.listPosts(SpaceFlightNewsCategory.ARTICLES.value)
            val request1 = mockWebServer.takeRequest()
            assertEquals(request1.path, "/articles")

            // testar o endpoint blogs
            mockWebServer.enqueue(MockResponse().setBody("[]"))
            service.listPosts(SpaceFlightNewsCategory.BLOGS.value)
            val request2 = mockWebServer.takeRequest()
            assertEquals(request2.path, "/blogs")

            // testar o endpoint reports
            mockWebServer.enqueue(MockResponse().setBody("[]"))
            service.listPosts(SpaceFlightNewsCategory.REPORTS.value)
            val request3 = mockWebServer.takeRequest()
            assertEquals(request3.path, "/reports")
        }
    }

    @Test
    fun `should reach correct endpoint when receiving query with option`() {
        runBlocking {
            mockWebServer.enqueue(MockResponse().setBody("[]"))
            service.listPostsTitleContains("articles", "mars")
            val request = mockWebServer.takeRequest()
            assertEquals(request.path, "/articles?title_contains=mars")
        }
    }

    @Test
    fun `should reach correct endpoint when receiving query with null option`() {
        runBlocking {
            mockWebServer.enqueue(MockResponse().setBody("[]"))
            service.listPostsTitleContains("articles", null)
            val request = mockWebServer.takeRequest()
            assertEquals(request.path, "/articles")
        }
    }
}
