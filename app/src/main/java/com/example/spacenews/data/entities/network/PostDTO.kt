package com.example.spacenews.data.entities.network

import com.example.spacenews.data.entities.db.PostDb
import com.example.spacenews.data.entities.model.Post

data class PostDTO(
    val id: Int,
    val title: String,
    val url: String,
    val imageUrl: String,
    val summary: String,
    val publishedAt: String,
    val updatedAt: String?,
    var launches: Array<LaunchDTO> = emptyArray()
) {

    fun toModel(): Post = Post(
        id = id,
        title = title,
        url = url,
        imageUrl = imageUrl,
        summary = summary,
        publishedAt = publishedAt,
        updatedAt = updatedAt,
        launches = launches.toModel()
    )
    fun toDb(): PostDb = PostDb(
        id = id,
        title = title,
        url = url,
        imageUrl = imageUrl,
        summary = summary,
        publishedAt = publishedAt,
        updatedAt = updatedAt,
        launches = launches.toDb()
    )
}

fun List<PostDTO>.toModel(): List<Post> =
    this.map {
        it.toModel()
    }

fun List<PostDTO>.toDb(): List<PostDb> =
    this.map {
        it.toDb()
    }
