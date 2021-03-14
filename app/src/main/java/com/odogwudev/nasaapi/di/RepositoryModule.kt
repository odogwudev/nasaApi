package com.odogwudev.nasaapi.di

import android.content.Context
import com.odogwudev.nasaapi.repository.MainRepository
import com.odogwudev.nasaapi.repository.retrofit.NetworkService
import com.odogwudev.nasaapi.repository.retrofit.dailypost.DailyPostNetworkMapper
import com.odogwudev.nasaapi.repository.room.DatabaseDao
import com.odogwudev.nasaapi.repository.room.dailypost.DailyPostCacheMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideMainRepository(
        @ApplicationContext
        context: Context,
        databaseDao: DatabaseDao,
        networkService: NetworkService,
        dailyPostCacheMapper: DailyPostCacheMapper,
        dailyPostNetworkMapper: DailyPostNetworkMapper
    ): MainRepository {
        return MainRepository(
            context,
            databaseDao,
            networkService,
            dailyPostCacheMapper,
            dailyPostNetworkMapper
        )
    }
}