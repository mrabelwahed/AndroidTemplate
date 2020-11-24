package com.ramadan.takeaway.data.repository

import com.ramadan.takeaway.domain.model.Restaurant
import io.reactivex.Completable
import io.reactivex.Single

interface RestaurantsRepository {

    /**
     * Return a single which will emits a list of restaurants otherwise an error.
     */
    fun getRestaurants(): Single<List<Restaurant>>

    /**
     * Return a Completable which complete bookmark operation or error.
     * @param restaurant to be bookmarked
     */
    fun favoriteRestaurant(restaurant: Restaurant): Completable

    /**
     * Return a Completable which complete un-bookmark operation or error.
     * @param restaurant to be un-bookmarked
     */
    fun unFavoriteRestaurant(restaurant: Restaurant): Completable
}
