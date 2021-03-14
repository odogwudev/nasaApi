package com.odogwudev.nasaapi.model

data class DailyPost(
    val copyright: String,
    var date: String,
    var explanation: String,
    var hdurl: String,
    val media_type: String,
    val service_version: String,
    var title: String,
    var url: String
)