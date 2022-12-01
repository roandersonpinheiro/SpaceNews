package com.example.spacenews.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.spacenews.data.entities.db.PostDb
import kotlinx.coroutines.flow.Flow
import org.jetbrains.annotations.NotNull

@Dao
interface PostDao {

    @NotNull
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveAll(list: List<PostDb>)

    @Query("SELECT * FROM post")
    fun listPosts(): Flow<List<PostDb>>

    @Query("DELETE FROM post")
    suspend fun clearDb()
}
