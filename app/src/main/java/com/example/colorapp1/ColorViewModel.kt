package com.example.colorapp1

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ColorViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ColorRepository = ColorRepository(application)
    val allColors: LiveData<List<ColorEntity>> = repository.allColors

    fun insert(color: ColorEntity) = viewModelScope.launch {
        repository.insert(color)
    }
}
