package com.example.spacenews.data.entities.network

import com.example.spacenews.data.entities.db.LaunchDb
import com.example.spacenews.data.entities.model.Launch

data class LaunchDTO(
    val id: String,
    val provider: String
) {

    fun toModel(): Launch = Launch(
        id = id,
        provider = provider
    )

    fun toDb(): LaunchDb = LaunchDb(
        id = id,
        provider = provider
    )
}

fun Array<LaunchDTO>.toModel(): Array<Launch> =
    this.map {
        it.toModel()
    }.toTypedArray()

fun Array<LaunchDTO>.toDb(): Array<LaunchDb> =
    this.map {
        it.toDb()
    }.toTypedArray()
