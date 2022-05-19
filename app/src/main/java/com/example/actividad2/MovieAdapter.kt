package com.example.actividad2

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.ajalt.timberkt.d
import com.squareup.picasso.Picasso
import android.content.Intent

class MovieAdapter(
    val movieSelected: (Movie) -> Unit,
    val removeMovie: (Movie) -> Unit,
    val displayMovie: (Movie) -> Unit,
) :
    ListAdapter<Movie, MovieAdapter.MovieViewHolder>(MovieDiffCallback) {

    class MovieViewHolder(
        view: View,
        movieSelected: (Int) -> Unit,
        removeMovie: (Int) -> Unit,
        displayMovie: (Int) -> Unit
    ) :
        RecyclerView.ViewHolder(view) {
        private val nameTextView: TextView = view.findViewById(R.id.movieName)
        private val releaseTextView: TextView = view.findViewById(R.id.release)
        private val movieImage: ImageView = view.findViewById(R.id.movieImage)
        private val removeButton: ImageButton = view.findViewById(R.id.removeButton)

        init {
            view.setOnClickListener { movieSelected(adapterPosition) }
            removeButton.setOnClickListener {
                d { "Remove movie...." }
                removeMovie(adapterPosition)
            }
            movieImage.setOnClickListener{
                d { "Display movie...." }
                displayMovie(adapterPosition)
            }
        }

        fun bind(movie: Movie) {
            nameTextView.text = movie.name
            releaseTextView.text = movie.release
            Picasso.get()
                .load(Uri.parse(movie.posterUrl))
                .resize(200, 200)
                .centerInside()
                .placeholder(R.drawable.camera_image)
                .into(movieImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        d { "onCreateViewHolder()" }
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(
            view,
            { movieSelected(getItem(it)) },
            { removeMovie(getItem(it)) },
            { displayMovie(getItem(it)) }
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        d { "onBindViewHolder(): ${getItem(position).name}" }
        holder.bind(getItem(position))
    }

}

object MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {

    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

}