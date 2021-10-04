package com.ramadan.takeaway.di

import android.content.Context
import androidx.room.Room
import com.ramadan.takeaway.data.db.AppDatabase
import com.ramadan.takeaway.data.db.RestaurantsDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }
    @Singleton
    @Provides
    fun providesRestaurantDAO(appDatabase: AppDatabase): RestaurantsDAO {
        return appDatabase.restaurantsDao()
    }
}
