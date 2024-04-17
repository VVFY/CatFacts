package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.local.entity.CatFactEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CatFactsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(catFact: CatFactEntity)

    @Query("SELECT * FROM cat_facts")
    fun loadAllCatFacts(): Flow<List<CatFactEntity>>

    @Query("DELETE FROM cat_facts")
    suspend fun deleteAll()
}
