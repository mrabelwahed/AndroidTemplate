package com.ramadan.takeaway.data.mapper

import com.ramadan.takeaway.data.model.RestaurantItem
import com.ramadan.takeaway.data.model.SortingValues
import com.ramadan.takeaway.domain.model.Restaurant
import com.ramadan.takeaway.util.EntityMapper

object RestaurantMapper : EntityMapper<RestaurantItem, Restaurant> {

    override fun mapFromEntity(entity: RestaurantItem): Restaurant {
        return Restaurant(
            name = entity.name,
            status = entity.status,
            averageProductPrice = entity.sortingValues.averageProductPrice,
            bestMatch = entity.sortingValues.bestMatch,
            distance = entity.sortingValues.distance,
            deliveryCosts = entity.sortingValues.deliveryCosts,
            popularity = entity.sortingValues.popularity,
            newest = entity.sortingValues.newest,
            minCost = entity.sortingValues.minCost,
            ratingAverage = entity.sortingValues.ratingAverage
        )
    }

    override fun mapToEntity(domainModel: Restaurant): RestaurantItem {
        val averageProductPrice = domainModel.averageProductPrice
        val bestMatch = domainModel.bestMatch
        val distance = domainModel.distance
        val deliveryCosts = domainModel.deliveryCosts
        val popularity = domainModel.popularity
        val newest = domainModel.newest
        val minCost = domainModel.minCost
        val ratingAverage = domainModel.ratingAverage

        val sortingValues = SortingValues(
            averageProductPrice,
            bestMatch,
            distance,
            deliveryCosts,
            popularity,
            newest,
            minCost,
            ratingAverage
        )
        return RestaurantItem(
            name = domainModel.name,
            status = domainModel.status,
            sortingValues = sortingValues
        )
    }

    fun mapFromEntityList(entities: List<RestaurantItem>): List<Restaurant> {
        return entities.map { mapFromEntity(it) }
    }
}
