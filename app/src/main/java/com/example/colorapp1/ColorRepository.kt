package com.example.colorapp1

import android.app.Application
import androidx.lifecycle.LiveData

class ColorRepository(application: Application) {
    private val colorDao: ColorDao
    val allColors: LiveData<List<ColorEntity>>

    init {
        val database = ColorDatabase.getDatabase(application)
        colorDao = database.colorDao()
        allColors = colorDao.getAllColors()
    }

    suspend fun insert(color: ColorEntity) {
        colorDao.insert(color)
    }
}
