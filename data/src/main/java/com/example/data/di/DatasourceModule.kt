package com.example.data.di

import com.example.data.remote.source.CatFactRemoteDataSource
import com.example.data.remote.source.CatFactRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DatasourceModule {

    @Singleton
    @Binds
    abstract fun bindCatFactNetworkDataSource(dataSource: CatFactRemoteDataSourceImpl): CatFactRemoteDataSource

}