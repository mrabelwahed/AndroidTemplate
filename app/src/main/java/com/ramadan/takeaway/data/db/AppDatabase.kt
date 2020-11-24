package com.ramadan.takeaway.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [RestaurantEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val DATABASE_NAME = "restaurants-db"
    }
    abstract fun restaurantsDao(): RestaurantsDAO
}
