package com.example.actividad2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;


public class MainActivityJava extends AppCompatActivity {

    private MainActivityViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //findViewById(R.id.loadButton).setOnClickListener({viewModel.loadData()});

    }
}