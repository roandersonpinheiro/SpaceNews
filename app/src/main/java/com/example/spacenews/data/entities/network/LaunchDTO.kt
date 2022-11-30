package com.example.spacenews.data.network

import com.example.spacenews.data.model.Launch

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
