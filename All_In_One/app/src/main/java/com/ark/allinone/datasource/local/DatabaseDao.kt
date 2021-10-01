package com.ark.allinone.datasource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ark.allinone.model.AddPlace

@Database(entities = [AddPlace::class], version = 1)
abstract class DatabaseDao : RoomDatabase() {

    abstract fun addPlaceDao(): AddPlaceDao

    companion object {
        @Volatile
        private var INSTANCE: DatabaseDao? = null
        fun getDBInstance(context: Context): RoomDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context, DatabaseDao::class.java,
                        "AllInOne"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }


}