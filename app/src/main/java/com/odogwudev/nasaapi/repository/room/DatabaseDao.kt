package com.odogwudev.nasaapi.repository.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.odogwudev.nasaapi.repository.room.dailypost.DailyPostCacheEntity

@Dao
interface DatabaseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDailyPost(dailyPost: DailyPostCacheEntity): Long

    @Query("SELECT * FROM dailyPosts WHERE title =:title LIMIT 1")
    suspend fun get(title: String): DailyPostCacheEntity
}