package com.ramadan.takeaway.di

import android.content.Context
import com.ramadan.takeaway.data.db.RestaurantsDAO
import com.ramadan.takeaway.data.repository.RestaurantsRepository
import com.ramadan.takeaway.data.repository.RestaurantsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun providesRestaurantsRepository(@ApplicationContext appContext: Context, dao: RestaurantsDAO): RestaurantsRepository {
        return RestaurantsRepositoryImpl(appContext, dao)
    }
}
