package com.example.data.repository

import com.example.data.di.DefaultDispatcher
import com.example.data.local.dao.CatFactsDao
import com.example.data.mapper.toCatFactEntity
import com.example.data.mapper.toCatFacts
import com.example.data.remote.dto.CatFactDto
import com.example.data.remote.source.CatFactRemoteDataSource
import com.example.domain.model.CatFact
import com.example.domain.repository.CatFactRepository
import com.example.domain.response.Response
import com.example.domain.response.resolveError
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject


class CatFactRepositoryImpl @Inject constructor(
    private val catFactRemoteSource: CatFactRemoteDataSource,
    private val catFactLocalSource: CatFactsDao,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher
) : CatFactRepository {

    override fun loadCatFact(): Flow<List<CatFact>> {
        return catFactLocalSource.loadAllCatFacts()
            .map { catFactEntityList ->
                withContext(dispatcher) {
                    catFactEntityList.toCatFacts()
                }
            }
    }

    override suspend fun fetchOneCatFact(): Response<Unit> {
        return runCatching {
            return when (val res: Response<CatFactDto> = catFactRemoteSource.fetchOneCatFact()) {
                is Response.Success -> {
                    withContext(dispatcher) {
                        catFactLocalSource.insert(res.data.toCatFactEntity())
                    }
                    Response.Success(Unit)
                }

                is Response.Error -> res
            }
        }.getOrElse {
            it.resolveError()
        }
    }


}