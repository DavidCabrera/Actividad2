package com.example.actividad2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.loadButton).setOnClickListener {
            viewModel.loadData()
        }
    }
}