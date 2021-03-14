package com.odogwudev.repository.retrofit.dailypost

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DailyPostNetworkEntity(
    @SerializedName("date")
    @Expose
    var date: String,
    @SerializedName("explanation")
    @Expose
    var explanation: String,
    @SerializedName("hdurl")
    @Expose
    var hdUrl: String,
    @SerializedName("title")
    @Expose
    var title: String
)
