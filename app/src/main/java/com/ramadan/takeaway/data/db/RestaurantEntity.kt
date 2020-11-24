package com.ramadan.takeaway.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "restaurants")
data class RestaurantEntity(
    @PrimaryKey
    @ColumnInfo(name = "rest_name")
    val name: String,
    @ColumnInfo(name = "is_favorite")
    var isFavorite: Boolean
)
