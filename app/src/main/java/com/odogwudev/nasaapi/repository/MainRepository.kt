package com.odogwudev.nasaapi.repository

import android.content.Context
import android.util.Log
import com.odogwudev.nasaapi.R
import com.odogwudev.nasaapi.model.DailyPost
import com.odogwudev.nasaapi.utils.DataState
import com.odogwudev.nasaapi.repository.retrofit.NetworkService
import com.odogwudev.nasaapi.repository.retrofit.dailypost.DailyPostNetworkMapper
import com.odogwudev.nasaapi.repository.room.DatabaseDao
import com.odogwudev.nasaapi.repository.room.dailypost.DailyPostCacheMapper
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainRepository constructor(
    @ApplicationContext
    private val context: Context,
    private val databaseDao: DatabaseDao,
    private val networkService: NetworkService,
    private val dailyPostCacheMapper: DailyPostCacheMapper,
    private val dailyPostNetworkMapper: DailyPostNetworkMapper
) {
    suspend fun getDailyPost(): Flow<DataState<DailyPost>> = flow {
        emit(DataState.Loading)
        try {
            val networkDailyPost =
                networkService.getDailyPost(context.getString(R.string.nasa_api_key))
            Log.d(TAG, "getDailyPost: $networkDailyPost")
            val dailyPost = dailyPostNetworkMapper.mapFromEntity(networkDailyPost)
            databaseDao.insertDailyPost(dailyPostCacheMapper.mapToEntity(dailyPost))
            val insertedDailyPost = databaseDao.get(dailyPost.title)
            Log.d(TAG, "getDailyPost: $insertedDailyPost")
            emit(DataState.Success(dailyPost))
        } catch (ex: Exception) {
            emit(DataState.Error(ex))
        }
    }

    companion object {
        const val TAG = "MainRepository"
    }
}