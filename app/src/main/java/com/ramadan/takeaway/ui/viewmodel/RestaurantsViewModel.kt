package com.ramadan.takeaway.ui.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ramadan.takeaway.domain.interactor.FavoriteRestaurantInteractor
import com.ramadan.takeaway.domain.interactor.GetRestaurantsInteractor
import com.ramadan.takeaway.domain.interactor.UnFavoriteRestaurantInteractor
import com.ramadan.takeaway.domain.model.Restaurant
import com.ramadan.takeaway.ui.mapper.RestaurantModelMapper
import com.ramadan.takeaway.ui.model.RestaurantModel
import com.ramadan.takeaway.util.DataState
import com.ramadan.takeaway.util.SortingKeys
import io.reactivex.disposables.CompositeDisposable
import java.util.Locale
import kotlin.collections.ArrayList

class RestaurantsViewModel @ViewModelInject constructor(
    private val getRestaurantsInteractor: GetRestaurantsInteractor,
    private val favoriteRestaurantInteractor: FavoriteRestaurantInteractor,
    private val unFavoriteRestaurantInteractor: UnFavoriteRestaurantInteractor
) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    private val _dataState: MutableLiveData<DataState<List<RestaurantModel>>> = MutableLiveData()
    val dataState: LiveData<DataState<List<RestaurantModel>>>
        get() = _dataState
    private val _sortingOptions: MutableLiveData<SortingKeys> = MutableLiveData()
    val sortingOptions: LiveData<SortingKeys>
        get() = _sortingOptions
    private val _favoriteState: MutableLiveData<DataState<RestaurantModel>> = MutableLiveData()
    val favoriteState: LiveData<DataState<RestaurantModel>>
        get() = _favoriteState

    var dataSet = ArrayList<RestaurantModel>()

    fun getRestaurants() {
        if (_dataState.value != null) return

        _dataState.value = DataState.Loading
        compositeDisposable.add(
            getRestaurantsInteractor.execute(Unit).subscribe(
                { res ->
                    _dataState.value =
                        DataState.Success(RestaurantModelMapper.mapFromEntityList(res))
                },
                { error -> _dataState.value = DataState.Error(error as RuntimeException) }
            )
        )
    }

    fun favoriteRestaurant(restaurantModel: RestaurantModel) {
        restaurantModel.isFavorite = true
        compositeDisposable.add(
            favoriteRestaurantInteractor.execute(RestaurantModelMapper.mapToEntity(restaurantModel))
                .subscribe {
                    _favoriteState.value = DataState.Success(restaurantModel)
                }
        )
    }

    fun unFavoriteRestaurant(restaurantModel: RestaurantModel) {
        restaurantModel.isFavorite = false
        compositeDisposable.add(
            unFavoriteRestaurantInteractor.execute(RestaurantModelMapper.mapToEntity(restaurantModel))
                .subscribe {
                    _favoriteState.value = DataState.Success(restaurantModel)
                }
        )
    }
    fun filterRestaurantsByKeyword(keyword: String):
        List<RestaurantModel> {
            val filteredList = ArrayList<RestaurantModel>()
            dataSet.forEach {
                if (it.name.toLowerCase(Locale.getDefault()).contains(keyword.toLowerCase(Locale.getDefault())))
                    filteredList.add(it)
            }
            return filteredList
        }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun setSelectedSortingOption(key: SortingKeys) {
        _sortingOptions.value = key
    }

    @VisibleForTesting
    fun changeDataStateForRestaurants(data: List<Restaurant>) {
        _dataState.value = DataState.Success(RestaurantModelMapper.mapFromEntityList(data))
    }

    @VisibleForTesting
    fun changeDataStateForRestaurant(data: Restaurant) {
        _favoriteState.value = DataState.Success(RestaurantModelMapper.mapFromEntity(data))
    }
}
