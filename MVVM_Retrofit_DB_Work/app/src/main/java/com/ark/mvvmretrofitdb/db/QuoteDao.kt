package com.ark.mvvmretrofitdb.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ark.mvvmretrofitdb.model.Results

@Dao
interface QuoteDao {

    @Insert
    suspend fun addQuotes(quotes: List<Results>)

    @Query("SELECT * FROM quote")
    suspend fun getQuotes(): List<Results>
}