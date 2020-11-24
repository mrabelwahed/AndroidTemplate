package com.ramadan.takeaway.ui.mapper

import com.ramadan.takeaway.domain.model.Restaurant
import com.ramadan.takeaway.ui.model.RestaurantModel
import com.ramadan.takeaway.util.EntityMapper

object RestaurantModelMapper : EntityMapper<Restaurant, RestaurantModel> {

    override fun mapFromEntity(entity: Restaurant): RestaurantModel {
        return RestaurantModel(
            name = entity.name,
            status = entity.status,
            averageProductPrice = entity.averageProductPrice,
            bestMatch = entity.bestMatch,
            distance = entity.distance,
            deliveryCosts = entity.deliveryCosts,
            popularity = entity.popularity,
            newest = entity.newest,
            minCost = entity.minCost,
            ratingAverage = entity.ratingAverage,
            isFavorite = entity.isFavorite
        )
    }

    override fun mapToEntity(uiModel: RestaurantModel): Restaurant {
        return Restaurant(
            name = uiModel.name,
            status = uiModel.status,
            averageProductPrice = uiModel.averageProductPrice,
            bestMatch = uiModel.bestMatch,
            distance = uiModel.distance,
            deliveryCosts = uiModel.deliveryCosts,
            popularity = uiModel.popularity,
            newest = uiModel.newest,
            minCost = uiModel.minCost,
            ratingAverage = uiModel.ratingAverage,
            isFavorite = uiModel.isFavorite
        )
    }

    fun mapFromEntityList(entities: List<Restaurant>): List<RestaurantModel> {
        return entities.map { mapFromEntity(it) }
    }
}
