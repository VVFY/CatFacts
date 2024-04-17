package com.example.data.di

import com.example.data.repository.CatFactRepositoryImpl
import com.example.domain.repository.CatFactRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindCatFactRepository(repository: CatFactRepositoryImpl): CatFactRepository
}