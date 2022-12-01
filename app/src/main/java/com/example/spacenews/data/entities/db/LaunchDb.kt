package com.example.spacenews.data.entities.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.spacenews.data.entities.model.Launch

@Entity(tableName = "launch")
data class LaunchDb(
    @PrimaryKey
    val id: String,
    val provider: String
) {

    fun toModel(): Launch = Launch(
        id = id,
        provider = provider
    )
}

fun Array<LaunchDb>.toModel(): Array<Launch> =
    this.map {
        it.toModel()
    }.toTypedArray()
