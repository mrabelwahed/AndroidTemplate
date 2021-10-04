package com.ramadan.takeaway.ui

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ramadan.takeaway.R
import com.ramadan.takeaway.ui.adapter.RestaurantsAdapter
import com.ramadan.takeaway.ui.model.RestaurantModel
import com.ramadan.takeaway.ui.sort.SortingOptionsHandler
import com.ramadan.takeaway.ui.viewmodel.RestaurantsViewModel
import com.ramadan.takeaway.util.DataState
import com.ramadan.takeaway.util.SortingKeys
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*

@AndroidEntryPoint
class HomeFragment: Fragment(R.layout.fragment_home) {
    lateinit var adapter: RestaurantsAdapter
    private val restaurantsViewModel: RestaurantsViewModel by viewModels()
    private var currentRestaurantModel: RestaurantModel? = null
    private var currentSortKey: SortingKeys = SortingKeys.BEST_MATCH
    private lateinit var searchView: SearchView

    private fun RecyclerView.setup(context: Context) {
        this.layoutManager = LinearLayoutManager(context)
        this.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }

    val clickListener: (RestaurantModel) -> Unit = { data ->
        currentRestaurantModel = data
        if (!data.isFavorite)
            restaurantsViewModel.favoriteRestaurant(data)
        else
            restaurantsViewModel.unFavoriteRestaurant(data)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        recyclerViewList.also { it.setup(requireContext()) }
        adapter = RestaurantsAdapter(clickListener = clickListener)
        recyclerViewList.adapter = adapter
        textViewSelectedSort.text = "SortBy: ${getSortingString(currentSortKey)}"
        handleSortBtn()
        restaurantsViewModel.getRestaurants()
        subscribeForObserver()
    }

    private fun handleSortBtn() {
        sortBtn.setOnClickListener {
            showPopup(it)
        }
    }

    private fun showPopup(v: View) {
        PopupMenu(requireContext(), v).apply {
            setOnMenuItemClickListener { item->
                onMenuItemClick(item)
                true
            }
            inflate(R.menu.menu_sort_actions)
            show()
        }
    }

    private fun subscribeForObserver() {
        // observe data
        restaurantsViewModel.dataState.observe(
            viewLifecycleOwner,
            Observer {
                when (it) {
                    is DataState.Success<List<RestaurantModel>> -> {
                        handleLoading(false)
                        restaurantsViewModel.dataSet = it.data as ArrayList<RestaurantModel>
                        adapter.addRestaurants(it.data)
                    }
                    is DataState.Error -> {
                        handleLoading(false)
                        displayError(it.exception.message)
                    }
                    is DataState.Loading -> {
                        handleLoading(true)
                    }
                }
            }
        )
        // observe sort key
        restaurantsViewModel.sortingOptions.observe(
            viewLifecycleOwner,
            Observer {
                textViewSelectedSort.text = "SortBy: ${getSortingString(it)}"
                textViewSelectedSort.setTextColor(resources.getColor(R.color.accentColor))
                adapter.changeSortType(SortingOptionsHandler.buildSortByOptionsComparator(it))
            }
        )

        // observe favorite Status
        restaurantsViewModel.favoriteState.observe(
            viewLifecycleOwner,
            Observer {
                if (it is DataState.Success<RestaurantModel>) {
                    currentRestaurantModel?.isFavorite = it.data.isFavorite
                    restaurantsViewModel.setSelectedSortingOption(currentSortKey)
                }
            }
        )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
       activity?.menuInflater?.inflate(R.menu.menu_search, menu)

        // Associate searchable configuration with the SearchView
        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.setSearchableInfo(
            searchManager
                .getSearchableInfo(activity?.componentName)
        )
        searchView.maxWidth = Integer.MAX_VALUE

        // listening to search query text change
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                filterRestaurantsByKeyword(query)
                return false
            }

            override fun onQueryTextChange(query: String) = onQueryTextSubmit(query)
        })
    }


    fun filterRestaurantsByKeyword(keyword: String) {
        val filteredList = restaurantsViewModel.filterRestaurantsByKeyword(keyword)
        adapter.addRestaurants(filteredList)
    }

    fun displayError(message: String?) {
        message?.let { textView_List_callToAction.text = it }
    }

    fun handleLoading(isDisplayed: Boolean) {
        progressBar.visibility = if (isDisplayed) View.VISIBLE else View.GONE
        textView_List_callToAction.visibility = if (isDisplayed) View.VISIBLE else View.GONE
    }

     private fun onMenuItemClick(item: MenuItem?): Boolean {
        val sortingKey = item?.itemId?.let { getSortingKey(it) }
        sortingKey?.let {
            currentSortKey = it
            restaurantsViewModel.setSelectedSortingOption(it)
        }
        return true
    }



    private fun getSortingKey(key: Int): SortingKeys {
        return when (key) {
            R.id.action_averageProductPrice -> SortingKeys.AVERAGE_PRODUCT_PRICE
            R.id.action_bestMatch -> SortingKeys.BEST_MATCH
            R.id.action_deliveryCosts -> SortingKeys.DELIVERY_COSTS
            R.id.action_distance -> SortingKeys.DISTANCE
            R.id.action_minCost -> SortingKeys.MIN_COST
            R.id.action_newest -> SortingKeys.NEWEST
            R.id.action_popularity -> SortingKeys.POPULARITY
            R.id.action_ratingAverage -> SortingKeys.RATING_AVERAGE
            else -> SortingKeys.BEST_MATCH
        }
    }

    private fun getSortingString(key: SortingKeys): String {
        return when (key) {
            SortingKeys.AVERAGE_PRODUCT_PRICE -> getString(R.string.action_averageProductPrice)
            SortingKeys.BEST_MATCH -> getString(R.string.action_bestMatch)
            SortingKeys.DISTANCE -> getString(R.string.action_distance)
            SortingKeys.DELIVERY_COSTS -> getString(R.string.action_deliveryCosts)
            SortingKeys.POPULARITY -> getString(R.string.action_popularity)
            SortingKeys.NEWEST -> getString(R.string.action_newest)
            SortingKeys.MIN_COST -> getString(R.string.action_minCost)
            SortingKeys.RATING_AVERAGE -> getString(R.string.action_ratingAverage)
        }
    }

}