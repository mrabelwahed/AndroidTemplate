package com.ramadan.takeaway.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface RestaurantsDAO {
    /**
     * Favorites a restaurant by saving it to user's favorites.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun favorite(entity: RestaurantEntity): Completable

    /**
     * UnFavorite a restaurant by deleting it from user's favorites.
     */
    @Delete
    fun unFavorite(entity: RestaurantEntity): Completable

    /**
     * Returns all user's favorite restaurants.
     */
    @Query("SELECT * FROM restaurants")
    fun getFavorites(): Single<List<RestaurantEntity>>
}
