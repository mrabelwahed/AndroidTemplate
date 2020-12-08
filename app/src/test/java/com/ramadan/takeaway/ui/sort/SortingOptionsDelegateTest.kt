package com.ramadan.takeaway.ui.sort

import com.ramadan.takeaway.ui.model.RestaurantModel
import com.ramadan.takeaway.util.SortingKeys
import org.junit.Assert.assertEquals
import org.junit.Test

class SortingOptionsDelegateTest {
    @Test
    fun testBuildSortByOptionsComparator_SortData_ByBestMatch() {
        val rest1 = RestaurantModel(
            name = "Tanoshii Sushi",
            status = "open",
            bestMatch = 3.0,
            newest = 96.0,
            ratingAverage = 4.5,
            distance = 1190.0,
            popularity = 17.0,
            averageProductPrice = 1536.0,
            deliveryCosts = 200.0,
            minCost = 1000.0
        )

        val rest2 = RestaurantModel(
            name = "Tandoori Express",
            status = "open",
            bestMatch = 40.0,
            newest = 96.0,
            ratingAverage = 4.5,
            distance = 1190.0,
            popularity = 17.0,
            averageProductPrice = 1536.0,
            deliveryCosts = 200.0,
            minCost = 1000.0
        )

        val rest3 = RestaurantModel(
            name = "Royal Thai",
            status = "open",
            bestMatch = 10.0,
            newest = 96.0,
            ratingAverage = 4.5,
            distance = 1190.0,
            popularity = 17.0,
            averageProductPrice = 1536.0,
            deliveryCosts = 200.0,
            minCost = 1000.0
        )
        val list = mutableListOf(rest1, rest2, rest3)
        val bestMatchComparator =
            SortingOptionsHandler.buildSortByOptionsComparator(SortingKeys.BEST_MATCH)
        list.sortWith(bestMatchComparator)
        assertEquals(list.indexOf(rest2), 0)
        assertEquals(list.indexOf(rest3), 1)
        assertEquals(list.indexOf(rest1), 2)
    }

    @Test
    fun testBuildSortByOptionsComparator_SortData_ByOpenState() {

        val rest1 = RestaurantModel(
            name = "Tanoshii Sushi",
            status = "closed",
            bestMatch = 3.0,
            newest = 96.0,
            ratingAverage = 4.5,
            distance = 1190.0,
            popularity = 17.0,
            averageProductPrice = 1536.0,
            deliveryCosts = 200.0,
            minCost = 1000.0
        )

        val rest2 = RestaurantModel(
            name = "Tandoori Express",
            status = "order ahead",
            bestMatch = 40.0,
            newest = 96.0,
            ratingAverage = 4.5,
            distance = 1190.0,
            popularity = 17.0,
            averageProductPrice = 1536.0,
            deliveryCosts = 200.0,
            minCost = 1000.0
        )

        val rest3 = RestaurantModel(
            name = "Royal Thai",
            status = "open",
            bestMatch = 10.0,
            newest = 96.0,
            ratingAverage = 4.5,
            distance = 1190.0,
            popularity = 17.0,
            averageProductPrice = 1536.0,
            deliveryCosts = 200.0,
            minCost = 1000.0
        )
        val list = mutableListOf(rest1, rest2, rest3)

        val bestMatchComparator =
            SortingOptionsHandler.buildSortByOptionsComparator(SortingKeys.BEST_MATCH)

        list.sortWith(bestMatchComparator)

        assertEquals(list.indexOf(rest3), 0)
        assertEquals(list.indexOf(rest2), 1)
        assertEquals(list.indexOf(rest1), 2)
    }

    @Test
    fun testBuildSortByOptionsComparator_SortData_ByFavorite() {
        val rest1 = RestaurantModel(
            name = "Tanoshii Sushi",
            status = "closed",
            bestMatch = 3.0,
            newest = 96.0,
            ratingAverage = 4.5,
            distance = 1190.0,
            popularity = 17.0,
            averageProductPrice = 1536.0,
            deliveryCosts = 200.0,
            minCost = 1000.0
        )

        val rest2 = RestaurantModel(
            name = "Tandoori Express",
            status = "order ahead",
            bestMatch = 40.0,
            newest = 96.0,
            ratingAverage = 4.5,
            distance = 1190.0,
            popularity = 17.0,
            averageProductPrice = 1536.0,
            deliveryCosts = 200.0,
            minCost = 1000.0,
            isFavorite = true
        )

        val rest3 = RestaurantModel(
            name = "Royal Thai",
            status = "open",
            bestMatch = 10.0,
            newest = 96.0,
            ratingAverage = 4.5,
            distance = 1190.0,
            popularity = 17.0,
            averageProductPrice = 1536.0,
            deliveryCosts = 200.0,
            minCost = 1000.0
        )

        val list = mutableListOf(rest1, rest2, rest3)

        val bestMatchComparator = SortingOptionsHandler.buildSortByOptionsComparator(SortingKeys.BEST_MATCH)

        list.sortWith(bestMatchComparator)

        assertEquals(list.indexOf(rest2), 0)
        assertEquals(list.indexOf(rest3), 1)
        assertEquals(list.indexOf(rest1), 2)
    }
}
