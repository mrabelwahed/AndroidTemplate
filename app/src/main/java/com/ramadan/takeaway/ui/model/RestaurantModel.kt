package com.ramadan.takeaway.ui.model

data class RestaurantModel(
    val name: String,
    val status: String,
    val averageProductPrice: Double,
    val bestMatch: Double,
    val distance: Double,
    val deliveryCosts: Double,
    val popularity: Double,
    val newest: Double,
    val minCost: Double,
    val ratingAverage: Double,
    var isFavorite: Boolean = false
)
