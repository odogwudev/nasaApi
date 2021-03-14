package com.odogwudev.nasaapi.repository.retrofit

import com.odogwudev.nasaapi.repository.retrofit.dailypost.DailyPostNetworkEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {
    @GET("planetary/apod")
    suspend fun getDailyPost(@Query("api_key") key: String): DailyPostNetworkEntity
}