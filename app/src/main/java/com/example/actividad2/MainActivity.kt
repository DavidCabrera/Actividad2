package com.example.actividad2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.github.ajalt.timberkt.d
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    val viewModel: MainActivityViewModel by viewModel()

    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        d { "onCreate" }

        // crate the adapter
        movieAdapter = MovieAdapter(
            movieSelected = {
                d { "Selected movie $it!!!" }
            },
            removeMovie = {
                d { "Remove movie $it !!!" }
                removeMovie(it)
            },
            displayMovie = {
                d { "Display movie $it !!!" }
                displayMovie(it)
            }
        )

        /*findViewById<Button>(R.id.movieName).setOnClickListener {
            startActivity(Intent(this, DetailsActivity::class.java))
        }*/

        // set the adapter
        findViewById<RecyclerView>(R.id.movieList).adapter = movieAdapter

        // subscribe to data changes
        lifecycleScope.launchWhenResumed {
            viewModel.movies.collect {
                // submit list
                movieAdapter.submitList(it)
            }
        }


    }

    private fun removeMovie(movie: Movie) {
        viewModel.removeMovie(movie)
    }

    private fun displayMovie(movie: Movie) {
        val intent = Intent(this, DetailsActivity::class.java)
        val  bundle = Bundle()
        //intent.putExtra("var1", "variable exitosa")
        intent.putExtra("movie", movie)
        startActivity(intent)
        //startActivity(Intent(this, DetailsActivity::class.java));
    }
}