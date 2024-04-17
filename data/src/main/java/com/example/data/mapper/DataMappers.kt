package com.example.data.mapper

import com.example.data.local.entity.CatFactEntity
import com.example.data.remote.dto.CatFactDto
import com.example.domain.model.CatFact

fun CatFactEntity.toCatFact() = CatFact(
    id = id,
    fact = fact
)

fun CatFactDto.toCatFactEntity() = CatFactEntity(
    id = 0,
    fact = fact.replace("\\",""),
    length = length
)

fun List<CatFactEntity>.toCatFacts() = map(CatFactEntity::toCatFact)