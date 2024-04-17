package com.example.data.remote.source

import com.example.data.remote.dto.CatFactDto
import com.example.data.remote.service.CatFactService
import com.example.data.remote.utils.response
import com.example.domain.response.Response
import javax.inject.Inject

class CatFactRemoteDataSourceImpl @Inject constructor(
    private val catFactService: CatFactService
): CatFactRemoteDataSource {
    override suspend fun fetchOneCatFact(): Response<CatFactDto> {
        return catFactService.getCatFact().response()
    }
}