package com.example.actividad2

import com.squareup.moshi.Json

data class Movie(
    val id: Int,
    val name: String,
    val release: String,
    @Json(name = "poster")
    val posterUrl: String
)
{
    fun asStoredMovie(): StoredMovie {
        return StoredMovie.newBuilder()
            .setId(id)
            .setName(name)
            .setRelease(release)
            .setPosterUrl(posterUrl)
            .build()
    }
}


