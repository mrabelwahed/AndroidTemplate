package com.ramadan.takeaway.domain.interactor

import com.ramadan.takeaway.data.repository.RestaurantsRepository
import com.ramadan.takeaway.domain.model.Restaurant
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UnFavoriteRestaurantInteractor @Inject constructor(private val repository: RestaurantsRepository) :
    Usecase<Restaurant, Completable> {

    override fun execute(param: Restaurant): Completable {
        return repository.unFavoriteRestaurant(param)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
