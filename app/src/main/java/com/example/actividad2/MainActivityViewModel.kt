package com.example.actividad2

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class MainActivityViewModel(application: Application, val model: Model) :
    AndroidViewModel(application) {

    val movies = model.movies

    fun removeMovie(movie: Movie) {
        model.removeMovie(movie)
    }

}