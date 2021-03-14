package com.odogwudev.repository.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.odogwudev.repository.room.dailypost.DailyPostCacheEntity
@Database(entities = [DailyPostCacheEntity::class], version = 1)
abstract class Database: RoomDatabase() {
    abstract fun databaseDao(): DatabaseDao

    companion object {
        val DB_NAME = "local_db"
    }
}