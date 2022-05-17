package com.example.actividad2

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope

class MainActivityViewModel(application: Application, val model: Model) :
    AndroidViewModel(application) {

    fun loadData() {
        model.loadData()
    }

}