package com.ramadan.takeaway.util

import androidx.recyclerview.widget.DiffUtil
import com.ramadan.takeaway.ui.model.RestaurantModel
import javax.inject.Inject

class RestaurantDiffUtils @Inject constructor(
    private var oldRestaurants: MutableList<RestaurantModel>,
    private var newRestaurants: MutableList<RestaurantModel>
) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldRestaurants[oldItemPosition].name == newRestaurants[newItemPosition].name &&
            oldRestaurants[oldItemPosition].isFavorite == newRestaurants[newItemPosition].isFavorite
    }

    override fun getOldListSize(): Int = oldRestaurants.size

    override fun getNewListSize(): Int = newRestaurants.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldRestaurants[oldItemPosition] == newRestaurants[newItemPosition]
    }
}
