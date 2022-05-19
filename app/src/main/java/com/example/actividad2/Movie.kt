package com.example.actividad2

import com.squareup.moshi.Json
import java.io.Serializable

data class Movie(
    val id: Int,
    val name: String,
    val release: String,
    @Json(name = "poster")
    val posterUrl: String,
    val playtime: String,
    val description: String,
    val gif: String
) : Serializable
{
    fun asStoredMovie(): StoredMovie {
        return StoredMovie.newBuilder()
            .setId(id)
            .setName(name)
            .setRelease(release)
            .setPosterUrl(posterUrl)
            .setPlaytime(playtime)
            .setDescription(description)
            .setGif(gif)
            .build()
    }
}


