package com.example.data.remote.service

import com.example.data.remote.dto.CatFactDto
import retrofit2.Response
import retrofit2.http.GET

interface CatFactService {

    @GET("fact")
    suspend fun getCatFact(): Response<CatFactDto>
}