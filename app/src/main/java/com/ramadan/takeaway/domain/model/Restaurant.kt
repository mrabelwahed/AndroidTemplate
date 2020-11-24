package com.ramadan.takeaway.domain.model

data class Restaurant(
    val name: String = "",
    val status: String = "",
    val averageProductPrice: Double = 0.0,
    val bestMatch: Double = 0.0,
    val distance: Double = 0.0,
    val deliveryCosts: Double = 0.0,
    val popularity: Double = 0.0,
    val newest: Double = 0.0,
    val minCost: Double = 0.0,
    val ratingAverage: Double = 0.0,
    var isFavorite: Boolean = false
)
