package com.example.actividad2

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.github.ajalt.timberkt.d
import com.squareup.picasso.Picasso

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        d { "onCreate DetailsActivity" }
        incomingEvent()
    }

    private fun incomingEvent() {
        d { "incomingEvent" }
        var movie = intent.extras?.get("movie") as Movie

        var nombre = findViewById<TextView>(R.id.name)
        var anio = findViewById<TextView>(R.id.releaseDetail)
        var posterMovie = findViewById<ImageView>(R.id.poster)
        var playtime = findViewById<TextView>(R.id.playtime)
        var description = findViewById<TextView>(R.id.description)
        d { "playtime" + playtime.toString() }
        d { "description" + description.toString() }
        nombre.setText(movie.name)
        anio.setText(movie.release)
        playtime.setText(movie.playtime)
        description.setText(movie.description)

        Picasso.get()
            .load(Uri.parse(movie.posterUrl))
            .resize(200, 200)
            .centerInside()
            .placeholder(R.drawable.camera_image)
            .into(posterMovie)
    }
}