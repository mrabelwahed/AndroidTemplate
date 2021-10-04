package com.ramadan.takeaway.data.repository

import android.content.Context
import com.google.gson.Gson
import com.ramadan.takeaway.data.db.RestaurantsDAO
import com.ramadan.takeaway.data.mapper.RestaurantEntityMapper
import com.ramadan.takeaway.data.mapper.RestaurantMapper
import com.ramadan.takeaway.data.model.RestaurantsResponse
import com.ramadan.takeaway.domain.model.Restaurant
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import java.io.IOException
import java.nio.charset.Charset

class RestaurantsRepositoryImpl(private val context: Context, private val dao: RestaurantsDAO) : RestaurantsRepository {

    private fun getRestaurantsFromJsonFile(): Single<List<Restaurant>> {
        val response = loadJSONFromAssets(context, "data.json") ?: "{}"
        val gson = Gson()
        val restaurants = gson.fromJson(
            response,
            RestaurantsResponse::class.java
        ).restaurants
        return Single.defer {
            Single.just(restaurants?.let { RestaurantMapper.mapFromEntityList(it) })
        }
    }

    private fun getFavoritesFromDB(): Single<List<Restaurant>> {
        return dao.getFavorites().map {
            RestaurantEntityMapper.mapFromEntityList(it)
        }
    }

    private fun loadJSONFromAssets(context: Context, filePath: String): String? {
        var json: String? = null
        try {
            val inputStream = context.assets.open(filePath)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, Charset.forName("UTF-8"))
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return json
    }

    override fun getRestaurants(): Single<List<Restaurant>> {
        return Single.zip(
            getRestaurantsFromJsonFile(),
            getFavoritesFromDB(),
            BiFunction<List<Restaurant>, List<Restaurant>, List<Restaurant>> { data, favorites ->

                data.map {
                    //  Update favorites.
                    favorites.map { favorite ->
                        if (favorite.name == it.name) {
                            it.isFavorite = true
                        }
                    }
                    it
                }
            }
        )
    }

    override fun favoriteRestaurant(restaurant: Restaurant): Completable {
        return dao.favorite(RestaurantEntityMapper.mapToEntity(restaurant))
    }

    override fun unFavoriteRestaurant(restaurant: Restaurant): Completable {
        return dao.unFavorite(RestaurantEntityMapper.mapToEntity(restaurant))
    }
}
