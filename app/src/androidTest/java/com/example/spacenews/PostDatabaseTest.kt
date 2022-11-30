package com.example.spacenews

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.spacenews.data.dao.PostDao
import com.example.spacenews.data.database.PostDatabase
import com.example.spacenews.data.entities.db.PostDb
import junit.framework.Assert.assertFalse
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class PostDatabaseTest : DbTest() {

    private lateinit var dao: PostDao
    private lateinit var postDatabase: PostDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        postDatabase = Room.inMemoryDatabaseBuilder(
            context, PostDatabase::class.java
        ).build()
        dao = postDatabase.dao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
//        postDatabase.close()
    }

    @Test
    fun `Should_Record_Post_In_Database_After`() {

        lateinit var result: List<PostDb>

        runBlocking {
            result = dao.listPosts().first()
        }
        assertTrue(result.isEmpty())
        runBlocking {
            dao.saveAll(dbPosts)
            result = dao.listPosts().first()
        }
        assertFalse(result.isEmpty())
    }

    @Test
    fun `Should_ReturnPostsCorrectly_WhenReadFromDatabase`() {
        lateinit var result: PostDb
        runBlocking {
            dao.saveAll(dbPosts)
            result = dao.listPosts().first()[0]
        }
        assertTrue(result.title == dbPosts[0].title)
    }

    @Test
    fun `ShouldClearDatabase_WhenInvokingClearDb`() {
        lateinit var result: List<PostDb>
        runBlocking {
            dao.saveAll(dbPosts)
            dao.clearDb()
            result = dao.listPosts().first()
        }
        assertTrue(result.isEmpty())
    }
}
