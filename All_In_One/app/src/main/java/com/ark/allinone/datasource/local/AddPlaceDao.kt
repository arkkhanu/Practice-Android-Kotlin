package com.ark.allinone.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ark.allinone.model.AddPlace

@Dao
interface AddPlaceDao {

    @Insert
    suspend fun addPlace(addPlace: AddPlace)

    @Query("SELECT * FROM AddPlace")
    suspend fun getAllPlaces(): List<AddPlace>

}