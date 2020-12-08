package com.ramadan.takeaway.ui.sort

import com.ramadan.takeaway.ui.model.RestaurantModel
import com.ramadan.takeaway.util.OpeningState
import com.ramadan.takeaway.util.SortingKeys
import java.util.Locale
import kotlin.Comparator

object SortingOptionsHandler {
    fun buildSortByOptionsComparator(sortingKey: SortingKeys?): Comparator<RestaurantModel> {
        val byFavorites = compareByDescending<RestaurantModel> { restaurant -> restaurant.isFavorite }
        val byOpeningState: (RestaurantModel) -> OpeningState = { restaurant ->
            val status =
                restaurant.status.toUpperCase(Locale.getDefault()).replace(" ", "_")
            OpeningState.valueOf(status)
        }

        return when (sortingKey) {
            SortingKeys.AVERAGE_PRODUCT_PRICE ->
                byFavorites
                    .thenBy(byOpeningState)
                    .thenByDescending { restaurant -> restaurant.averageProductPrice }
            SortingKeys.BEST_MATCH ->
                byFavorites
                    .thenBy(byOpeningState)
                    .thenByDescending { restaurant -> restaurant.bestMatch }
            SortingKeys.DISTANCE ->
                byFavorites
                    .thenBy(byOpeningState)
                    .thenByDescending { restaurant -> restaurant.distance }
            SortingKeys.DELIVERY_COSTS ->
                byFavorites
                    .thenBy(byOpeningState)
                    .thenByDescending { restaurant -> restaurant.deliveryCosts }
            SortingKeys.POPULARITY ->
                byFavorites
                    .thenBy(byOpeningState)
                    .thenByDescending { restaurant -> restaurant.popularity }
            SortingKeys.NEWEST ->
                byFavorites
                    .thenBy(byOpeningState)
                    .thenByDescending { restaurant -> restaurant.newest }
            SortingKeys.MIN_COST ->
                byFavorites
                    .thenBy(byOpeningState)
                    .thenByDescending { restaurant -> restaurant.minCost }
            SortingKeys.RATING_AVERAGE ->
                byFavorites
                    .thenBy(byOpeningState)
                    .thenByDescending { restaurant -> restaurant.ratingAverage }
            //  Default.
            null ->
                byFavorites
                    .thenBy(byOpeningState)
                    .thenByDescending { restaurant -> restaurant.bestMatch }
        }
    }
}
