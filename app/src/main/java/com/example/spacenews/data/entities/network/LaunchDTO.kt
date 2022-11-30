package com.example.spacenews.data.entities.network

import com.example.spacenews.data.entities.model.Launch

data class LaunchDTO(
    val id: String,
    val provider: String
) {

    fun toModel(): Launch = Launch(
        id = id,
        provider = provider
    )
}

fun Array<LaunchDTO>.toModel(): Array<Launch> =
    this.map {
        it.toModel()
    }.toTypedArray()
