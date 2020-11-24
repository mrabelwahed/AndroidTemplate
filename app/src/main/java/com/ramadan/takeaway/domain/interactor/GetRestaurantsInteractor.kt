package com.ramadan.takeaway.domain.interactor

import com.ramadan.takeaway.data.repository.RestaurantsRepository
import com.ramadan.takeaway.domain.model.Restaurant
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GetRestaurantsInteractor @Inject constructor(private val repository: RestaurantsRepository) : Usecase<Unit, Single<List<Restaurant>>> {

    override fun execute(param: Unit): Single<List<Restaurant>> {
        return repository.getRestaurants()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
