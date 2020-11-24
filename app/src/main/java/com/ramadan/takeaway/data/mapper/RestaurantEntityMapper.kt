package com.ramadan.takeaway.data.mapper

import com.ramadan.takeaway.data.db.RestaurantEntity
import com.ramadan.takeaway.domain.model.Restaurant
import com.ramadan.takeaway.util.EntityMapper

object RestaurantEntityMapper : EntityMapper<RestaurantEntity, Restaurant> {
    override fun mapFromEntity(entity: RestaurantEntity): Restaurant {
        return Restaurant(
            name = entity.name,
            isFavorite = entity.isFavorite
        )
    }

    override fun mapToEntity(model: Restaurant): RestaurantEntity {
        return RestaurantEntity(name = model.name, isFavorite = model.isFavorite)
    }

    fun mapFromEntityList(entities: List<RestaurantEntity>): List<Restaurant> {
        return entities.map { mapFromEntity(it) }
    }
}
