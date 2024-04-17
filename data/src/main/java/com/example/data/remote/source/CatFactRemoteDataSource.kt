package com.example.data.remote.source

import com.example.data.remote.dto.CatFactDto
import com.example.domain.response.Response
import javax.inject.Singleton

@Singleton
interface CatFactRemoteDataSource {
    suspend fun fetchOneCatFact(): Response<CatFactDto>
}