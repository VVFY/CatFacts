package com.example.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.local.dao.CatFactsDao
import com.example.data.local.entity.CatFactEntity

@Database(entities = [CatFactEntity::class], version = 1, exportSchema = true)
abstract class CatFactDatabase : RoomDatabase() {

    abstract fun catFactDao(): CatFactsDao

    companion object {
        const val DB_NAME = "cat_fact_db"
    }
}