package com.ramadan.takeaway.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.ramadan.takeaway.ui.model.RestaurantModel
import com.ramadan.takeaway.ui.sort.SortingOptionsHandler
import com.ramadan.takeaway.ui.sort.wraper.SortedListComparatorWrapper
import com.ramadan.takeaway.util.SortingKeys

class RestaurantSortedListImpl(adapter: RecyclerView.Adapter<*>) : SortedListComparatorWrapper<RestaurantModel>(
    adapter,
    DEFAULT_ORDER
) {

    companion object {
        private val DEFAULT_ORDER = SortingOptionsHandler.buildSortByOptionsComparator(SortingKeys.BEST_MATCH)
    }

    override fun areContentsTheSame(oldItem: RestaurantModel, newItem: RestaurantModel): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(item1: RestaurantModel, item2: RestaurantModel): Boolean {
        return item1 == item2
    }
}
