package com.example.domain.repository

import com.example.domain.response.Response
import com.example.domain.model.CatFact
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Singleton
interface CatFactRepository {

    fun loadCatFact(): Flow<List<CatFact>>

    suspend fun fetchOneCatFact() : Response<Unit>
}