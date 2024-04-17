package com.example.domain.usecase

import com.example.domain.repository.CatFactRepository
import com.example.domain.response.Response
import javax.inject.Inject

class FetchCatFactUseCase @Inject constructor(
    private val catFactRepository: CatFactRepository
) {
    suspend operator fun invoke(): Response<Unit> {
        return catFactRepository.fetchOneCatFact()
    }
}