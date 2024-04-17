package com.example.domain.usecase

import com.example.domain.model.CatFact
import com.example.domain.repository.CatFactRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCatFactsUseCase @Inject constructor(
    private val catFactRepository: CatFactRepository
) {
    operator fun invoke(): Flow<List<CatFact>> {
        return catFactRepository.loadCatFact()
    }
}