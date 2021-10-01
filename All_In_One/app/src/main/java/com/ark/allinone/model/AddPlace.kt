package com.ark.allinone.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "AddPlace")
class AddPlace {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var lat: Double = 0.0
    var lng: Double = 0.0
    var title: String? = null
}