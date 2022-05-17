package com.example.actividad2

import com.example.actividad2.retrofit.DisneyInterface
import com.github.ajalt.timberkt.d
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class Model(private val disneyService: DisneyInterface) {

    private val coroutineScope = CoroutineScope(Job() + Dispatchers.Default)

    fun loadData() {
        d { "loadData...start" }
        coroutineScope.launch {
            val movies = disneyService.loadMovies()
            d { "Movies actually downloaded now: $movies" }
        }
        d { "loadData...end" }
    }
}