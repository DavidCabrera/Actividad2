package com.example.actividad2

import androidx.datastore.core.DataStore
import com.github.ajalt.timberkt.d
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class Model(private val moviesDataStore: DataStore<MovieStore>, private val disneyService: DisneyInterface) {

    private val coroutineScope = CoroutineScope(Job() + Dispatchers.Default)

    private val _movies = MutableStateFlow<List<Movie>>(listOf())
    val movies = _movies as StateFlow<List<Movie>>

    init {

        coroutineScope.launch {
            d { "loadData...start" }
            val moviesFromJson = disneyService.loadMovies()
            d { "Movies actually downloaded now: $movies" }
            d { "loadData...end" }

            moviesDataStore.data
                .map { it.initialized }
                .filter { !it }
                .first {
                    d { "Initialize data store..." }
                    initDataStore(moviesFromJson)
                    return@first true
                }
        }

        coroutineScope.launch {
            moviesDataStore.data
                .collect { movieStore ->
                    d { "Movies count: ${movieStore.moviesCount}" }
                    val movies = movieStore.moviesList.map {
                        Movie(it.id, it.name, it.release, it.posterUrl, it.playtime, it.description, it.gif)
                    }
                    _movies.value = movies
                }
        }

    }

    private fun initDataStore(moviesFromJson: List<Movie>) {
        // create moshi parser
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val type = Types.newParameterizedType(List::class.java, Movie::class.java)
        val adapter = moshi.adapter<List<Movie>>(type)

        // read the json
        //val moviesFromJson: List<Movie> = adapter.fromJson(moviesData)!!

        // create the storedMovies list
        val moviesToStore = moviesFromJson.map { it.asStoredMovie() }

        // save data to data store
        coroutineScope.launch {
            moviesDataStore.updateData { movieStore ->
                movieStore.toBuilder()
                    .addAllMovies(moviesToStore)
                    .setInitialized(true)
                    .build()
            }
        }

    }

    fun removeMovie(movie: Movie) {
        val toStoreMovies = movies.value
            .filter { it.id != movie.id }
            .map { it.asStoredMovie() }

        coroutineScope.launch {
            moviesDataStore.updateData { movieStore ->
                movieStore.toBuilder()
                    .clearMovies()
                    .addAllMovies(toStoreMovies)
                    .build()
            }
        }
    }
}