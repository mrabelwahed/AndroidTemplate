package com.ramadan.takeaway.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SortedList
import com.ramadan.takeaway.R
import com.ramadan.takeaway.ui.model.RestaurantModel
import com.ramadan.takeaway.ui.sort.wraper.SortedListComparatorWrapper
import javax.inject.Inject

class RestaurantsAdapter @Inject constructor(val clickListener: (RestaurantModel) -> Unit) :
    RecyclerView.Adapter<RestaurantsAdapter.RestaurantsViewHolder>() {
    private val restaurantSortedList: SortedList<RestaurantModel>
    private val sortedListComparatorWrapper: SortedListComparatorWrapper<RestaurantModel>

    init {
        sortedListComparatorWrapper =
            RestaurantSortedListImpl(this)
        restaurantSortedList = SortedList(
            RestaurantModel::class.java,
            SortedList.BatchedCallback<RestaurantModel>(sortedListComparatorWrapper)
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_restaurant_view, parent, false)
        return RestaurantsViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: RestaurantsViewHolder, position: Int) {
        holder.bindRestaurant(restaurantSortedList[position], clickListener)
    }

    override fun getItemCount() = restaurantSortedList.size()

    fun addRestaurants(restaurants: List<RestaurantModel>) {
        restaurantSortedList.clear()
        with(restaurantSortedList) {
            beginBatchedUpdates()
            addAll(restaurants)
            endBatchedUpdates()
        }
    }

    fun changeSortType(comparator: Comparator<RestaurantModel>) {
        with(restaurantSortedList) {
            sortedListComparatorWrapper.setComparator(comparator)
            beginBatchedUpdates()
            val tempRestaurants =
                (0 until restaurantSortedList.size()).mapTo(ArrayList<RestaurantModel>()) { get(it) }
            clear()
            addAll(tempRestaurants)
            tempRestaurants.clear()
            endBatchedUpdates()
        }
    }

    class RestaurantsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.textView_restaurant_name)
        val status: TextView = itemView.findViewById(R.id.textView_restaurant_open_status)
        val favoriteImage: ImageView = itemView.findViewById(R.id.imageView_ListItem_favorite)
        fun bindRestaurant(
            restaurantModel: RestaurantModel,
            clickListener: (RestaurantModel) -> Unit
        ) {
            name.text = restaurantModel.name
            status.text = restaurantModel.status
            if (restaurantModel.isFavorite)
                favoriteImage.setImageResource(R.drawable.ic_favorite_remove)
            else
                favoriteImage.setImageResource(R.drawable.ic_favorite_add)

            favoriteImage.setOnClickListener {
                clickListener(restaurantModel)
            }
        }
    }
}
