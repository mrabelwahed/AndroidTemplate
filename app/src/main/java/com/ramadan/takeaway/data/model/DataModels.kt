package com.ramadan.takeaway.data.model

import com.google.gson.annotations.SerializedName


data class RestaurantsResponse(
    @SerializedName("restaurants")
    val restaurants: List<RestaurantItem>?
)

data class RestaurantItem(
    @SerializedName("sortingValues")
    val sortingValues: SortingValues,
    @SerializedName("name")
    val name: String,
    @SerializedName("status")
    val status: String
)

data class SortingValues(
    @SerializedName("averageProductPrice")
    val averageProductPrice: Double,
    @SerializedName("bestMatch")
    val bestMatch: Double,
    @SerializedName("distance")
    val distance: Double,
    @SerializedName("deliveryCosts")
    val deliveryCosts: Double,
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("newest")
    val newest: Double,
    @SerializedName("minCost")
    val minCost: Double,
    @SerializedName("ratingAverage")
    val ratingAverage: Double
)
