
package com.example.colorapp1

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ColorDao {
    @Insert
    suspend fun insert(colorEntity: ColorEntity)

    @Query("SELECT * FROM color_table ORDER BY time DESC")
    fun getAllColors(): LiveData<List<ColorEntity>>
}
