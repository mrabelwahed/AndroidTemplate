package com.ramadan.takeaway.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.ramadan.takeaway.domain.interactor.FavoriteRestaurantInteractor
import com.ramadan.takeaway.domain.interactor.GetRestaurantsInteractor
import com.ramadan.takeaway.domain.interactor.UnFavoriteRestaurantInteractor
import com.ramadan.takeaway.domain.model.Restaurant
import com.ramadan.takeaway.ui.mapper.RestaurantModelMapper
import com.ramadan.takeaway.ui.model.RestaurantModel
import com.ramadan.takeaway.util.DataState
import com.ramadan.test_utils.RxSchedulerRule
import com.ramadan.test_utils.mock
import io.reactivex.Completable
import io.reactivex.Single
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import java.lang.RuntimeException

class RestaurantsViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var restaurantsViewModel: RestaurantsViewModel

    @Mock
    private lateinit var getRestaurantsInteractor: GetRestaurantsInteractor

    @Mock
    private lateinit var favoriteRestaurantInteractor: FavoriteRestaurantInteractor

    @Mock
    private lateinit var unFavoriteRestaurantInteractor: UnFavoriteRestaurantInteractor

    @Rule
    @JvmField
    var testSchedulerRule: RxSchedulerRule = RxSchedulerRule()

    @Mock
    private var restaurantsStateObserver: Observer<DataState<List<RestaurantModel>>> = mock()

    @Mock
    private var favoriteStateObserver: Observer<DataState<RestaurantModel>> = mock()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        restaurantsViewModel = RestaurantsViewModel(
            getRestaurantsInteractor,
            favoriteRestaurantInteractor,
            unFavoriteRestaurantInteractor
        )
            .apply {
                dataState.observeForever(restaurantsStateObserver)
                favoriteState.observeForever(favoriteStateObserver)
            }
    }

    @Test
    fun `DemoViewModel is ready for test`() {
        assertNotNull(restaurantsViewModel)
    }

    @Test
    fun `should success when getRestaurants returns proper data`() {
        // Given
        val data = givenData()
        `when`(getRestaurantsInteractor.execute(Unit))
            .thenReturn(Single.just(data))
        // When
        getRestaurantsInteractor.execute(Unit)
        restaurantsViewModel.changeDataStateForRestaurants(data)
        // Then
        verify(restaurantsStateObserver).onChanged(DataState.Success(RestaurantModelMapper.mapFromEntityList(data)))
    }

    @Test(expected = RuntimeException::class)
    fun `should receive error state when getRestaurants throws Exception `() {
        // given
        `when`(getRestaurantsInteractor.execute(Unit))
            .thenThrow(RuntimeException())
        // when
        getRestaurantsInteractor.execute(Unit)
        // Then
        verify(restaurantsStateObserver).onChanged(DataState.Loading)
        verify(restaurantsStateObserver).onChanged(DataState.Error(RuntimeException()))
    }

    @Test
    fun `should success when favorite restaurant is success`() {
        // Given
        val data = givenRestaurant()
        `when`(favoriteRestaurantInteractor.execute(data))
            .thenReturn(Completable.complete())
        // When
        favoriteRestaurantInteractor.execute(data)
        restaurantsViewModel.changeDataStateForRestaurant(data)
        // Then
        verify(favoriteStateObserver).onChanged(DataState.Success(RestaurantModelMapper.mapFromEntity(data)))
    }

    @Test
    fun `should success when un favorite restaurant is success`() {
        // Given
        val data = givenRestaurant()
        `when`(unFavoriteRestaurantInteractor.execute(data))
            .thenReturn(Completable.complete())
        // When
        unFavoriteRestaurantInteractor.execute(data)
        restaurantsViewModel.changeDataStateForRestaurant(data)
        // Then
        verify(favoriteStateObserver).onChanged(DataState.Success(RestaurantModelMapper.mapFromEntity(data)))
    }

    private fun givenData(): List<Restaurant> {
        val rest1 = Restaurant(
            name = "Tanoshii Sushi",
            status = "open",
            bestMatch = 0.0,
            newest = 96.0,
            ratingAverage = 4.5,
            distance = 1190.0,
            popularity = 17.0,
            averageProductPrice = 1536.0,
            deliveryCosts = 200.0,
            minCost = 1000.0
        )

        val rest2 = Restaurant(
            name = "Tandoori Express",
            status = "open",
            bestMatch = 0.0,
            newest = 96.0,
            ratingAverage = 4.5,
            distance = 1190.0,
            popularity = 17.0,
            averageProductPrice = 1536.0,
            deliveryCosts = 200.0,
            minCost = 1000.0
        )

        val rest3 = Restaurant(
            name = "Royal Thai",
            status = "closed",
            bestMatch = 0.0,
            newest = 96.0,
            ratingAverage = 4.5,
            distance = 1190.0,
            popularity = 17.0,
            averageProductPrice = 1536.0,
            deliveryCosts = 200.0,
            minCost = 1000.0
        )
        val list = ArrayList<Restaurant>()
        list.add(rest1)
        list.add(rest2)
        list.add(rest3)
        return list
    }

    private fun givenRestaurant() = Restaurant(
        name = "Tanoshii Sushi",
        status = "open",
        bestMatch = 0.0,
        newest = 96.0,
        ratingAverage = 4.5,
        distance = 1190.0,
        popularity = 17.0,
        averageProductPrice = 1536.0,
        deliveryCosts = 200.0,
        minCost = 1000.0
    )
}
