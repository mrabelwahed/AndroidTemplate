package com.ramadan.takeaway.data.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class AppDatabaseTest {
    private lateinit var restaurantsDAO: RestaurantsDAO
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        ).build()
        restaurantsDAO = db.restaurantsDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun favoriteRestaurantAndReadInList() {
        val rest = RestaurantEntity(name = "SeaFood", isFavorite = true)
        restaurantsDAO.favorite(rest).blockingAwait()
        restaurantsDAO.getFavorites().test().assertValue {
            list ->
            list.isNotEmpty()
        }
    }

    @Test
    @Throws(Exception::class)
    fun unFavoriteRestaurantAndReadInList() {
        val rest = RestaurantEntity(name = "SeaFood", isFavorite = true)
        restaurantsDAO.favorite(rest).blockingAwait()
        rest.copy(isFavorite = false)
        restaurantsDAO.unFavorite(rest).blockingAwait()
        restaurantsDAO.getFavorites().test().assertValue {
            list ->
            list.isEmpty()
        }
    }
}
